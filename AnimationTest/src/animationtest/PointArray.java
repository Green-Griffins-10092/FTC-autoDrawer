package animationtest;

import java.util.ArrayList;
import java.util.List;


public class PointArray {
    public static List<Point> points = new ArrayList<Point>();
    
    
    public static void addPoint(int x, int y){
        points.add(new Point(x, y));
    }
    
    public static double getAngle(int i){
        double x1 = points.get(i).getX();
        double y1 = points.get(i).getY();
        double x2 = points.get(i+1).getX();
        double y2 = points.get(i+1).getY();
        double x3 = points.get(i-1).getX();
        double y3 = points.get(i-1).getY();
        
        double a1 = Math.toDegrees(Math.atan2(x2-y1, x2-y1));
        //double a2 = Math.toDegrees(Math.atan2(x3-x1, y3-y1));
        
        return(a1);
    }
}