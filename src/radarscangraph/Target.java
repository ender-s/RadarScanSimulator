package radarscangraph;

public class Target 
{
    
    private Point3D position;
    private double positionAngle, range;
    
    public Target(double positionAngle, double range)
    {
        this.positionAngle = positionAngle;
        this.range = range;
    }
    
    public Point3D getPosition() { return position; }
    public void setPosition(Point3D position) { this.position = position; }
    
    public double getPositionAngle() { return positionAngle; }
    public void setPositionAngle(double positionAngle) { this.positionAngle = positionAngle; }
    
    public double getRange() { return range; }
    public void setRange(double range) { this.range = range; }

}
