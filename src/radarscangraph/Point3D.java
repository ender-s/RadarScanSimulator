package radarscangraph;

public class Point3D 
{
    
    private double x, y, z;
    
    public Point3D()
    {
        x = 0;
        y = 0;
        z = 0;
    }
    
    public Point3D(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public double getX() { return x; }
    public void setX(double x) { this.x = x; }
    
    public double getY() { return y; }
    public void setY(double y) {this.y = y; }
    
    public double getZ() { return z; }
    public void setZ(double z) { this.z = z; }
    
    public double getDistanceTo(Point3D point)
    {
        double x1 = point.getX();
        double y1 = point.getY();
        double z1 = point.getZ();
        return Math.sqrt(Math.pow(x - x1, 2) + Math.pow(y - y1, 2) + Math.pow(z - z1, 2));
    }
    
    @Override
    public String toString()
    {
        return String.format("Point3D [X = %f, Y = %f, Z = %f]", x, y, z);
    }
    
    public static Point3D getOrigin()
    {
        return new Point3D(0, 0, 0);
    }
    
}
