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
}
