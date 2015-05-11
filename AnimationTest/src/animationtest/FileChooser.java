package animationtest;

import java.io.File;
import javax.swing.JFileChooser;

public class FileChooser {
    public static String fileChooser(String title,String type,String toolTip){
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle(title);
        chooser.setApproveButtonText(type);
        chooser.setApproveButtonToolTipText(toolTip);
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        
        return(f.getAbsolutePath());
    }
}
