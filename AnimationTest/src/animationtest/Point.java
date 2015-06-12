package animationtest;

import java.io.Serializable;

import static animationtest.FTCauto.MainGraphicsPanel.FIELD_X_OFFSET;
import static animationtest.FTCauto.MainGraphicsPanel.FIELD_Y_OFFSET;
import static animationtest.MainFrame.auto;

public class Point implements Cloneable, Serializable {
    public double x;
    public double y;
    public int size = 100;
    public int sizeSpeed = 1;
    public int transparency = 1;
    public String extraCode = "";
    private int speed = 50;

    public Point(int x, int y) {
        setX(x);
        setY(y);
    }

    Point(double x, double y, String code, int speed) {
        this.x = x;
        this.y = y;
        extraCode = code;
        this.speed = speed;
    }

    public void movePoint(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }

    public double getX() {
        return (x * (auto.fieldSize / 1000));
    }

    public void setX(int x) {
        this.x = (x - FIELD_X_OFFSET) / (auto.fieldSize / 1000);
    }

    public double getY() {
        return (y * (auto.fieldSize / 1000));
    }

    public void setY(int y) {
        this.y = (y - FIELD_Y_OFFSET) / (auto.fieldSize / 1000);
    }

    public int getSpeed() {
        return speed;
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
        return rtn * auto.getInchesToPixels();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        try {
            @SuppressWarnings("unchecked")
            Point v = (Point) super.clone();
            v.x = this.x;
            v.y = this.y;
            v.speed = this.speed;
            v.extraCode = this.extraCode;
            return v;
        } catch (CloneNotSupportedException e) {
            // this shouldn't happen, since we are Cloneable
            throw new InternalError();
        }
    }

    @Override
    public String toString() {
        return "x:" + x + " y:" + y
                + " \"" + extraCode + "\"," + " [" + speed + "]";
    }
}
