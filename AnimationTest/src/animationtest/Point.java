package animationtest;

import java.io.Serializable;

import static animationtest.FTCauto.MainGraphicsPanel.FIELD_X_OFFSET;
import static animationtest.FTCauto.MainGraphicsPanel.FIELD_Y_OFFSET;

public class Point implements Cloneable, Serializable {
    public double x;
    public double y;
    public int size = 100;
    public int sizeSpeed = 2;
    public int transparency = 1;
    public String extraCode = "";
    private int speed = 50;

    public Point(int x, int y, double conversionFactor) {
        setX(x, conversionFactor);
        setY(y, conversionFactor);
    }

    Point(double x, double y, String code, int speed) {
        this.x = x;
        this.y = y;
        extraCode = code;
        this.speed = speed;
    }

    public double getX(double conversionFactor) {
        return (x / conversionFactor);
    }

    public void setX(int x, double conversionFactor) {
        this.x = (x - FIELD_X_OFFSET) * conversionFactor;
    }

    public double getY(double conversionFactor) {
        return (y / conversionFactor);
    }

    public void setY(int y, double conversionFactor) {
        this.y = (y - FIELD_Y_OFFSET) * conversionFactor;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }


    public double getDistance(Point p) {
        double x1 = this.x;
        double x2 = p.x;
        double y1 = this.y;
        double y2 = p.y;

        double x = x1 - x2;
        double y = y1 - y2;

        x = x * x;
        y = y * y;

        return Math.sqrt(x + y);
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
