package animationtest;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;
import static java.lang.Math.cos;

public class PointArray implements Cloneable {

    //The point that is selected
    //(will default to the last point placed, so we will not have
    //errors with it trying to look at a non existent point)
    //-1 represents no selected point
    public int selectedPoint = -1;
    private List<Point> list;
    
    public PointArray() {
        list = new ArrayList<Point>();
    }
    
    public void addPoint(int index, int x, int y, double conversionFactor) {
        list.add(index, new Point(x, y, conversionFactor));
    }

    public double getDistance(int index) {
        double x1 = list.get(index).x;
        double x2 = list.get(index + 1).x;
        double y1 = list.get(index).y;
        double y2 = list.get(index + 1).y;
        return sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    public double getAngle(int i) {
        ///Lots of trig stuff :P
        double x1 = list.get(i - 1).x;
        double y1 = list.get(i - 1).y;
        double x2 = list.get(i).x;
        double y2 = list.get(i).y;
        double x3 = list.get(i + 1).x;
        double y3 = list.get(i + 1).y;

        double u1 = x2 - x1;
        double u2 = y2 - y1;
        double v1 = x3 - x2;
        double v2 = y3 - y2;

        double dotProduct = u1 * v1 + u2 * v2;
        double uNorm = sqrt(u1 * u1 + u2 * u2);
        double vNorm = sqrt(v1 * v1 + v2 * v2);

        double angle = acos(dotProduct / (uNorm * vNorm));

        //rotation test
        //1. take vector v and scale it so the norms of u and v are the same.
        v1 *= uNorm / vNorm;
        v2 *= uNorm / vNorm;
        //2. rotate counterclockwise by variable angle
        double vPrime1 = v1 * cos(angle) - v2 * sin(angle);
        double vPrime2 = v2 * cos(angle) + v1 * sin(angle);
        //3. check if v' is the same as u,
        //  if so we rotated clockwise and don't need to change angle,
        //  otherwise we rotated counterclockwise and need to negate angle.
        //(the tests are stated in this manner to avoid round off error)
        if (abs(vPrime1 - u1) <= .000001 && abs(vPrime2 - u2) <= .000001) {
            angle = -angle;
        }

        return toDegrees(angle);

    }

    @Override
    public PointArray clone() {
        PointArray v = new PointArray();
        v.selectedPoint = this.selectedPoint;
        for (Point p : this.list) {
            v.add(p.clone());
        }

        return v;
    }

    protected void add(Point point) {
        list.add(point);
    }

    public int size() {
        return list.size();
    }

    public Point get(int i) {
        return list.get(i);
    }

    public void remove(int i) {
        list.remove(i);
    }

    public void clear() {
        list.clear();
    }

    public List<Point> getList() {
        return list;
    }
}
