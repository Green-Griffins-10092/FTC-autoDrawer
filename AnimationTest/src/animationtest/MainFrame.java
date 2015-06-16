package animationtest;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class MainFrame extends JFrame {

    static FTCauto.MainGraphicsPanel auto;
    static InfoBar.MainGraphicsPanel infoBar;
    private static boolean developing = false;

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
