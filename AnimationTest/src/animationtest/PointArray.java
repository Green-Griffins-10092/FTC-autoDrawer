package animationtest;

import java.util.ArrayList;


public class PointArray extends ArrayList<Point> implements Cloneable {

    //The point that is selected
    //(will default to the last point placed, so we will not have
    //errors with it trying to look at a non existent point)
    //-1 represents no selected point
    public int selectedPoint = -1;

    public void addPoint(int index, int x, int y) {
        super.add(index, new Point(x, y));
    }

    public double getAngle(int i) {
        ///Lots of trig stuff :P
        double x1 = super.get(i).getX();
        double y1 = super.get(i).getY();
        double x2 = super.get(i + 1).getX();
        double y2 = super.get(i + 1).getY();
        double x3 = super.get(i - 1).getX();
        double y3 = super.get(i - 1).getY();

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
        double x1 = super.get(i).getX();
        double y1 = super.get(i).getY();
        double x2 = super.get(i + 1).getX();
        double y2 = super.get(i + 1).getY();
        double x3 = super.get(i - 1).getX();
        double y3 = super.get(i - 1).getY();

        x2 -= x1;
        y2 -= y1;

        double tmp = x2;
        x2 = y2;
        y2 = tmp;

        x2 += x1;
        y2 += y1;
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
        for (Point p : this) {
            try {
                v.add((Point) p.clone());
            } catch (CloneNotSupportedException ignored) {

            }
        }

        return v;
    }
}
