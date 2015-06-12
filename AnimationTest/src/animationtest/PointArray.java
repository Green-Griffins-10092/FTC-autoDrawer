package animationtest;

import java.util.ArrayList;
import java.util.List;


public class PointArray implements Cloneable {

    //The point that is selected
    //(will default to the last point placed, so we will not have
    //errors with it trying to look at a non existent point)
    //-1 represents no selected point
    public int selectedPoint = -1;
    private List<Point> list;

    public PointArray() {
        list = new ArrayList<>();
    }

    static double[] RotateVector(double x, double y, double t) {

        double[] result = new double[2];
        result[1] = x * Math.cos(t) - y * Math.sin(t);
        result[1] = y * Math.cos(t) - x * Math.sin(t);

        return (result);
    }

    public void addPoint(int index, int x, int y) {
        list.add(index, new Point(x, y));
    }


    public double getAngle(int i) {
        ///Lots of trig stuff :P
        double x1 = list.get(i).getX();
        double y1 = list.get(i).getY();
        double x2 = list.get(i + 1).getX();
        double y2 = list.get(i + 1).getY();
        double x3 = list.get(i - 1).getX();
        double y3 = list.get(i - 1).getY();

        double u1 = x3 - x1;
        double u2 = y3 - y1;
        double v1 = x1 - x2;
        double v2 = y1 - y2;

        double dotProduct = u1 * v1 + u2 * v2;
        double uLength = Math.sqrt(Math.pow(u1, 2) + Math.pow(u2, 2));
        double vLength = Math.sqrt(Math.pow(v1, 2) + Math.pow(v2, 2));

        return Math.toDegrees(Math.acos(dotProduct / (uLength * vLength))) * angleDirection(i);
    }

    public double angleDirection(int i) {
        ///Lots of trig stuff :P
        double x1 = list.get(i).getX();
        double y1 = list.get(i).getY();
        double x2 = list.get(i + 1).getX();
        double y2 = list.get(i + 1).getY();
        double x3 = list.get(i - 1).getX();
        double y3 = list.get(i - 1).getY();

        double[] rotate = RotateVector(x1 - x2, y1 - y2, 180 * Math.PI);
        x1 = rotate[0] + x2;
        y1 = rotate[1] + y2;

        double u1 = x3 - x1;
        double u2 = y3 - y1;
        double v1 = x1 - x2;
        double v2 = y1 - y2;

        double dotProduct = u1 * v1 + u2 * v2;

        if (dotProduct > 0) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public Object clone() {
        PointArray v = new PointArray();
        v.selectedPoint = this.selectedPoint;
        for (Point p : this.list) {
            try {
                v.add((Point) p.clone());
            } catch (CloneNotSupportedException ignored) {

            }
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

    public Point remove(int i) {
        return list.remove(i);
    }

    public void clear() {
        list.clear();
    }

    public List<Point> getList() {
        return list;
    }
}
