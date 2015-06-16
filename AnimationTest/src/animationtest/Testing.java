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
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }   
        
        MainFrame frame = new MainFrame();
        robotEditor = new RobotEditor.MainGraphicsPanel();
        frame.add(robotEditor);
        frame.pack();
        frame.setTitle("Robot Editor");
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
