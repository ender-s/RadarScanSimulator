package radarscangraph;

import java.util.*;
import windows.ScanGraphWindow;

public class ConicalScan extends Scan
{
    
    private CircularMotion circularMotion;
    private Cone cone;
    
    public ConicalScan(ScanGraphWindow windowReference, Antenna antenna, double step,
            int numberOfMotionRepetition, double timeIncreaseStep, boolean targetMovement)
    {
        super(ScanType.CONICAL, windowReference, antenna, step, numberOfMotionRepetition, timeIncreaseStep, targetMovement);

    }
    
    private void setTargetAtTime(Target target, double t)
    {
        double angle = circularMotionOfTarget.getAngleAtTime(t);
        target.setPositionAngle(angle);
        setPositionOfTarget();
    }

    @Override
    protected double getTotalGainAtTime(double t)
    {
        if (HelperMethods.DEBUG)
            System.out.print("---------------------------------------------------------\nTime: " + t + " | ");
        double teta = circularMotion.getAngleAtTime(t);
        
        if (targetMovement)
            setTargetAtTime(target, t);
        
        double diff = Math.abs(teta - target.getPositionAngle());
        
        if (HelperMethods.DEBUG)
            System.out.printf("t = %f | Angle: %f | Angular difference: %f%n", t, teta, diff);

        double azimuth = Math.abs(getAzimuthAtAngle(teta));
        double elevation = Math.abs(getElevationAtAngle(teta));
        
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
    public void setPositionOfTarget()
    {
        double teta = target.getPositionAngle();
        Point3D position = new Point3D(getXCoordinateAtAngle(teta),
                                       getYCoordinateAtAngle(teta),
                                       getZCoordinateAtAngle(teta));
        target.setPosition(position);
    }

    @Override
    public boolean fetchValues()
    {
        
        ArrayList<String> fieldNamesArrayList = new ArrayList<String>(Arrays.asList("Target Range",
            "Squint Angle",
            "Degree of Target",
            "Period of Circular Motion"));

        javax.swing.JComponent[] targetMovementComponents = windowReference.getConicalTargetMovementComponents();      
        
        ArrayList<javax.swing.JTextField> textFieldArrayList = 
                new ArrayList<javax.swing.JTextField>(windowReference.getSpecialTextFields().subList(0, 4));
        
        if (targetMovement)
        {
            fieldNamesArrayList.add("Period of Circular Motion of Target");
            textFieldArrayList.add((javax.swing.JTextField)targetMovementComponents[1]);
        }
            
        String[] fieldNames = fieldNamesArrayList.toArray(new String[0]);

        javax.swing.JTextField[] textFields = textFieldArrayList.toArray(new javax.swing.JTextField[0]);
        
        int[] integerFieldIndexes = {};        
        boolean[] validnessList = HelperMethods.getValidnessList(textFields, integerFieldIndexes);
        
        if (!HelperMethods.checkValidness(validnessList, fieldNames, windowReference))
            return false;
        
        
        double targetRange = Double.parseDouble(textFields[0].getText());
        double squintAngle = Double.parseDouble(textFields[1].getText());
        double degreeOfTarget = Double.parseDouble(textFields[2].getText());
        double periodOfCircularMotion = Double.parseDouble(textFields[3].getText());
        
        
                
        this.cone = new Cone(targetRange, squintAngle);
        System.out.printf("Radius of circular motion is %f\n", cone.getRadius());
        
        this.circularMotion = new CircularMotion(cone.getRadius(), periodOfCircularMotion, 0, true);
        
        if (targetMovement)
        {
            double periodOfCircularMotionOfTarget = Double.parseDouble(textFields[4].getText());
            int index = ((javax.swing.JComboBox)targetMovementComponents[0]).getSelectedIndex();
            boolean direction = true ? index == 0: false;
            this.circularMotionOfTarget = new CircularMotion(cone.getRadius(), periodOfCircularMotionOfTarget, degreeOfTarget, direction);
        }
        
        Target target = new Target(degreeOfTarget, targetRange);
        super.setTarget(target);
        setPositionOfTarget();
        rfPathLoss = calculateRFPathLoss();

        return true;
    }
    
    @Override
    public void fillPowerMap(Map<Double, Double> powerMap)
    {
        double t = circularMotion.getPeriod() * numberOfMotionRepetition;
        double increment_step = timeIncreaseStep;
        for (double i = 0.0; i <= t; i += increment_step)
            powerMap.put(i, getPowerDensityAtTargetAtTime(i));

    }

    private double getXCoordinateAtAngle(double teta)
    {
        return circularMotion.getRadius() * Math.sin(Math.toRadians(teta));
    }
    
    private double getYCoordinateAtAngle(double teta)
    {
        return 0;
    }
    
    private double getZCoordinateAtAngle(double teta)
    {   
        return (circularMotion.getRadius() * Math.cos(Math.toRadians(teta)));
    }

    private double getAzimuthAtAngle(double teta)
    {
        
        Point3D targetPosition = target.getPosition();
        Point3D beam = new Point3D(getXCoordinateAtAngle(teta), getYCoordinateAtAngle(teta), getZCoordinateAtAngle(teta));

        
        double azimuthOfBeam = 0.0, azimuthOfTarget = 0.0;
        if (beam.getX() == 0) azimuthOfBeam = 0.0;
        else
        {
            azimuthOfBeam = new Angle(Math.toDegrees(Math.atan(beam.getY() / beam.getX()))).getAngle();
        }
        
        if (targetPosition.getX() == 0) azimuthOfTarget = 0.0;
        else
        {
            azimuthOfTarget = new Angle(Math.toDegrees(Math.atan(targetPosition.getY() / targetPosition.getX()))).getAngle();
        }
        
        double azimuth = new Angle(azimuthOfBeam - azimuthOfTarget).getAngle();

        if (HelperMethods.DEBUG)
            System.out.printf("Calculating azimuth at angle %f | Beam: (%f, %f, %f) | Target: (%f, %f, %f) | Azimuth: %f%n", 
                teta, beam.getX(), beam.getY(), beam.getZ(), targetPosition.getX(),
                targetPosition.getY(), targetPosition.getZ(), azimuth);

        return azimuth;
    }
    
    private double getElevationAtAngle(double teta)
    {
        Point3D targetPosition = target.getPosition();
        Point3D beam = new Point3D(getXCoordinateAtAngle(teta), getYCoordinateAtAngle(teta), getZCoordinateAtAngle(teta));
        
        double distance = beam.getDistanceTo(targetPosition);
        if (distance == 0) 
        {
            if (HelperMethods.DEBUG)
                System.out.println("Calculating elevation: Returning 0 since distance is 0");
            return 0;
        }

        
        Point3D origin = new Point3D(0, 0, 0);
        double distanceOfBeam = beam.getDistanceTo(origin);
        double distanceOfTarget = targetPosition.getDistanceTo(origin);
            
        Angle elevationAngleOfBeam = new Angle(Math.toDegrees(Math.acos(beam.getZ() / distanceOfBeam)));
        Angle elevationAngleOfTarget = new Angle(Math.toDegrees(Math.acos(targetPosition.getZ() / distanceOfTarget)));
        
        double elevationOfBeam = elevationAngleOfBeam.getAngle();
        double elevationOfTarget = elevationAngleOfTarget.getAngle();
        
        double elevation;
        
        if ((beam.getX() <= 0 && elevationOfBeam <= 90 && targetPosition.getX() >= 0 && elevationOfTarget <= 90)
         || (targetPosition.getX() <= 0 && elevationOfTarget <= 90 && beam.getX() >= 0 && elevationOfBeam <= 90))
        {
            elevation = Math.abs((-1 * elevationOfTarget) - elevationOfBeam);
        }
        
        else
        {
            if (targetPosition.getX() < 0)
                elevationOfTarget = 360 - elevationOfTarget;

            if (beam.getX() < 0)
                elevationOfBeam = 360 - elevationOfBeam;

            elevation = new Angle(Math.abs(elevationOfBeam - elevationOfTarget)).getAngle();
        }

        if (HelperMethods.DEBUG)
            System.out.printf("Calculating elevation at angle %f | Beam: (%f, %f, %f) elevationOfBeam: %f | Target: (%f, %f, %f) elevationOfTarget: %f | Elevation: %f | Distance: %f%n", 
                teta, beam.getX(), beam.getY(), beam.getZ(), elevationOfBeam, targetPosition.getX(),
                targetPosition.getY(), targetPosition.getZ(), elevationOfTarget, elevation, distance);
        
        return elevation;
    }
    
    public CircularMotion getCircularMotion() { return circularMotion; }
    
    @Override
    protected double getTotalGainAtTime(double t, double t2)
    {
        return 0;
    }

}
