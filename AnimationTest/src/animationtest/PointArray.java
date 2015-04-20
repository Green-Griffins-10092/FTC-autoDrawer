
package animationtest;

import java.util.ArrayList;
import java.util.List;


public class PointArray {
    public static List<Point> points = new ArrayList<Point>();
    
    
    
    
    public static void addPoint(int x, int y){
        Point aPoint = new Point();
        aPoint.setX(x);
        aPoint.setY(y);
        
        
        points.add(aPoint);
    }
}
