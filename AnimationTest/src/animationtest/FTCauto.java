//Test test2

package animationtest;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Line2D;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.WindowConstants;

import static animationtest.PointArray.points;

public class FTCauto extends JFrame {
    
    public FTCauto() {
        add(new MainGraphicsPanel());
    }
    
    public static double fieldSize = 0;
    
    //!!Only for development version!!
    public static boolean showCredits = true;
    
    public static void main(String[] args) {
        
        
        for(int i = 0; i<20; i++){
            PointArray.addPoint((int) (Math.random() * 999), (int) (Math.random() * 999));
        }
        
        
        
        FTCauto frame = new FTCauto();
        frame.setTitle("AutoDrawer");
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setCursor(Cursor.CROSSHAIR_CURSOR);
        frame.setVisible(true);
        
        
    }
    
    public static class MainGraphicsPanel extends JPanel{
        
        
        //Variables
        public static int mouseX = 0;
        public static int mouseY = 0;
        
        private int openingTrans = 255;
        private int openingTextTrans = 255;
        
        private int frames =0;
        
        //Picture stuff
        private static final Image stuffedGriffins = new ImageIcon("AnimationTest\\STUFFED_GRIFFINS_FINAL_GRN.jpg").getImage();
        private static final Image field = new ImageIcon("AnimationTest\\Field.png").getImage();
        //private static final Image field_shadow = new ImageIcon("AnimationTest\\Field_Shadow.png").getImage();
        
        
        public MainGraphicsPanel(){
            
            
            //Getting mouse location when moved
            addMouseMotionListener(new MouseMotionAdapter(){
                
                
                public void mouseMoved(MouseEvent e){
                    mouseX = e.getX();
                    mouseY = e.getY();
                    
                }
            });
            
            
            //The animation timer
            Timer timer = new Timer(10, new TimerListener());
            timer.start();
            
        }
        
        
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            
            
            
            Graphics2D g2 = (Graphics2D) g;
            
            //Main program
            Color darkBlueD = new Color(45,62,78);
            Color darkBlueL = new Color(52,74,97);
            Color lightGreenD = new Color(23,160,134);
            Color lightGreenL = new Color(27,188,155);


            //Main frame
            g.setColor(darkBlueL);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(darkBlueD);
            g.fillRect(0, 0, 110, getHeight());

            g.setColor(lightGreenD);
            g.fillRect(0, 0, 100, getHeight());
            
            g.setColor(lightGreenL);
            g.fillRect(0, 0, 5, getHeight());
            
            g.fillRect(0, 0, 100, 5);
            
            
            

            //Resizing the field
            if(getWidth()-100<getHeight()){
                fieldSize = getWidth()-100;
            }else{
                fieldSize = getHeight()-10;
            }
            
            g.drawImage(field, 100, 10, (int)fieldSize, (int) fieldSize, null);
            
            //Draw the Points
            for(int i = 0; i < points.size(); i++){
                g.fillOval((int) points.get(i).getX()+100-5, (int) points.get(i).getY()+10-5, 10, 10);
                
                
                
                if(i>0){
                    g2.setStroke(new BasicStroke(5));
                    g2.draw(new Line2D.Float((float) points.get(i).getX()+100, (float) points.get(i).getY()+10, 
                            (float) points.get(i-1).getX()+100, (float) points.get(i-1).getY()+10));
                    
                    //g.drawLine((int) points.get(i).getX()+100, (int) points.get(i).getY()+10,
                      //      (int) points.get(i-1).getX()+100, (int) points.get(i-1).getY()+10);
                }
            }

            //Mouse stuff
            g2.setStroke(new BasicStroke(2));
            g.drawLine(mouseX, 0, mouseX, getHeight());
            g.drawLine(0, mouseY, getWidth(), mouseY);

            g.drawLine(mouseX+1, 0, mouseX+1, getHeight());
            g.drawLine(0, mouseY+1, getWidth(), mouseY+1);

            //----Shadow Testing (having a semi-transparent image cast "shadows" onto the field)----
            //g.setColor(lightGreenL);
            //g.fillRect(mouseX, mouseY, 100, 100);
            
            //Field shadow
            //g.drawImage(field_shadow, 100, 10, fieldSize, fieldSize,null);
            //----------------------
            
            //Opening credits & stuff
            
            if(showCredits){
                if(openingTrans>0&&frames>200){
                    openingTrans-=5;
                }
                
                if(frames>50 && openingTextTrans>0){
                    openingTextTrans-=5;
                }
                
                Color OpeningBackground = new Color(50,50,50,openingTrans);
                g.setColor(OpeningBackground);
                g.fillRect(0, 0, getWidth(), getHeight());
                
                if(frames<250){
                    g.drawImage(stuffedGriffins, (getWidth()/2)-374, (getHeight()/2)-149, 748, 299,null);
                }
                
                
                OpeningBackground = new Color(50,50,50,openingTextTrans);
                g.setColor(OpeningBackground);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
            
            frames++;
        }
        class TimerListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                repaint();
            }
        }
    }
    
}
