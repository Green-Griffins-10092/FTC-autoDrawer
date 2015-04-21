
package animationtest;

import static animationtest.FTCauto.fieldSize;

public class Point {
    private double x = 10;
    private double y = 10;
    private int speed = 50;
    
    
    //public int getScale
    
    
    
    public double getX() {
        return x/fieldSize;
    }
    
    public double getY() {
        return y/fieldSize;
    }
    
    public int getSpeed() {
        return speed;
    }
    
    public void setX(int x) {
        this.x = x*fieldSize;
    }
    
    public void setY(int y) {
        this.y = y* FTCauto.MainGraphicsPanel.getWidth();
    }
    
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
}
