package animationtest;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static animationtest.FTCauto.MainGraphicsPanel.FIELD_X_OFFSET;
import static animationtest.FTCauto.MainGraphicsPanel.FIELD_Y_OFFSET;

/**
 * Created by david on 4/29/2015.
 * replaces the anonymous inner class in FTCauto.
 */
public class ToolMouseListener implements MouseListener {

    //The type of tool to use, 0 represents no tool
    // Positive numbers represent tested tools, negative numbers represent tools being tested
    //1 - Add: This tool will add points to the field
    //2 - Delete: This tool will delete points you click on
    //(And perhaps if you drag delete multiple points in the selection)
    //3 - Edit: With this tool you can drag and select points
    //(Hopefully with a point selected you will be able to precisely change the x & y and the speed)
    //-1 - Get Distance: This tool prints the distance between the selected point and the clicked point
    public int toolType = 0;
    public int pointDragging = -1;
    public History history;
    private boolean developing;
    private FTCauto.MainGraphicsPanel autoPanel;

    public ToolMouseListener(FTCauto.MainGraphicsPanel autoPanel, boolean developing) {
        this.developing = developing;
        history = new History(autoPanel.points);
        this.autoPanel = autoPanel;
    }

    //This method checks if the coordinates represented by the parameters
    //is one of the points stored in List points.
    //returns the index of the point that was clicked, or -1 if no point was clicked.
    private int clickedPoint(int x, int y) {
        int clickedOn = -1;
        for (int i = 0; i < autoPanel.points.size(); i++) {
            if (Math.abs((autoPanel.points.get(i).getX(autoPanel.getInchesToPixels()) + FIELD_X_OFFSET) - x) < 10) {
                if (Math.abs((autoPanel.points.get(i).getY(autoPanel.getInchesToPixels()) + FIELD_Y_OFFSET) - y) < 10) {
                    clickedOn = i;
                    break;
                }
            }
        }
        return clickedOn;
    }

    private void addPoint(int index, int x, int y) {
        // add point to list
        autoPanel.points.addPoint(index + 1, x, y, autoPanel.getInchesToPixels());
        //make new point the selected point
        autoPanel.points.selectedPoint = index + 1;

        //add to history
        history.addVersion(autoPanel.points);
    }

    private void removePoint(int x, int y) {
        int point = clickedPoint(x, y);
        if (point != -1) {
            autoPanel.points.remove(point);
            autoPanel.points.selectedPoint = -1;
            history.addVersion(autoPanel.points);
        }
    }

    private void movePoint(int index, int x, int y) {
        autoPanel.points.get(index).setX(x, autoPanel.getInchesToPixels());
        autoPanel.points.get(index).setY(y, autoPanel.getInchesToPixels());
        autoPanel.points.selectedPoint = index;
        history.addVersion(autoPanel.points);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //check if the mouse was clicked inside the field
        if (e.getX() > FIELD_X_OFFSET && e.getX() < autoPanel.fieldSize + FIELD_X_OFFSET && e.getY() > FIELD_Y_OFFSET && e.getY() < autoPanel.fieldSize + FIELD_Y_OFFSET) {
            if (toolType == 1) {
                //add the point where clicked
                if (clickedPoint(e.getX(), e.getY()) == -1) {
                    addPoint(autoPanel.points.selectedPoint, e.getX(), e.getY());
                } else {
                    autoPanel.points.selectedPoint = clickedPoint(e.getX(), e.getY());
                }
            } else if (toolType == 2) {
                removePoint(e.getX(), e.getY());
            } else if (toolType == 3) {
                int point = clickedPoint(e.getX(), e.getY());
                if (point == -1 && autoPanel.points.selectedPoint != -1) {
                    movePoint(autoPanel.points.selectedPoint, e.getX(), e.getY());
                } else if (point != -1) {
                    autoPanel.points.selectedPoint = point;
                }
            } else if (developing && toolType == -1) {
                int point = clickedPoint(e.getX(), e.getY());
                if (autoPanel.points.selectedPoint == -1) {
                    autoPanel.points.selectedPoint = point;
                } else if (point != -1) {
                    System.out.println("Distance between point " + autoPanel.points.selectedPoint + " and point " + point + " is \n"
                            + autoPanel.points.get(autoPanel.points.selectedPoint).getDistance(autoPanel.points.get(point)) + " inches");
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if ((e.isShiftDown() || e.getButton() == MouseEvent.BUTTON3) && pointDragging == -1 && toolType == 3) {
            pointDragging = clickedPoint(e.getX(), e.getY());

        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (pointDragging != -1) {
            pointDragging = -1;
            history.addVersion(autoPanel.points);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


}
