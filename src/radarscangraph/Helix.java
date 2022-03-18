package radarscangraph;

public class Helix 
{
    
    private double radius;
    private double pitch;
    private double b;
    
    public Helix(double radius, double pitch)
    {
        this.radius = radius;
        this.pitch = pitch;
        calculateB();
    }
    
    private void calculateB()
    {
        b = pitch / (2 * Math.PI);
    }
    
    public void setRadius(double radius)
    {
        this.radius = radius;
    }
    
    public double getRadius()
    {
        return radius;
    }
    
    public void setPitch(double pitch)
    {
        this.pitch = pitch;
        calculateB();
    }
    
    public double getPitch()
    {
        return pitch;
    }
    
    public Point3D getPositionAtTime(double t)
    {
        return new Point3D(getXCoordinateAtTime(t), getYCoordinateAtTime(t), getZCoordinateAtTime(t));
    }
    
    public double getXCoordinateAtTime(double t)
    {
        return radius * Math.cos(t);
    }
    
    public double getYCoordinateAtTime(double t)
    {
        return radius * Math.sin(t);
    }
    
    public double getZCoordinateAtTime(double t)
    {
        return b * t;
    }   
}
