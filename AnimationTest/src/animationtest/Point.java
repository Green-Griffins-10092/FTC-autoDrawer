
package animationtest;

import static animationtest.FTCauto.fieldSize;

public class Point {
    private double x = 10;
    private double y = 10;
    private int speed = 50;
    
    
    //public int getScale
    
    
    
    public double getX() {
        return (x * (fieldSize/1000));
    }
    
    public double getY() {
        return (y *( fieldSize/1000));
    }
    
    public int getSpeed() {
        return speed;
    }
    
    public void setX(int x) {
        this.x = x  *( fieldSize/1000);
    }
    
    public void setY(int y) {
        this.y = y *( fieldSize/1000);
    }
    
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
}
