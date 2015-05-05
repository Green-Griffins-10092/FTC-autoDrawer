package animationtest;

import java.util.ArrayList;


public class PointArray extends ArrayList<Point> implements Cloneable{


    public void addPoint(int x, int y){
        super.add(new Point(x, y));
    }

    public double getAngle(int i){
        double x1 = super.get(i).getX();
        double y1 = super.get(i).getY();
        double x2 = super.get(i+1).getX();
        double y2 = super.get(i+1).getY();
        double x3 = super.get(i-1).getX();
        double y3 = super.get(i-1).getY();

        double a1 = Math.toDegrees(Math.atan2(x1, y1));
        //double a2 = Math.toDegrees(Math.atan2(x3-x1, y3-y1));

        return(a1);
    }

    @Override
    public Object clone()
    {
        PointArray v = new PointArray();
        for (Point p:this)
        {
            try {
                v.add((Point) p.clone());
            }
            catch (CloneNotSupportedException e) {

            }

        }

        return v;
    }
}
