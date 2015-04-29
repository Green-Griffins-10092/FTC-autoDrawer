package animationtest;

import java.util.ArrayList;
import java.util.List;


public class PointArray {
    public static List<Point> points = new ArrayList<Point>();
    
    
    public static void addPoint(int x, int y){
        points.add(new Point(x, y));
    }
}
