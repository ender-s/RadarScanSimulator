package radarscangraph;

public class Cone {
    
    private double h, r, squintAngle;
    
    public Cone(double h, double squintAngle)
    {
        this.h = h;
        this.squintAngle = new Angle(squintAngle).getAngle();
        this.r = getRadiusAtDistance(h);
    }
    
    public double getHeight() { return h; }
    public void setHeight(double h) { this.h = h; this.r = getRadiusAtDistance(h); }
    
    public double getRadius() { return r; }
    
    public double getSquintAngle() { return squintAngle; }
    public void setSquintAngle(double squintAngle) { this.squintAngle = squintAngle; this.r = getRadiusAtDistance(h); }
    
    private double getRadiusAtDistance(double x)
    {
        double beta = 90 - squintAngle;
        return (Math.sin(Math.toRadians(squintAngle)) * x)/Math.sin(Math.toRadians(beta)); // applying sinus theorem
    }
    
}
