
package animationtest;

import static animationtest.FTCauto.fieldSize;

public class Point {
    private double x = 10;
    private double y = 10;
    private int speed = 50;
    public int size = 100;
    public int sizeSpeed = 1;
    public int transparency = 1;
    
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
        this.x = (x-100)  /( fieldSize/1000);
    }
    
    public void setY(int y) {
        this.y = (y-10) /( fieldSize/1000);
    }
    
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
}
