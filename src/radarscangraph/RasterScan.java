package radarscangraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import windows.ScanGraphWindow;

public class RasterScan extends Scan
{
    
    private double elevationOfTarget, azimuthOfTarget,
            lengthOfBar, elevationDecreasePerBar, linearSpeedOfBeam,
            initialAzimuthOfBeam, initialElevationOfBeam,
            zCoordinateChangePerBarStep, barPassDuration,
            initialXCoordinateOfBeam, initialZCoordinateOfBeam, barElevationPassDuration;
    
    private int numberOfBars;
    private Point3D initialPositionOfTarget;

    public RasterScan(ScanGraphWindow windowReference, Antenna antenna, double step,
            int numberOfMotionRepetition, double timeIncreaseStep, boolean targetMovement)
    {
        super(ScanType.RASTER, windowReference, antenna, step, numberOfMotionRepetition, timeIncreaseStep, targetMovement);
    }
    
    private Point3D getPositionAtTime(double t)
    {
        double oneFullPassDuration = barPassDuration + barElevationPassDuration;
        int numberOfFullPasses = (int)(t / oneFullPassDuration);
        
        double x, y, z;

        // simplify time variable (subtract time passed for previous passes)
        t -= numberOfFullPasses * oneFullPassDuration;
        double remainingTForBarPass = t;
        if (remainingTForBarPass > barPassDuration)
            remainingTForBarPass = barPassDuration;
        
        // if num is even, go right. if num is odd, go left
        if (numberOfFullPasses % 2 == 0)
            x = initialXCoordinateOfBeam + (linearSpeedOfBeam * remainingTForBarPass); // go right from the left-most side
       
        else
            x = (initialXCoordinateOfBeam + lengthOfBar) - (linearSpeedOfBeam * remainingTForBarPass); // go left from the right-most side
        
        y = target.getPosition().getY();
        
        if (HelperMethods.DEBUG)
        {
            System.out.printf("numberOfFullPasses:%d zCoordinateChangePerBarStep:%f initialZCoordinateOfBeam:%f%n", numberOfFullPasses, zCoordinateChangePerBarStep, initialZCoordinateOfBeam);
            System.out.printf("barPassDuration:%f remainingTime:%f%n", barPassDuration, t);
        }
        
        z = initialZCoordinateOfBeam - (numberOfFullPasses * zCoordinateChangePerBarStep);
        if (t > barPassDuration)
        {
            
            t -= barPassDuration;
            z -= t * linearSpeedOfBeam;
            
            if (HelperMethods.DEBUG)
            {
                System.out.printf("remainingTime is greater than barPassDuration. newT:%f newZ:%f%n", t, z);
            }
        }
        
        return new Point3D(x, y, z);
    }

    private double getAzimuthByPosition(Point3D point)
    {
        double x = point.getX(), y = point.getY();
        if (x == 0) return 0;
        
        if (y == 0 && x < 0) return 180;
        if (y == 0 && x > 0) return 0;
        
        double positiveDivision = Math.abs(y / x);
        double arctan = Math.toDegrees(Math.atan(positiveDivision));
        
        if (x > 0 && y > 0)
            return arctan;
        if (x < 0 && y > 0)
            return 180 - arctan;
        if (x < 0 && y < 0)
            return 180 + arctan;
        
        return 360 - arctan;
    }
    
    private double getElevationByPosition(Point3D point)
    {
        double x = point.getX();
        double y = point.getY();
        double z = point.getZ();
        
        
        if (y == 0)
        {
            if (x != 0) return Math.toDegrees(Math.atan(z / x));
            else
            {
               if (z == 0) return 0;
               else return 90;
            }
               
        }
        return 90 - Math.toDegrees(Math.acos(z / point.getDistanceTo(new Point3D(0, 0, 0))));
    }
    
    @Override
    public void setPositionOfTarget() 
    {   
        double p = target.getRange();
        
        double elevation = Math.toRadians(90 - elevationOfTarget);
        double azimuth = Math.toRadians(azimuthOfTarget);
        
        if (azimuthOfTarget == 0 || elevationOfTarget == 0)
        {
            if (azimuthOfTarget == 0 && elevationOfTarget != 0)
                target.setPosition(new Point3D(p, 0.0, p * Math.tan(elevation)));
            
            if (azimuthOfTarget != 0 && elevationOfTarget == 0)
                target.setPosition(new Point3D(p * Math.sin(azimuth), p * Math.cos(azimuth), 0.0));
            
            if (azimuthOfTarget == 0 && elevationOfTarget == 0)
                target.setPosition(new Point3D(p, 0.0, 0.0));
            
            return;
        }
                
        double x = p * Math.sin(elevation) * Math.cos(azimuth);
        double y = p * Math.sin(elevation) * Math.sin(azimuth);
        double z = p * Math.cos(elevation);
               
        this.initialPositionOfTarget = new Point3D(x, y, z);
        target.setPosition(this.initialPositionOfTarget);
    }

    @Override
    protected double getTotalGainAtTime(double t, double t2)
    {
        Point3D point = getPositionAtTime(t);
        double azimuthOfBeam = getAzimuthByPosition(point);
        double elevationOfBeam = getElevationByPosition(point);
        
        
        if (targetMovement)
        {
            setTargetAtTime(target, t2);
            this.azimuthOfTarget = getAzimuthByPosition(target.getPosition());
            this.elevationOfTarget = getElevationByPosition(target.getPosition());
            rfPathLoss = calculateRFPathLoss();
        }
        
        if (HelperMethods.DEBUG)
        {
            System.out.print("---------------------------------------------------------\nTime: " + t + " | ");
            System.out.printf("Beam: %s Azimuth = %f Elevation = %f | Target: %s Azimuth = %f Elevation = %f%n", point,
                    azimuthOfBeam, elevationOfBeam, target.getPosition(), azimuthOfTarget, elevationOfTarget);
        }

        double azimuth = Math.abs(azimuthOfBeam - azimuthOfTarget);
        if (azimuth > 180) azimuth = 360 - azimuth;
        double elevation = Math.abs(elevationOfBeam - elevationOfTarget);
        
        
        
        if (HelperMethods.DEBUG)
            System.out.printf("At time t = %f: Azimuth = %f Elevation = %f%n", t, azimuth, elevation);
        
        if (azimuth > antenna.getAzimuthWidth() / 2 || elevation > antenna.getElevationWidth() / 2)
        {
            if (HelperMethods.DEBUG)
                System.out.println("Out of beam! Returning 0");
            return 0;
        }

        int azimuthIndex = getClosestIndex(azimuth, azimuthHeader);
        int elevationIndex = getClosestIndex(elevation, elevationHeader);

        double result = antenna.getGain() + M[azimuthIndex][elevationIndex];
        
        if (HelperMethods.DEBUG)
        {
            System.out.printf("Azimuth: %f, Elevation: %f, azimuthIndex: %d, elevationIndex: %d, gain: %f%n",
                azimuth, elevation, azimuthIndex, elevationIndex, M[azimuthIndex][elevationIndex]);
            System.out.printf("from matrix: %f | getTotalGainAtTime: Returning %f%n", M[azimuthIndex][elevationIndex], result);
        }
        
        return result;
        
    }
    
    @Override
    protected double getTotalGainAtTime(double t) 
    {
        return 0;
    }

    @Override
    public boolean fetchValues()
    {
        ArrayList<String> fieldNamesArrayList = new ArrayList<String>(Arrays.asList("Target Range",
            "Elevation of Target",
            "Azimuth of Target",
            "Length of Bar",
            "Elevation Decrease per Bar",
            "Linear Speed of Beam",
            "Number of Bars",
            "Initial Azimuth of Beam",
            "Initial Elevation of Beam"));
        
        ArrayList<javax.swing.JTextField> textFieldArrayList = 
                new ArrayList<javax.swing.JTextField>(windowReference.getSpecialTextFields().subList(13, 22));
        
        
        javax.swing.JComponent[] targetMovementComponents = windowReference.getRasterTargetMovementComponents();
        
        if (targetMovement)
        {
            fieldNamesArrayList.add("Period of Circular Motion");
            fieldNamesArrayList.add("Radius of Circular Motion");
            
            textFieldArrayList.add((javax.swing.JTextField) targetMovementComponents[1]);
            textFieldArrayList.add((javax.swing.JTextField) targetMovementComponents[2]);
        }
        
        String[] fieldNames = fieldNamesArrayList.toArray(new String[0]);
        javax.swing.JTextField[] textFields = textFieldArrayList.toArray(new javax.swing.JTextField[0]);
                
        int[] integerFieldIndexes = {6};        
        boolean[] validnessList = HelperMethods.getValidnessList(textFields, integerFieldIndexes);
        
        if (!HelperMethods.checkValidness(validnessList, fieldNames, windowReference))
            return false;

        double targetRange = Double.parseDouble(textFields[0].getText());
        double elevationOfTarget = Double.parseDouble(textFields[1].getText());
        double azimuthOfTarget = Double.parseDouble(textFields[2].getText());
        double lengthOfBar = Double.parseDouble(textFields[3].getText());
        double decreasePerBar = Double.parseDouble(textFields[4].getText());
        double linearSpeedOfBeam = Double.parseDouble(textFields[5].getText());
        int numberOfBars = Integer.parseInt(textFields[6].getText());
        double initialAzimuthOfBeam = Double.parseDouble(textFields[7].getText());
        double initialElevationOfBeam = Double.parseDouble(textFields[8].getText());

        
        if (elevationOfTarget != 0 && azimuthOfTarget == 0)
        {
            double temp = elevationOfTarget;
            elevationOfTarget = azimuthOfTarget;
            azimuthOfTarget = temp;
            
            temp = initialAzimuthOfBeam;
            initialAzimuthOfBeam = initialElevationOfBeam;
            initialElevationOfBeam = temp;
        }
        
        
        this.elevationOfTarget = elevationOfTarget;
        this.azimuthOfTarget = azimuthOfTarget;
        this.lengthOfBar = lengthOfBar;
        this.zCoordinateChangePerBarStep = decreasePerBar;
        this.linearSpeedOfBeam = linearSpeedOfBeam;
        this.numberOfBars = numberOfBars;
        this.initialAzimuthOfBeam = initialAzimuthOfBeam;
        this.initialElevationOfBeam = initialElevationOfBeam;
        
        
        if (targetMovement)
        {
            double periodOfCircularMotionOfTarget = Double.parseDouble(textFields[9].getText());
            double radiusOfCircularMotionOfTarget = Double.parseDouble(textFields[10].getText());
            
            int index = ((javax.swing.JComboBox) targetMovementComponents[0]).getSelectedIndex();
            boolean direction = true ? index == 0 : false;
            this.circularMotionOfTarget = new CircularMotion(radiusOfCircularMotionOfTarget,
                periodOfCircularMotionOfTarget, 0, direction);
        }
        
        
        Target target = new Target(azimuthOfTarget, targetRange);
        super.setTarget(target);
        setPositionOfTarget();
        rfPathLoss = calculateRFPathLoss();
        
        double yDistance = target.getPosition().getY();
        this.initialXCoordinateOfBeam = yDistance / Math.tan(Math.toRadians(initialAzimuthOfBeam));
        
        this.barPassDuration = lengthOfBar / linearSpeedOfBeam;
        
        this.initialZCoordinateOfBeam = 
                Math.sqrt(Math.pow(this.initialXCoordinateOfBeam, 2) + Math.pow(yDistance, 2))
                * Math.tan(Math.toRadians(initialElevationOfBeam));

   
        return true;
    }

    private void setTargetAtTime(Target target, double t)
    {
        double teta = circularMotionOfTarget.getAngleAtTime(t);
        double x = getXCoordinateOfTargetAtAngle(teta);
        double y = getYCoordinateOfTargetAtAngle(teta);
        double z = getZCoordinateOfTargetAtAngle(teta);
        
        Point3D newPosition = new Point3D(x, y, z);
        double range = newPosition.getDistanceTo(Point3D.getOrigin());
        
        target.setPosition(newPosition);
        target.setRange(range);
    }
    
    private double getXCoordinateOfTargetAtAngle(double teta)
    {
        
        double x = initialPositionOfTarget.getX();
        double r = circularMotionOfTarget.getRadius();
        double deltaX = r * Math.sin(Math.toRadians(teta));
        return x + deltaX;

    }
    
    private double getYCoordinateOfTargetAtAngle(double teta)
    {
        return initialPositionOfTarget.getY();
    }
    
    private double getZCoordinateOfTargetAtAngle(double teta)
    {
        
        double z = initialPositionOfTarget.getZ();
        double r = circularMotionOfTarget.getRadius();
        double deltaZ = r * Math.cos(Math.toRadians(teta));
        return z + deltaZ;
            
    }
    
    @Override
    public void fillPowerMap(Map<Double, Double> powerMap)
    {
        double oneFullPassDuration = barPassDuration + barElevationPassDuration;
        double numberOfFullPass = numberOfBars - 1;
        double numberOfSingleHorizontalPass = 1;
        
        double time = oneFullPassDuration * numberOfFullPass + numberOfSingleHorizontalPass * barPassDuration;
        
        double increment_step = timeIncreaseStep;
        
        double i = 0.0;
        
        for (int c = 0; c < numberOfMotionRepetition; ++c)
        {
            for (double t = 0.0; t <= time; t += increment_step)
            {
                powerMap.put(i, getPowerDensityAtTargetAtTime(t, i));
                i += increment_step;
            }
        }
    }
}
