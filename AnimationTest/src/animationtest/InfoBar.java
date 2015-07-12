package animationtest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class InfoBar extends JFrame {

    public InfoBar(boolean developing) {
        add(new MainGraphicsPanel(developing));
    }

    public static class MainGraphicsPanel extends JPanel {


        //Variables
        public int mouseX = 0;
        public int mouseY = 0;
        Color background = new Color(200, 200, 200);
        Color sidePanelDark = new Color(0, 0, 0, 50);
        Color sidePanel = new Color(0, 90, 33);
        Color sidePanelLight = new Color(200, 200, 200);
        private boolean developing;

        public MainGraphicsPanel(boolean developing) {


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

            setPreferredSize(new Dimension(140, 100));

            this.developing = developing;
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            //Main frame
            g.setColor(background);
            g.fillRect(0, 0, getWidth(), getHeight());

            g.setColor(sidePanel);
            g.fillRect(0, 0, 100, getHeight());


            g.setColor(sidePanelLight);
            g.fillRect(0, 0, 100, 10);


            //Menu Shadow
            g.setColor(sidePanelDark);
            //g.fillRect(90, 0, 10, getHeight());

            int[] shadowXPoints = {100, 130, 130, 100};
            int[] shadowYPoints = {10, 30, getHeight(), getHeight()};

            g.fillPolygon(shadowXPoints, shadowYPoints, 4);

        }


        class TimerListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        }
    }

}

