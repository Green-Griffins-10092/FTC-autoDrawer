package animationtest;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.*;

import static java.lang.Thread.sleep;

class MainFrame extends JFrame {

    protected static boolean splashScreen = true;
    protected static SplashScreen spSc;

    static FTCauto.MainGraphicsPanel auto;
    static InfoBar.MainGraphicsPanel infoBar;
    private static boolean developing = false;
    
    private static final Image icon = new ImageIcon("Images/icon.png").getImage();
    
    public static void main(String[] args) {

        spSc = SplashScreen.getSplashScreen();

        if (spSc == null) {
            splashScreen = false;
            System.out.println("No Splash Screen");
        } else {
            System.out.println("Splash Screen!");
        }

        for (String s : args) {
            if (s.equals("-d") || s.equalsIgnoreCase("--developing") || s.equalsIgnoreCase("-developing") || s.equals("developing"))
                developing = true;
        }


//        try {
//            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//            } catch (Exception e) {
//                // If Nimbus is not available, fall back to cross-platform
//             try {
//                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
//            } catch (Exception ex) {
//
//            }
//        }
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
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

        //display the splash screen for a little
        if (splashScreen && !developing) {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        MainFrame frame = new MainFrame();
        infoBar = new InfoBar.MainGraphicsPanel(developing);
        auto = new FTCauto.MainGraphicsPanel(developing);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(infoBar, BorderLayout.LINE_START);
        frame.getContentPane().add(auto, BorderLayout.CENTER);
        frame.pack();
        frame.setTitle("AutoDrawer");
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);
        frame.setJMenuBar(MenuBars.menuBars(developing));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setIconImage(icon);
        frame.setVisible(true);
        
        auto.tool.toolType = 1;
    }
}
