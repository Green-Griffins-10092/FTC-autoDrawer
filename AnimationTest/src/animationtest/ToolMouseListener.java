package animationtest;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import animationtest.PointArray.*;

/**
 * Created by david on 4/29/2015.
 */
public class ToolMouseListener implements MouseListener{

    //The point that are selected
    //(will default to the last point placed, so we will not have
    //errors with it trying to look at a non existent point)
    //-1 represents no selected point
    public static int selectedPoint = -1;

    //The type of tool to use, 0 represents no tool
    // Positive numbers represent tested tools, negative numbers represent tools being tested
    //1 - Add: This tool will add points to the field
    //2 - Delete: This tool will delete points you click on
    //(And perhaps if you drag delete multiple points in the selection)
    //3 - Edit: With this tool you can drag and select points
    //(Hopefully with a point selected you will be able to precisely change the x & y and the speed)
    //-1 - Get Distance: This tool prints the distance between the selected point and the clicked point
    public static int toolType = 0;

    //copy of the reference of points
    public static List<Point> points = FTCauto.points;

    //This method checks if the coordinates represented by the parameters
    //is one of the points stored in List points.
    //returns the index of the point that was clicked, or -1 if no point was clicked.
    private int clickedPoint(int x, int y)
    {
        int clickedOn = -1;
        for (int i = 0; i < points.size(); i++) {
            if (Math.abs((points.get(i).getX() + 100) - x) < 20) {
                if (Math.abs((points.get(i).getY() + 10) - y) < 20) {
                    clickedOn = i;
                    break;
                }
            }
        }
        return clickedOn;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        //check if the mouse was clicked inside the field
        if (e.getX() > 100 && e.getX() < FTCauto.fieldSize + 100 && e.getY() > 10 && e.getY() < FTCauto.fieldSize + 10) {
            if(toolType == 1){
                // check if the button pressed is the left button
                if (e.getButton() == MouseEvent.BUTTON1) {
                    // add point to list
                    points.addPoint(mouseX, mouseY);
                    //make new point the selected point
                    selectedPoint = points.size() - 1;
                }
            }else if(toolType == 2){
                int point = clickedPoint(e.getX(), e.getY());
                if(point != -1)
                {
                    points.remove(point);
                    selectedPoint = -1;
                }
            }else if(toolType == 3)
            {
                int point = clickedPoint(e.getX(), e.getY());
                if(point == -1 && selectedPoint != -1) {
                    points.set(selectedPoint, new Point(e.getX(), e.getY()));
                }else if (point != -1)
                {
                    selectedPoint = point;
                }
            }else if (FTCauto.developing && toolType == -1)
            {
                int point = clickedPoint(e.getX(), e.getY());
                if (selectedPoint == -1)
                {
                    selectedPoint = point;
                }
                else if (point != -1)
                {
                    System.out.println("Distance between point " + selectedPoint + " and point " + point + " is \n" + points.get(selectedPoint).getDistance(points.get(point)) + " inches");
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {


    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


}
