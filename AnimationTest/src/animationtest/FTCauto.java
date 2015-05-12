package animationtest;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
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

public class FTCauto extends JFrame {
    
    public FTCauto() {
        add(new MainGraphicsPanel());
    }
    
    public static double fieldSize = 0;
    
    //!!Only for development version!!
    public static boolean developing = true;

    public static PointArray points = new PointArray();
    
    public static class MainGraphicsPanel extends JPanel{
        
        
        //Variables
        public static int mouseX = 0;
        public static int mouseY = 0;
        
        private int movePoint = 1;
        
        private int openingTrans = 255;
        private int openingTextTrans = 255;
         
        private int frames = 0;
        
        //Picture stuff  !!You have to use png so you can have transparency
        private static final Image stuffedGriffins = new ImageIcon("STUFFED_GRIFFINS_FINAL_GRN.png").getImage();
        private static final Image field = new ImageIcon("Field.png").getImage();
        private static final Image autoDrawer = new ImageIcon("autoDrawer.png").getImage();
        
        
        private static final double FIELD_HEIGHT_IN_INCHES = 18;
        private static double inchesToPixels = FIELD_HEIGHT_IN_INCHES/fieldSize;

        public static double getInchesToPixels()
        {
            return inchesToPixels;
        }
        static ToolMouseListener tool;
        
        public MainGraphicsPanel(){
            
            
            //Getting mouse location when moved
            addMouseMotionListener(new MouseMotionAdapter(){
                
                
                public void mouseMoved(MouseEvent e){
                    mouseX = e.getX();
                    mouseY = e.getY();
                    
                }
            });
            addMouseMotionListener(new MouseMotionAdapter(){
                
                
                public void mouseDragged(MouseEvent e){
                    mouseX = e.getX();
                    mouseY = e.getY();
                    
                }
            });
            
            //The animation timer
            Timer timer = new Timer(10, new TimerListener());
            timer.start();

            tool = new ToolMouseListener();
            addMouseListener(tool);
        }
        
        Color background = new Color(200,200,200);
        Color sidePanelDark = new Color(0,0,0,50);
        Color sidePanel = new Color(0,90,33);
        Color sidePanelLight = new Color(200,200,200);
        Color warningRed = new Color(250, 0, 0);
        
        
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            
            Graphics2D g2 = (Graphics2D) g;
            
            //Update the dragged point
            if(tool.pointDragging!=-1){
                points.get(tool.pointDragging).setX(mouseX);
                points.get(tool.pointDragging).setY(mouseY);
            }
            
            
            //Main frame
            g.setColor(background);
            g.fillRect(0, 0, getWidth(), getHeight());
            
            g.setColor(sidePanel);
            g.fillRect(0, 0, 100, getHeight());
            
            
            g.setColor(sidePanelLight);
            g.fillRect(0, 0, 100, 10);
            
            
            //Resizing the field
            if(getWidth()-100<getHeight()-10){
                fieldSize = getWidth()-100;
            }else{
                fieldSize = getHeight()-10;
            }
            
            g.drawImage(field, 100, 10, (int)fieldSize, (int) fieldSize, null);

            
            //Menu Shadow
             g.setColor(sidePanelDark);
            //g.fillRect(90, 0, 10, getHeight());
            
            int[] shadowXPoints = {100,130,130,100};
            int[] shadowYPoints = {10,30,getHeight(),getHeight()};
        
            g.fillPolygon(shadowXPoints,shadowYPoints, 4);
            
            
            //Draw the Points
            for(int i = 0; i < points.size(); i++){
                //Making it into a more usable form
                int size = points.get(i).size;
                
                Color point = new Color(0, 200, 50, points.get(i).transparency);
                
                //set color
                g.setColor(point);
                
               //Draw the point
                g.fillOval((int) points.get(i).getX()+100-(size/2), (int) points.get(i).getY()+10-(size/2), 
                        size, size);
                
                //Changing the size
                if(size>10){
                    points.get(i).size -=points.get(i).sizeSpeed;
                    
                    points.get(i).sizeSpeed+=0.5;
                }
                //And the transparency
                if(points.get(i).transparency<250){
                    points.get(i).transparency+=2;
                }
                if(i>0){
                    g2.setStroke(new BasicStroke(5));
                    g2.draw(new Line2D.Float((float) points.get(i).getX()+100, (float) points.get(i).getY()+10,
                            (float) points.get(i-1).getX()+100, (float) points.get(i-1).getY()+10));
                }
                
                int transMax = 250;
                //if the point is selected, then set color to be blue, if not green.
                if(tool.selectedPoint == i) {
                    point = new Color(0, 10, 150, points.get(i).transparency);
                    g.setColor(point);
                    transMax = 150;
                }
                
                //Draw the point
                g.fillOval((int) points.get(i).getX()+100-(size/2), (int) points.get(i).getY()+10-(size/2),
                        size, size);
                
                //Draw the angle
                if(i!=0&&i!=points.size()-1){
                    g.setColor(new Color(0,0,0,points.get(i).transparency));
                    String angle = String.valueOf((int)points.getAngle(i));
                    g.drawString(angle,
                        (int) (points.get(i).getX()+100), (int) points.get(i).getY()+10);
                        
                }
                
                if(!points.get(i).extraCode.equals("")){
                    g.setColor(new Color(0,0,0));
                    g2.setStroke(new BasicStroke(2));
                    g2.draw(new Line2D.Float((float) points.get(i).getX()+100, (float) points.get(i).getY()+10,
                            (float) points.get(i).getX()+70, (float) points.get(i).getY()-10));
                    
                    String outCode = "";
                    
                    if(points.get(i).extraCode.length()>15){
                        for(int j =0; j<15; j++){
                            outCode+=points.get(i).extraCode.charAt(j);
                            
                        }
                        outCode+="...";
                    }else{
                        outCode=points.get(i).extraCode;
                    }
                    
                    g.setColor(new Color(0,0,0,50));
                    g.fillRect((int) points.get(i).getX()+63, (int) points.get(i).getY()-26,(8*outCode.length())+4, 14);
                    g.drawRect((int) points.get(i).getX()+63, (int) points.get(i).getY()-26,(8*outCode.length())+4, 14);
                    
                    g.setColor(new Color(0,0,0));
                    g.drawString(outCode,  (int) points.get(i).getX()+65, (int) points.get(i).getY()-15);
                }
                
                
                //Changing the size
                if(size>10){
                    points.get(i).size -=points.get(i).sizeSpeed;

                    points.get(i).sizeSpeed+=0.1;
                }

                //And the transparency
                if(points.get(i).transparency < transMax){
                    points.get(i).transparency += 2;
                }
            }
            
            //Changing the tool color
            if(tool.toolType == 1){
                Color point = new Color(0,200,50,(int) ((Math.sin((double)frames/20)*50)+130));
                g.setColor(point);
            }else if(tool.toolType == 2){
                Color point = new Color(200,50,0,(int) ((Math.sin((double)frames/20)*50)+130));
                g.setColor(point);
            }else if(tool.toolType == 3){
                Color point = new Color(50, 225, 200, (int) ((Math.sin((double)frames/20)*50)+130));
                g.setColor(point);
            }else if(developing && tool.toolType == -1){
                g.setColor(warningRed);
            }
            
            //Mouse stuff
            g2.setStroke(new BasicStroke(2));
            g.drawLine(mouseX, 0, mouseX, getHeight());
            g.drawLine(0, mouseY, getWidth(), mouseY);

            g.drawLine(mouseX+1, 0, mouseX+1, getHeight());
            g.drawLine(0, mouseY+1, getWidth(), mouseY+1);
            
            //Opening credits & stuff
            if(!developing){
                if(openingTrans>0&&frames>300){
                    openingTrans-=5;
                }
                
                if(frames>50 && openingTextTrans>0){
                    openingTextTrans-=5;
                }
                
                Color OpeningBackground = new Color(255,255,255,openingTrans);
                g.setColor(OpeningBackground);
                g.fillRect(0, 0, getWidth(), getHeight());
                
                if(frames<300){
                    g.drawImage(stuffedGriffins, 50, getHeight()-150, 748/3, 299/3,null);
                    g.drawImage(autoDrawer, (getWidth()/2)-300, (getHeight()/2)-100, 629, 236,null);
                }
                
                
                
                OpeningBackground = new Color(10,10,10,openingTextTrans);
                g.setColor(OpeningBackground);
                g.fillRect(0, 0, getWidth(), getHeight());
            } else {
                g.setColor(warningRed);
                g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 35));
                g.drawString("Developing!", 5, 20);
            }
            
            //the ratio of inches to pixels in the field
            inchesToPixels = FIELD_HEIGHT_IN_INCHES/fieldSize;
            
            frames++;
        }
        
        
        class TimerListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                repaint();
            }
        }
    }
    
}
