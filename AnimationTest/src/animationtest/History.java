package animationtest;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by David on 5/3/2015.
 * To hold things related to history
 */
public class History {
    private static List<PointArray> historyList = new LinkedList<PointArray>();

    private static int currentVersion = 0;


    public static void addVersion(PointArray version) {
        if(currentVersion == historyList.size() - 1) {
            //add the version
            historyList.add((PointArray) version.clone());
            //then set the current version to be the added version
            currentVersion = historyList.size() - 1;
        }
    }
}
