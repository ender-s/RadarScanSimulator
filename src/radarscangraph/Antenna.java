package radarscangraph;

public class Antenna 
{
    
    private double transmittingPower, transmittingPowerWatts, gain, azimuthWidth, elevationWidth, maxGain, frequency;
    
    public Antenna(double transmittingPower, double gain,
                   double azimuthWidth, double elevationWidth, double maxGain,
                   double frequency)
    {
        this.transmittingPower = transmittingPower;
        calculateTransmittingPowerWatts();
        this.gain = gain;
        this.azimuthWidth = azimuthWidth;
        this.elevationWidth = elevationWidth;
        this.maxGain = maxGain;
        this.frequency = frequency;
    }
    
    private void calculateTransmittingPowerWatts()
    {
        transmittingPowerWatts = (1 * Math.pow(10, transmittingPower / 10)) / 1000;
    }
    
    public double getTransmittingPower() { return transmittingPower; }
    public double getTransmittingPowerWatts() { return transmittingPowerWatts; }
    public void setTransmittingPower(double transmittingPower) 
    {
        this.transmittingPower = transmittingPower; 
        calculateTransmittingPowerWatts();
    }
    
    public double getGain() { return gain; }
    public void setGain(double gain) { this.gain = gain; }
    
    public double getAzimuthWidth() { return azimuthWidth; }
    public void setAzimuthWidth(double azimuthWidth) { this.azimuthWidth = azimuthWidth; }
    
    public double getElevationWidth() { return elevationWidth; }
    public void setElevationWidth(double elevationWidth) { this.elevationWidth = elevationWidth; }
    
    public double getMaxGain() { return maxGain; }
    public void setMaxGain(double maxGain) { this.maxGain = maxGain; }
    
    public double getPathLossForDistanceInMeters(double distance)
    {
        return 20 * Math.log10(distance / 1000) + 20 * Math.log10(frequency) + 32.44 - transmittingPower;
    }
    
}
