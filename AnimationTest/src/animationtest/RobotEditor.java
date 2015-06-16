
package animationtest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Thomas
 */
public class RobotEditor extends JFrame{
    public RobotEditor() {
        add(new MainGraphicsPanel());
    }
    
    public static class MainGraphicsPanel extends JPanel {
        
        //Assets
        
        public int mouseX = 0;
        public int mouseY = 0;
        public MainGraphicsPanel() {
            
            
            //Getting mouse location when moved
            addMouseMotionListener(new MouseMotionAdapter() {
                
                
                public void mouseMoved(MouseEvent e) {
                    mouseX = e.getX();
                    mouseY = e.getY();

                }
            });
            addMouseMotionListener(new MouseMotionAdapter() {
                
                
                public void mouseDragged(MouseEvent e) {
                    mouseX = e.getX();
                    mouseY = e.getY();

                }
            });
            
            //The animation timer
            Timer timer = new Timer(10, new TimerListener());
            timer.start();
            
        }
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            
        }
        
        class TimerListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        }
        
    }
}
