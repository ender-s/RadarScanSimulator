package radarscangraph;

public class CircularMotion 
{
    // r: radius
    // T: period
    // f: frequency
    // V: linear velocity
    // w: angular velocity
    
    private double r, T, f, V, w, initialAngle;
    private final boolean direction;
    // true: clockwise
    // false: anticlockwise
    
    private final double PI = Math.PI;
    
    public CircularMotion(double r, double T, double initialAngle, boolean direction)
    {
        this.r = r;
        this.T = T;
        this.initialAngle = initialAngle;
        this.direction = direction;
        setFeatures(r, T);
        
    }
    
    private void setFeatures(double r, double T)
    {
        this.f = 1/T;
        this.V = (2*PI*r)/T;
        this.w = (2*PI)/T;
    }
    
    public double getRadius() { return r; }
    public void setRadius() { this.r = r; setFeatures(r, T); }
    
    public double getPeriod() { return T; }
    public void setPeriod(double T) { this.T = T; setFeatures(r, T); }
    
    public double getPositionAtTime(double t)
    {
        double distanceByInitialAngle = (2*PI*r) * (initialAngle/360);
        double result = (distanceByInitialAngle + ((2*PI*r*t)/T)) % (2*PI*r);
        if (result < 0) result += 2*PI*r;
        return result;
    }
    
    public double getAngleAtTime(double t)
    {
        double result = (360 * t) / T;
        if (direction)
            result = (initialAngle + result) % 360;
        else
            result = ((360 - result) + initialAngle) % 360;
                    
        if (result < 0) result += 360;
        return result;
    }
}
