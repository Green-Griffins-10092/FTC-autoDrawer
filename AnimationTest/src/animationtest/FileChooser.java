package animationtest;

import java.io.File;
import javax.swing.JFileChooser;

public class FileChooser {
    public static String fileChooser(String type){
        JFileChooser chooser = new JFileChooser();
        chooser.setName("Save as:");
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        
        return(f.getAbsolutePath());
    }
}
