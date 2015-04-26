package animationtest;

import javax.swing.JFileChooser;

public class FileChooser {
    public static String fileChooser(String type){
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        
        return(chooser.getName(null));
    }
}
