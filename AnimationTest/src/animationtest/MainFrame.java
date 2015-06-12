package animationtest;

import java.awt.BorderLayout;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

public class MainFrame extends JFrame {

    public static boolean developing = false;
    public static FTCauto.MainGraphicsPanel auto;
    public static InfoBar.MainGraphicsPanel infoBar;

    public static void main(String[] args) {


        for (String s : args) {
            if (s.equals("-d") || s.equalsIgnoreCase("--developing") || s.equalsIgnoreCase("-developing") || s.equals("developing"))
                developing = true;
        }


        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
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
