package animationtest;

import java.util.ArrayList;
import java.util.List;


public class PointArray {
    public List<Point> path = new ArrayList<Point>();
    
    
    public void addPoint(int x, int y){
        path.add(new Point(x, y));
    }

    public List<Point> getPath() {
        return path;
    }
    
    public double getAngle(int i){
        double x1 = path.get(i).getX();
        double y1 = path.get(i).getY();
        double x2 = path.get(i+1).getX();
        double y2 = path.get(i+1).getY();
        double x3 = path.get(i-1).getX();
        double y3 = path.get(i-1).getY();
        
        double a1 = Math.toDegrees(Math.atan2(x2-y1, x2-y1));
        //double a2 = Math.toDegrees(Math.atan2(x3-x1, y3-y1));
        
        return(a1);
    }
}
