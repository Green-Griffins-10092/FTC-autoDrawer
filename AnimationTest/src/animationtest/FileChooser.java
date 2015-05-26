package animationtest;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooser {
    private static JFileChooser chooser = new JFileChooser();

    public static File fileChooser(String title, String type, String toolTip) {
        chooser.setDialogTitle(title);
        chooser.setApproveButtonText(type);
        chooser.setApproveButtonToolTipText(toolTip);
        chooser.setAcceptAllFileFilterUsed(false);
        FileFilter filter = new FileNameExtensionFilter("autoDrawer File", "tAD", "bAD");
        chooser.addChoosableFileFilter(filter);
        int n = chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();

        Settings.lastSavePath = f;

        return f;

    }
}
