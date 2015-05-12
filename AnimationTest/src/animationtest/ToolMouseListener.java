package animationtest;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by david on 4/29/2015.
 * replaces the anonymous inner class in FTCauto.
 */
public class ToolMouseListener implements MouseListener{

    //The point that are selected
    //(will default to the last point placed, so we will not have
    //errors with it trying to look at a non existent point)
    //-1 represents no selected point
    public int selectedPoint = -1;
    
    //The type of tool to use, 0 represents no tool
    // Positive numbers represent tested tools, negative numbers represent tools being tested
    //1 - Add: This tool will add points to the field
    //2 - Delete: This tool will delete points you click on
    //(And perhaps if you drag delete multiple points in the selection)
    //3 - Edit: With this tool you can drag and select points
    //(Hopefully with a point selected you will be able to precisely change the x & y and the speed)
    //-1 - Get Distance: This tool prints the distance between the selected point and the clicked point
    public int toolType = 0;

    public History history = new History(FTCauto.points);

    public int pointDragging = -1;
    //This method checks if the coordinates represented by the parameters
    //is one of the points stored in List points.
    //returns the index of the point that was clicked, or -1 if no point was clicked.
    private int clickedPoint(int x, int y)
    {
        int clickedOn = -1;
        for (int i = 0; i < FTCauto.points.size(); i++) {
            if (Math.abs((FTCauto.points.get(i).getX() + 100) - x) < 20) {
                if (Math.abs((FTCauto.points.get(i).getY() + 10) - y) < 20) {
                    clickedOn = i;
                    break;
                }
            }
        }
        return clickedOn;
    }

    private void addPoint(int x, int y){
        // add point to list
        FTCauto.points.addPoint(x, y);
        //make new point the selected point
        selectedPoint = FTCauto.points.size() - 1;

        //add to history
        history.addVersion(FTCauto.points);
    }

    private void removePoint(int x, int y){
        int point = clickedPoint(x, y);
        if(point != -1)
        {
            FTCauto.points.remove(point);
            selectedPoint = -1;
            history.addVersion(FTCauto.points);
        }
    }

    private void movePoint(int index, int x, int y)
    {
        FTCauto.points.set(index, new Point(x, y));
        selectedPoint = index;
        history.addVersion(FTCauto.points);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //check if the mouse was clicked inside the field
        if (e.getX() > 100 && e.getX() < FTCauto.fieldSize + 100 && e.getY() > 10 && e.getY() < FTCauto.fieldSize + 10) {
            if(toolType == 1){
                // check if the button pressed is the left button
                if (e.getButton() == MouseEvent.BUTTON1) {
                    //add the point where clicked
                    addPoint(e.getX(), e.getY());
                }
            }else if(toolType == 2){
                removePoint(e.getX(), e.getY());
            }else if(toolType == 3)
            {
                int point = clickedPoint(e.getX(), e.getY());
                if(point == -1 && selectedPoint != -1) {
                    movePoint(selectedPoint, e.getX(), e.getY());
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
                    System.out.println("Distance between point " + selectedPoint + " and point " + point + " is \n"
                            + FTCauto.points.get(selectedPoint).getDistance(FTCauto.points.get(point)) + " inches");
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.isShiftDown() && pointDragging == -1 && toolType == 3)
        {
            pointDragging = clickedPoint(e.getX(), e.getY());
            
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (pointDragging != -1)
        {
            pointDragging = -1;
            history.addVersion(FTCauto.points);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


}
