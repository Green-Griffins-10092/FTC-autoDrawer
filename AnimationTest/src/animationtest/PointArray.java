package animationtest;

import java.util.ArrayList;


public class PointArray extends ArrayList<Point> implements Cloneable{


    public void addPoint(int x, int y){
        super.add(new Point(x, y));
    }

    public double getAngle(int i){
        ///Lots of trig stuff :P
        double x1 = super.get(i).getX();
        double y1 = super.get(i).getY();
        double x2 = super.get(i+1).getX();
        double y2 = super.get(i+1).getY();
        double x3 = super.get(i-1).getX();
        double y3 = super.get(i-1).getY();
        
        double u1 = x3-x1;
        double u2 = y3-y1;
        double v1 = x1-x2;
        double v2 = y1-y2;
        
        double dotProduct = u1*v1+u2*v2;
        double uLength = Math.sqrt(Math.pow(u1, 2)+Math.pow(u2, 2));
        double vLength = Math.sqrt(Math.pow(v1, 2)+Math.pow(v2, 2));
        
        double angle = Math.toDegrees(Math.acos(dotProduct/(uLength*vLength)));
        
        return(angle);
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
