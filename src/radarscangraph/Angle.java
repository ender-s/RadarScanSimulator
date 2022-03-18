package radarscangraph;

public class Angle 
{
    
    private double angle;
    private int quadrant;
    public Angle(double angle) { this.angle = scaleAngle(angle); setQuadrant(); }
    
    public double getAngle() { return angle; }
    public void setAngle(double angle) { this.angle = scaleAngle(angle); setQuadrant(); }
    
    private double scaleAngle(double angle)
    {
        if (angle >= 0) angle -= 360 * ((int)angle / 360);
        else angle += 360 * (((int)(-1 * angle) / 360) + 1);
        
        if (angle == 360) angle = 0;
       
        return angle;
    }
    
    private void setQuadrant()
    {
        if (angle >= 0 && angle < 90) this.quadrant = 1;
        else if (angle < 180) this.quadrant = 2;
        else if (angle < 270) this.quadrant = 3;
        else this.quadrant = 4;
            
    }
    
    public int getQuadrant() { return quadrant; }
    
}
