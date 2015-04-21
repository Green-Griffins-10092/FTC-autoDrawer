package animationtest;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.*;

public class MenuBars extends JMenuBar{
    public static JMenuBar menuBars(){
        JMenuBar menuBar = new JMenuBar();
    
        JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_A);
        file.getAccessibleContext().setAccessibleDescription("Test Main menu");
        menuBar.add(file);
        
        JMenuItem menuItem1 = new JMenuItem("Save",KeyEvent.VK_T);
        menuItem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
        menuItem1.getAccessibleContext().setAccessibleDescription("Save the file");
        file.add(menuItem1);
        
        JMenuItem menuItem2 = new JMenuItem("Save As",KeyEvent.VK_T);
        //menuItem2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
        menuItem2.getAccessibleContext().setAccessibleDescription("Save the file");
        file.add(menuItem2);
        
        JMenuItem menuItem3 = new JMenuItem("Open",KeyEvent.VK_T);
        menuItem3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
        menuItem3.getAccessibleContext().setAccessibleDescription("Open a file");
        file.add(menuItem3);
        
        return(menuBar);
    }
}
