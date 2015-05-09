package animationtest;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import animationtest.FTCauto.MainGraphicsPanel;

public class MenuBars{
    
    public static JMenuBar menuBar = new JMenuBar();
    public static JMenu file = new JMenu("File");
    public static JMenu tool = new JMenu("Tool Type");
    public static JMenuItem toolAdd = new JMenuItem("Add",KeyEvent.VK_T);
    public static JMenuItem toolDelete = new JMenuItem("Delete",KeyEvent.VK_T);
    public static JMenuItem toolEdit = new JMenuItem("Edit", KeyEvent.VK_T);

    //testing methods, will be added to menu if
    //developing in FTCauto is true
    public static JMenu testing = new JMenu("Testing Methods");
    public static JMenuItem testingGetDistance = new JMenuItem("Get Distance");

    public static JMenuBar menuBars(){
        
        
        
        file.setMnemonic(KeyEvent.VK_A);
        file.getAccessibleContext().setAccessibleDescription("Test Main menu");
        menuBar.add(file);
        
        JMenuItem menuItem1 = new JMenuItem("Save",KeyEvent.VK_S);
        menuItem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
        menuItem1.getAccessibleContext().setAccessibleDescription("Save the file");
        file.add(menuItem1);
        
        JMenuItem menuItem2 = new JMenuItem("Save As",KeyEvent.VK_S);
        //menuItem2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
        menuItem2.getAccessibleContext().setAccessibleDescription("Save the file");
        file.add(menuItem2);
        
        JMenuItem menuItem5 = new JMenuItem("Export");
        menuItem5.getAccessibleContext().setAccessibleDescription("Export the file");
        file.add(menuItem5);
        
        file.addSeparator();
        
        JMenuItem menuItem3 = new JMenuItem("Open",KeyEvent.VK_S);
        menuItem3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        menuItem3.getAccessibleContext().setAccessibleDescription("Open a file");
        file.add(menuItem3);
        
        file.addSeparator();
        
        JMenuItem menuItem4 = new JMenuItem("Close");
        menuItem4.getAccessibleContext().setAccessibleDescription("Close the program");
        file.add(menuItem4);
        
        file.addSeparator();

        JMenuItem undo = new JMenuItem("Undo", KeyEvent.VK_S);
        undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        file.add(undo);

        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FTCauto.points = MainGraphicsPanel.tool.history.getPreviousVersion();
            }
        });

        JMenuItem redo = new JMenuItem("Redo", KeyEvent.VK_S);
        redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        file.add(redo);

        redo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FTCauto.points = MainGraphicsPanel.tool.history.getNextVersion();
            }
        });
        
        //------------Tool Menu----------------
        
        tool.setMnemonic(KeyEvent.VK_B);
        tool.getAccessibleContext().setAccessibleDescription("Tool Menu");
        menuBar.add(tool);
        
        
        toolAdd.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));
        toolAdd.getAccessibleContext().setAccessibleDescription("Change the tool to add");
        tool.add(toolAdd);
        
        
        toolDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        toolDelete.getAccessibleContext().setAccessibleDescription("Change the tool to delete");
        tool.add(toolDelete);

        toolEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        toolEdit.getAccessibleContext().setAccessibleDescription("Change the tool to edit");
        tool.add(toolEdit);
        
        toolAdd.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
                MainGraphicsPanel.tool.toolType = 1;
                System.out.println("Add");
            }
        });
        
        toolDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainGraphicsPanel.tool.toolType  = 2;
                System.out.println(MainGraphicsPanel.tool.toolType );
            }
        });

        toolEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainGraphicsPanel.tool.toolType  = 3;
                System.out.println(MainGraphicsPanel.tool.toolType );
            }
        });
        
        menuItem4.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
        
        //--------Selected point menu-------
        JMenu selectedPoint = new JMenu("Selected point");
        menuBar.add(selectedPoint);
        
        JMenuItem changeExtraCode = new JMenuItem("Change Extra code",KeyEvent.VK_S);
        menuItem3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.ALT_MASK));
        menuItem3.getAccessibleContext().setAccessibleDescription("Change the extra code");
        selectedPoint.add(changeExtraCode);
        
        if(FTCauto.developing)
        {
            menuBar.add(testing);
            testingGetDistance.getAccessibleContext().setAccessibleDescription("Test the getDistance method\n" +
                    " prints the distance between the selected point and\n " +
                    "the clicked on point to the terminal");
            testing.add(testingGetDistance);

            testingGetDistance.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MainGraphicsPanel.tool.toolType  = -1;
                    System.out.println(MainGraphicsPanel.tool.toolType );
                }
            });
        }

        return(menuBar);
    }
}
