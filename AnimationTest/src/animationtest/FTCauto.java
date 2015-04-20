//Test test2

package animationtest;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static animationtest.PointArray.*;


public class FTCauto extends JFrame {
    
    public FTCauto() {
        add(new MainGraphicsPanel());
    }
    
    public static double fieldSize = 0;
    
    //!!Only for develepment version!!
    public static boolean showCredits = false;
    
    public static void main(String[] args) {
        
        
        for(int i = 0; i<20; i++){
            PointArray.addPoint((int) (Math.random() * 999), (int) (Math.random() * 999));
        }
        
        
        
        FTCauto frame = new FTCauto();
        frame.setTitle("AutoDrawer");
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setCursor(Cursor.CROSSHAIR_CURSOR);
        frame.setVisible(true);
        
        
    }
    
    
    public static class MainGraphicsPanel extends JPanel{
        
        //Variables
        private int mouseX = 0;
        private int mouseY = 0;
        
        
        private int openingTrans = 255;
        private int openingTextTrans = 255;
        
        private int frames =0;
        
        //Picture stuff
        private static final Image stuffedGriffins = new ImageIcon("STUFFED_GRIFFINS_FINAL_GRN.png").getImage();
        private static final Image field = new ImageIcon("Field.png").getImage();
        private static final Image field_shadow = new ImageIcon("Field_Shadow.png").getImage();
        
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
            
            //Main program
            Color darkBlueD = new Color(45,62,78);
            Color darkBlueL = new Color(52,74,97);
            Color lightGreenD = new Color(23,160,134);
            Color lightGreenL = new Color(27,188,155);
            
            Color lightGreenLT = new Color(27,188,155,100);
            
            
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
            
            //Mouse stuff
            //g.drawLine(mouseX, 0, mouseX, getHeight());
            //g.drawLine(0, mouseY, getWidth(), mouseY);
            
            //g.drawLine(mouseX+1, 0, mouseX+1, getHeight());
            //g.drawLine(0, mouseY+1, getWidth(), mouseY+1);
            
            //Resizing the field
            if(getWidth()-100<getHeight()){
                fieldSize = getWidth()-100;
            }else{
                fieldSize = getHeight()-10;
            }
            
            //Field
            g.drawImage(field, 100, 10, (int)fieldSize, (int) fieldSize,null);
            
            //Draw the Points
            for(int i = 0; i < points.size(); i++){
                g.fillOval((int) points.get(i).getX()+100, (int) points.get(i).getY()+10, 10, 10);
            }
            
            
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
                }else if(openingTextTrans>0&&frames>100){

                }

                Color OpeningBackground = new Color(50,50,50,openingTrans);
                g.setColor(OpeningBackground);
                g.fillRect(0, 0, getWidth(), getHeight());

                if(frames<300){
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
