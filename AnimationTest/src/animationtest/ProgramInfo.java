package animationtest;

import java.io.File;
import java.util.List;

/**
 * Created by David on 6/15/2015.
 * Designed to hold info about a robot for a export.
 */
public class ProgramInfo {
    private List<ItemData> servos;
    private List<ItemData> motors;

    private double wheelDiameter;
    private double distanceBetweenWheels;

    private String programName;

    private PointArray pointArray;
    private File saveLocation;

    public ProgramInfo(PointArray points, File file, String programName, List<ItemData> servos, List<ItemData> motors) {
        pointArray = points;
        saveLocation = file;
        this.servos = servos;
        this.motors = motors;
        this.programName = programName;
    }

    public PointArray getPointArray() {
        return pointArray;
    }

    public File getSaveLocation() {
        return saveLocation;
    }

    public List<ItemData> getServos() {
        return servos;
    }

    public List<ItemData> getMotors() {
        return motors;
    }

    public double getWheelDiameter() {
        return wheelDiameter;
    }

    public double getDistanceBetweenWheels() {
        return distanceBetweenWheels;
    }

    public String getProgramName() {
        return programName;
    }

    protected static class ItemData {
        private String programName;
        private String controllerName;
        private boolean reversed;

        protected ItemData(String programName, String controllerName) {
            this.programName = programName;
            this.controllerName = controllerName;
            reversed = false;
        }

        public ItemData(String programName, String controllerName, boolean reversed) {
            this.programName = programName;
            this.controllerName = controllerName;
            this.reversed = reversed;
        }

        public boolean isReversed() {
            return reversed;
        }

        public String getProgramName() {
            return programName;
        }

        public String getControllerName() {
            return controllerName;
        }
    }
}
