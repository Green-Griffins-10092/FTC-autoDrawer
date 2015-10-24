package animationtest;

import java.io.File;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by David on 6/15/2015.
 * Designed to hold info about a robot for a export.
 */

public class ProgramInfo {
    public double wheelDiameter;
    public double distanceBetweenWheels;
    public double gearRatio;
    private List<ItemData> servos;
    private List<ItemData> motors;
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

    public ProgramInfo()
    {
    }

    public double getGearRatio() {
        return gearRatio;
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

    public void clearMotors()
    {
        motors = new ArrayList<>();
    }

    public ItemData[] getDriveMotors() {
        int length = 0;
        for (ItemData motor : motors) {
            if (motor.isDriveMotor())
                length++;
        }
        ItemData[] rtn = new ItemData[length];
        if (length != 0) {

            int index = 0;
            for (ItemData motor : motors) {
                if (motor.isDriveMotor()) {
                    rtn[index] = motor;
                    index++;
                }
            }
        } else {
            rtn = (ItemData[]) motors.toArray();
        }
        return rtn;
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
        private boolean driveMotor;
        
        protected ItemData(String programName, String controllerName) {
            this.programName = programName;
            this.controllerName = controllerName;
            reversed = false;
            driveMotor = false;
        }

        public ItemData(String programName, String controllerName, boolean reversed, boolean driveMotor) {
            this.programName = programName;
            this.controllerName = controllerName;
            this.reversed = reversed;
            this.driveMotor = driveMotor;
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

        public boolean isDriveMotor() {
            return driveMotor;
        }
    }
}
