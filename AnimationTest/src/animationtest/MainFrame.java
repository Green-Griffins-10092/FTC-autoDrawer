package animationtest;

import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

public class MainFrame extends JFrame {

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        MainFrame frame = new MainFrame();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(new InfoBar.MainGraphicsPanel(), BorderLayout.LINE_START);
        frame.getContentPane().add(new FTCauto.MainGraphicsPanel(), BorderLayout.CENTER);
        frame.pack();
        frame.setTitle("AutoDrawer");
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);
        frame.setJMenuBar(MenuBars.menuBars());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
