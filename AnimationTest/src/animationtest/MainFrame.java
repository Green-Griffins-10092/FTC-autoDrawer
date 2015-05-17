
package animationtest;

import java.awt.Cursor;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class MainFrame extends JFrame {
    public MainFrame(){
        add(new FTCauto.MainGraphicsPanel());
        
    }
    
    
    
    
    public static void main(String[] args) {
        
        MainFrame frame = new MainFrame();
        frame.setTitle("AutoDrawer");
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);
        frame.setJMenuBar(MenuBars.menuBars());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setCursor(Cursor.CROSSHAIR_CURSOR);
        frame.setVisible(true);
    }
}
