

//Test Test Test


package animationtest;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class FTCauto extends JFrame {
    
    public FTCauto() {
        add(new MovingMessagePanel("message moving"));
    }
    
    public static void main(String[] args) {
        
        System.out.println("hello world");
        FTCauto frame = new FTCauto();
        frame.setTitle("Animation test");
        frame.setSize(1246, 499);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        
    }

    static class MovingMessagePanel extends JPanel{
        
        //stuff
        private String message = "Welcome to Java";
        private int xCoordinate = 0;
        private int yCoordinate = 20;
        private int speed = 2;
        
        private int mouseX = 0;
        private int mouseY = 0;
        
        private int openingTrans = 255;
        private int openingTextTrans = 255;
        
        private int frames =0;
        
        public MovingMessagePanel(String message){
            this.message = message;
            
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
        
        //Picture stuff
        private static final Image stuffedGriffins = new ImageIcon("STUFFED_GRIFFINS_FINAL_GRN.png").getImage();
        private static final Image field = new ImageIcon("Field.png").getImage();
        private static final Image field_shadow = new ImageIcon("Field_Shadow.png").getImage();
        
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            
            if(xCoordinate>getWidth()+100){
                xCoordinate=-100;
            }
            
            xCoordinate+=speed;
            
            

            
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
            
            //Extra animation stuff
            g.setColor(lightGreenLT);
            g.fillRect(xCoordinate,0,100,10);
            
            //Mouse stuff
            g.drawLine(mouseX, 0, mouseX, getHeight());
            g.drawLine(0, mouseY, getWidth(), mouseY);
            
            g.drawLine(mouseX+1, 0, mouseX+1, getHeight());
            g.drawLine(0, mouseY+1, getWidth(), mouseY+1);
            
            //Opening credits & stuff
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
            
            
            frames++;
        }
        class TimerListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                repaint();
            }
        }
    }
    
}
