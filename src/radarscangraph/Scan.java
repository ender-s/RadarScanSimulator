package radarscangraph;

import java.util.Map;
import windows.ScanGraphWindow;

public abstract class Scan 
{
    
    protected final ScanGraphWindow windowReference;
    protected final ScanType scanType;
    protected final Antenna antenna;
    protected Target target;
    protected double step;

    
    protected double[] azimuthHeader, elevationHeader;
    protected double[][] M;
    
    protected double rfPathLoss;
    protected final double PI = Math.PI;
    protected int numberOfMotionRepetition;
    protected double timeIncreaseStep;
    
    
    // target movement
    protected final boolean targetMovement;
    protected boolean movementDirectionOfTarget;
    // true: clockwise, right, or upwards
    // false: anticlockwise, left, or downwards
    protected CircularMotion circularMotionOfTarget;
    
    public Scan(ScanType scanType, ScanGraphWindow windowReference, Antenna antenna, double step,
            int numberOfMotionRepetition, double timeIncreaseStep, boolean targetMovement)
    {
        this.windowReference = windowReference;
        this.scanType = scanType;
        this.antenna = antenna;
        this.step = step;
        this.numberOfMotionRepetition = numberOfMotionRepetition;
        this.timeIncreaseStep = timeIncreaseStep;
        this.targetMovement = targetMovement;
        
        setUpMatrix();
        
        
        if (HelperMethods.PRINT_MATRIX)
        {
            System.out.println("The matrix is:");
            printMatrix(M, azimuthHeader, elevationHeader);
        }
    }
    
    public void setTarget(Target target)
    {
        this.target = target;
    }
    
    public Target getTarget() { return target; }

    public abstract void setPositionOfTarget();

    private void printMatrix(double[][] matrix, double[] rowHeader, double[] columnHeader)
    {
        System.out.print("      ");
        for (int i = 0; i < columnHeader.length; ++i)
        {
            System.out.printf("%6.2f", columnHeader[i]);
        }
        System.out.println();
        
        int n = matrix.length;
        int m = matrix[0].length;
        
        for (int i = 0; i < n; ++i)
        {
            System.out.printf("%6.2f", rowHeader[i]);
            for (int j = 0; j < m; ++j)
                System.out.printf("%6.2f", matrix[i][j]);
            System.out.println();
        }
        
    }
    
    private void setUpMatrix()
    {
        double azimuthToLimit = antenna.getAzimuthWidth() / 2;
        double elevationToLimit = antenna.getElevationWidth() / 2;
        int n = (int) Math.floor(azimuthToLimit / step) + 1;
        int m = (int) Math.floor(elevationToLimit / step) + 1;
        
        
        azimuthHeader = new double[n];
        elevationHeader = new double[m];
        
        M = new double[n][m];
        
        fillHeaders();
        fillAntennaGainValues();
    }
    
    private void fillAntennaGainValues()
    {
        
        int n = azimuthHeader.length;
        int m = elevationHeader.length;
        
        int i = 0, j = 0;
        
        double initial = antenna.getMaxGain();
        
        M[i][j] = initial;
        
        while (i < n && j < m)
        {
            if (!(i == 0 && j == 0)) M[i][j] = M[i - 1][j] - (M[i - 1][j] / (n - i + 1));
            
            double ySubtractionFactor = M[i][j] / (n - i);
            double xSubtractionFactor = M[i][j] / (m - j);
            
            if (n >= m)
            {
                for (int y = i + 1; y < n; ++y) M[y][j] = M[y - 1][j] - ySubtractionFactor;
                for (int x = j + 1; x < m; ++x) M[i][x] = M[x][i];
            }
            
            else
            {
                for (int x = j + 1; x < m; ++x) M[i][x] = M[i][x - 1] - xSubtractionFactor;
                for (int y = i + 1; y < n; ++y) M[y][j] = M[j][y];
            }
                
            ++i;
            ++j;
        }

    }
    
    
    private void fillHeaders()
    {
        int n = azimuthHeader.length;
        int m = elevationHeader.length;
        
        double val = 0.0;
        for (int i = 0; i < n; ++i)
        {
            azimuthHeader[i] = val;
            val += step;
        }
        
        val = 0.0;
        
        for (int i = 0; i < m; ++i)
        {
            elevationHeader[i] = val;
            val += step;
                    
        }
    }
    
    protected double calculateRFPathLoss()
    {
        return antenna.getPathLossForDistanceInMeters(target.getRange());
    }
    
    protected int getClosestIndex(double val, double[] list)
    {
        int closestIndex = 0;
        for (int i = 1; i < list.length; ++i)
            if (Math.abs(val - list[i]) < Math.abs(val - list[closestIndex]))
                closestIndex = i;
        
        return closestIndex;
        
    }
    
    protected int getClosestIndex(Double val, Double[] list)
    {
        int closestIndex = 0;
        for (int i = 1; i < list.length; ++i)
            if (Math.abs(val - list[i]) < Math.abs(val - list[closestIndex]))
                closestIndex = i;
        
        return closestIndex;
        
    }
    
    protected double getPowerDensityAtTargetAtTime(double t)
    {        
        double totalGain = getTotalGainAtTime(t);
        if (HelperMethods.DEBUG)
            System.out.printf("t = %f | Total gain = %f%n", t, totalGain);
        if (totalGain == 0)
        {
            return 0;
        }
        
        double result = ((antenna.getTransmittingPower() * totalGain) / (4 * PI * Math.pow(target.getRange(), 2))) - rfPathLoss;
        return result;
    }
    
    protected double getPowerDensityAtTargetAtTime(double t, double t2)
    {
        double totalGain = getTotalGainAtTime(t, t2);
        
        if (HelperMethods.DEBUG)
            System.out.printf("t = %f | Total gain = %f%n", t, totalGain);
        
        if (totalGain == 0)
            return 0;
        
        double result = ((antenna.getTransmittingPower() * totalGain) / (4 * PI * Math.pow(target.getRange(), 2))) - rfPathLoss;
        return result;
    }
    
    public void setNumberOfMotionRepetition(int numberOfMotionRepetition)
    {
        this.numberOfMotionRepetition = numberOfMotionRepetition;
    }
    
    protected abstract double getTotalGainAtTime(double t);
    protected abstract double getTotalGainAtTime(double t, double t2);
    
    public abstract boolean fetchValues();
    
    public abstract void fillPowerMap(Map<Double, Double> powerMap);
}
