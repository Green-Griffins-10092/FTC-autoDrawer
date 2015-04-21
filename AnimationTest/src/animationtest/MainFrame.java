

package animationtest;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setCursor(Cursor.CROSSHAIR_CURSOR);
        frame.setVisible(true);

        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                PointArray.addPoint(e.getX(), e.getY());
                
                
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }
}
