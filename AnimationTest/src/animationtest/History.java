package animationtest;

import java.util.LinkedList;

/**
 * Created by David on 5/3/2015.
 * To hold things related to history
 */
public class History {

    private LinkedList<PointArray> historyList = new LinkedList<PointArray>();

    private int currentVersion = 0;

    //default constructor
    public History() {
    }

    //constructor that adds the passed PointArray to the historyList
    public History(PointArray p) {
        addVersion(p);
    }

    //add a clone of the point to the list
    //if the current version is not the last item in the list,
    //then it uses recursion to remove every point after the point
    public void addVersion(PointArray version) {
        if (currentVersion >= (historyList.size() - 1)) {
            //add the version
            historyList.addLast((PointArray) version.clone());
            //then set the current version to be the added version
            currentVersion = historyList.size() - 1;
        } else {
            historyList.remove(historyList.size() - 1);
            addVersion(version);
        }
    }

    private PointArray getVersion(int i) {
        return (PointArray) historyList.get(i).clone();
    }

    //for getting the current version
    public PointArray getVersion() {
        return getVersion(currentVersion);
    }

    //basically is a undo method
    public PointArray getPreviousVersion() {
        if (currentVersion > 0) {
            currentVersion--;
        }
        return getVersion();
    }

    //basically a redo method
    public PointArray getNextVersion() {
        if (currentVersion < historyList.size() - 1) {
            currentVersion++;
        }
        return getVersion();
    }

    //returns the size of historyList
    public int size() {
        return historyList.size();
    }

    //returns the index of the current version
    public int getCurrentVersion() {
        return currentVersion;
    }
}
