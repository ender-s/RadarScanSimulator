package radarscangraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import windows.ScanGraphWindow;

public class HelicalScan extends Scan
{
    
    private Helix helix;
    private double elevationOfTarget, azimuthOfTarget;
    private double time; // max time for a complete helical scan
    private Point3D initialPositionOfTarget;
    private final int targetMovementAxis;
    // 0: x coordinate of target does not change
    // 1: y coordinate of target does not change
    // 2: z coordinate of target does not change
    
    public HelicalScan(ScanGraphWindow windowReference, Antenna antenna, double step,
            int numberOfMotionRepetition, double timeIncreaseStep, boolean targetMovement, int targetMovementAxis)
    {
        super(ScanType.HELICAL, windowReference, antenna, step, numberOfMotionRepetition, timeIncreaseStep, targetMovement);
        this.targetMovementAxis = targetMovementAxis;
    }
    
    private double getAzimuthByPosition(Point3D point)
    {
        double x = point.getX(), y = point.getY();
        if (x == 0) return 0;
        
        if (y == 0 && x < 0) return 270;
        if (y == 0 && x > 0) return 90;
        
        double positiveDivision = Math.abs(y / x);
        double arctan = Math.toDegrees(Math.atan(positiveDivision));
        
        if (x > 0 && y > 0)
            return 90 - arctan;
        if (x < 0 && y > 0)
            return 270 + arctan;
        if (x < 0 && y < 0)
            return 270 - arctan;
        
        return 90 + arctan;
    }    
    
    private double getElevationByPosition(Point3D point)
    {
        double z = point.getZ();
        
        return 90 - Math.toDegrees(Math.acos(z / point.getDistanceTo(new Point3D(0, 0, 0))));
        
    }
    
    @Override
    protected double getTotalGainAtTime(double t, double t2)
    {
        Point3D point = helix.getPositionAtTime(t);
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
            System.out.printf("At time t = %f: Azimuth = %f Elevation = %f", t, azimuth, elevation);
        
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
            "Helical Scan Time",
            "Pitch of Helix"));
        
        ArrayList<javax.swing.JTextField> textFieldArrayList =
                new ArrayList<javax.swing.JTextField>(windowReference.getSpecialTextFields().subList(8, 13));
        
        javax.swing.JComponent[] targetMovementComponents = windowReference.getHelicalTargetMovementComponents();
        
        if (targetMovement)
        {
            fieldNamesArrayList.add("Period of Circular Motion");
            
            textFieldArrayList.add((javax.swing.JTextField) targetMovementComponents[2]);
        }
        
        String[] fieldNames = fieldNamesArrayList.toArray(new String[0]);
        javax.swing.JTextField[] textFields = textFieldArrayList.toArray(new javax.swing.JTextField[0]);
        
        int[] integerFieldIndexes = {};        
        boolean[] validnessList = HelperMethods.getValidnessList(textFields, integerFieldIndexes);
        
        if (!HelperMethods.checkValidness(validnessList, fieldNames, windowReference))
            return false;
        
        
        double targetRange = Double.parseDouble(textFields[0].getText());
        double elevationOfTarget = Double.parseDouble(textFields[1].getText());
        double azimuthOfTarget = Double.parseDouble(textFields[2].getText());
        double helicalScanTime = Double.parseDouble(textFields[3].getText());
        double pitchOfHelix = Double.parseDouble(textFields[4].getText());

        this.elevationOfTarget = elevationOfTarget;
        this.azimuthOfTarget = azimuthOfTarget;
        this.time = helicalScanTime;
        double radiusOfHelix = targetRange * Math.cos(Math.toRadians(elevationOfTarget));
        System.out.println("Radius of Helix is " + radiusOfHelix);
        
        this.helix = new Helix(radiusOfHelix, pitchOfHelix);
        
        if (targetMovement)
        {
            double periodOfCircularMotionOfTarget = Double.parseDouble(textFields[5].getText());
            int index = ((javax.swing.JComboBox)targetMovementComponents[0]).getSelectedIndex();
            boolean direction = true ? index == 0: false;
            this.circularMotionOfTarget = new CircularMotion(radiusOfHelix,
                    periodOfCircularMotionOfTarget, 0, direction);

            
        }
        
        Target target = new Target(azimuthOfTarget, targetRange);
        super.setTarget(target);
        setPositionOfTarget();
        rfPathLoss = calculateRFPathLoss();

        return true;
    }
    
    private double getXCoordinateOfTargetAtAngle(double teta)
    {
        double x = initialPositionOfTarget.getX();
        
        if (targetMovementAxis == 0) return x;
        else
        {
            double r = circularMotionOfTarget.getRadius();
            double deltaX = r * Math.sin(Math.toRadians(teta));
            return x + deltaX;
        }    
        
    }
    
    private double getYCoordinateOfTargetAtAngle(double teta)
    {
        double y = initialPositionOfTarget.getY();
        if (targetMovementAxis == 1) return y;
        else
        {
            double r = circularMotionOfTarget.getRadius();
            double deltaY;
            if (targetMovementAxis == 0)
                deltaY = r * Math.sin(Math.toRadians(teta));
            else
                deltaY = r * Math.cos(Math.toRadians(teta));
            return y + deltaY;
        }
    }
    
    private double getZCoordinateOfTargetAtAngle(double teta)
    {
        double z = initialPositionOfTarget.getZ();
        if (targetMovementAxis == 2) return z;
        else
        {
            double r = circularMotionOfTarget.getRadius();
            double deltaZ = r * Math.cos(Math.toRadians(teta));
            return z + deltaZ;
        }
            
    }

    @Override
    public void fillPowerMap(Map<Double, Double> powerMap)
    {
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

    @Override
    public void setPositionOfTarget() 
    {
        double p = target.getRange();
        double elevation = Math.toRadians(elevationOfTarget);
        double azimuth = Math.toRadians(azimuthOfTarget);
                
        double x = p * Math.sin(elevation) * Math.cos(azimuth);
        double y = p * Math.sin(elevation) * Math.sin(azimuth);
        double z = p * Math.cos(elevation);
               
        this.initialPositionOfTarget = new Point3D(x, y, z);
        
        target.setPosition(this.initialPositionOfTarget);
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

}
