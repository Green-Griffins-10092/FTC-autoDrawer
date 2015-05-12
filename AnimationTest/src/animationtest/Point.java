package animationtest;

import static animationtest.FTCauto.fieldSize;

public class Point implements Cloneable {
    public double x;
    public double y;
    private int speed = 50;
    public int size = 100;
    public int sizeSpeed = 1;
    public int transparency = 1;
    public String extraCode = "";

    public Point(double x, double y) {
        this.x = (x - 100) / (fieldSize / 1000);
        this.y = (y - 10) / (fieldSize / 1000);
    }

    public void movePoint(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }

    public double getX() {
        return (x * (fieldSize / 1000));
    }

    public double getY() {
        return (y * (fieldSize / 1000));
    }

    public int getSpeed() {
        return speed;
    }

    public void setX(int x) {
        this.x = (x - 100) / (fieldSize / 1000);
    }

    public void setY(int y) {
        this.y = (y - 10) / (fieldSize / 1000);
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    
    
    public double getDistance(Point p) {
        double x1 = this.getX();
        double x2 = p.getX();
        double y1 = this.getY();
        double y2 = p.getY();

        double x = x1 - x2;
        double y = y1 - y2;

        x = x * x;
        y = y * y;

        double rtn = Math.sqrt(x + y);
        return rtn * FTCauto.MainGraphicsPanel.getInchesToPixels();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        try {
            @SuppressWarnings("unchecked")
            Point v = (Point) super.clone();
            v.x = this.x;
            v.y = this.y;
            v.speed = this.speed;
            v.extraCode = this.extraCode.substring(0);
            return v;
        } catch (CloneNotSupportedException e) {
            // this shouldn't happen, since we are Cloneable
            throw new InternalError();
        }
    }
}
