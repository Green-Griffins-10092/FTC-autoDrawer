package animationtest;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager.LookAndFeelInfo;

class MainFrame extends JFrame {
    
    static FTCauto.MainGraphicsPanel auto;
    static InfoBar.MainGraphicsPanel infoBar;
    private static boolean developing = false;
    
    public static void main(String[] args) {
        System.out.println("test");
        
        for (String s : args) {
            if (s.equals("-d") || s.equalsIgnoreCase("--developing") || s.equalsIgnoreCase("-developing") || s.equals("developing"))
                developing = true;
        }


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
        frame.setVisible(true);
    }
}
