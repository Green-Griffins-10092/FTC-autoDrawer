package animationtest;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

public class Testing extends JFrame {
    public static RobotEditor.MainGraphicsPanel robotEditor;
    
    public static void main(String[] args) {
        
        //!!------This is only for testing, and will not be in the final build------!!\\
        
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
            } catch (Exception e) {
                // If Nimbus is not available, fall back to cross-platform
             try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception ex) {

            }
        } 
        
        MainFrame frame = new MainFrame();
        
        //frame.add();
        frame.setTitle("Robot Editor");
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
