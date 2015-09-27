package animationtest;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class FileChooser {
    private static JFileChooser chooser = new JFileChooser();

    public static File fileChooser(String title, String type, String toolTip, boolean fileFilter) {
        chooser.setDialogTitle(title);
        chooser.setApproveButtonText(type);
        chooser.setApproveButtonToolTipText(toolTip);
        if (fileFilter) {
            chooser.setAcceptAllFileFilterUsed(false);
            FileFilter filter = new FileNameExtensionFilter("autoDrawer File", "tAD", "bAD");
            chooser.addChoosableFileFilter(filter);
        } else {
            chooser.resetChoosableFileFilters();
            chooser.setAcceptAllFileFilterUsed(true);
        }
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();

        Settings.lastSavePath = f;

        return f;
    }
}
