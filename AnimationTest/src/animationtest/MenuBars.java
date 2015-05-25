package animationtest;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import animationtest.FTCauto.MainGraphicsPanel;

public class MenuBars {
    
    
    public static JMenuBar menuBar = new JMenuBar();
    public static JMenu file = new JMenu("File");
    public static JMenu tool = new JMenu("Tool Type");
    public static JMenuItem toolAdd = new JMenuItem("Add", new ImageIcon("Waypoint.png"));
    public static JMenuItem toolDelete = new JMenuItem("Delete", new ImageIcon("Delete.jpg"));
    public static JMenuItem toolEdit = new JMenuItem("Edit", new ImageIcon("Edit.png"));
    
    //testing methods, will be added to menu if
    //developing in FTCauto is true
    public static JMenu testing = new JMenu("Testing Methods");
    public static JMenuItem testingGetDistance = new JMenuItem("Get Distance", new ImageIcon("Ruler.jpg"));

    public static JMenuBar menuBars() {

        file.setMnemonic(KeyEvent.VK_A);
        file.getAccessibleContext().setAccessibleDescription("Test Main menu");
        menuBar.add(file);
        
        JMenuItem newFile = new JMenuItem("New File", KeyEvent.VK_S);
        //newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        newFile.getAccessibleContext().setAccessibleDescription("Make a new blank file");
        file.add(newFile);
        
        JMenuItem saveText = new JMenuItem("Save Text", KeyEvent.VK_S);
        saveText.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        saveText.getAccessibleContext().setAccessibleDescription("Save the file in text format");
        file.add(saveText);

        JMenuItem saveBinary = new JMenuItem("Save Binary", KeyEvent.VK_S);
        saveText.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        saveText.getAccessibleContext().setAccessibleDescription("Save the file in binary format");
        file.add(saveBinary);
        
        JMenuItem saveAs = new JMenuItem("Save As", KeyEvent.VK_S);
        //menuItem2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
        saveAs.getAccessibleContext().setAccessibleDescription("Save the file");
        file.add(saveAs);
        
        JMenuItem menuItem5 = new JMenuItem("Export");
        menuItem5.getAccessibleContext().setAccessibleDescription("Export the file");
        file.add(menuItem5);

        file.addSeparator();
        
        JMenuItem openText = new JMenuItem("Open Text", KeyEvent.VK_S);
        openText.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        openText.getAccessibleContext().setAccessibleDescription("Open a text file");
        file.add(openText);
        
        JMenuItem openBinary = new JMenuItem("Open Binary", KeyEvent.VK_S);
        openText.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        openText.getAccessibleContext().setAccessibleDescription("Open a binary file");
        file.add(openBinary);

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
        
        newFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n = JOptionPane.showConfirmDialog(null, "Are you sure you want to overwrite the existing data?", "New File", 0, JOptionPane.WARNING_MESSAGE);
                
                System.out.println(n);
                
                if(n == JOptionPane.YES_OPTION){
                    //Reset All data
                    FTCauto.points = new PointArray();
                    MainGraphicsPanel.tool.history = new History(FTCauto.points);
                    MainGraphicsPanel.file = null;
                }
            }
        });
        
        saveText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (MainGraphicsPanel.file == null) {
                        MainGraphicsPanel.file = FileChooser.fileChooser("Save", "Save", "Save a file");
                        Export.writeTextFile(Export.pointsToString(), MainGraphicsPanel.file.getAbsolutePath() + ".tAD"); // tAd = text AutoDrawer
                        MainGraphicsPanel.tool.history = new History(FTCauto.points);
                    } else {
                        Export.writeTextFile(Export.pointsToString(), MainGraphicsPanel.file.getAbsolutePath() + ".tAD"); // tAd = text AutoDrawer
                        MainGraphicsPanel.tool.history = new History(FTCauto.points);
                    }
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(MenuBars.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        saveBinary.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (MainGraphicsPanel.file == null) {
                        MainGraphicsPanel.file = FileChooser.fileChooser("Save", "Save", "Save a file");
                        Export.writeBinaryFile(MainGraphicsPanel.file.getAbsolutePath() + ".bAD"); // bAD = binary AutoDrawer
                        MainGraphicsPanel.tool.history = new History(FTCauto.points);
                    } else {
                        Export.writeBinaryFile(MainGraphicsPanel.file.getAbsolutePath() + ".bAD"); // bAD = binary AutoDrawer
                        MainGraphicsPanel.tool.history = new History(FTCauto.points);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(MenuBars.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        saveAs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainGraphicsPanel.file = FileChooser.fileChooser("Save", "Save", "Save a file");
                //TODO: Ask User What format, then call the appropriate saving methods.
            }
        });

        openText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String path = FileChooser.fileChooser("Open", "Open", "Open a file").getAbsolutePath();
                    if (path.substring(path.lastIndexOf('.')).equals(".tAD")) {
                        FTCauto.points = Export.readTextFile(path);
                        MainGraphicsPanel.tool.history = new History(FTCauto.points);
                        MainGraphicsPanel.file = new File(path);
                    } else {
                        System.out.println("Invalid file type!");
                    }
                } catch (NullPointerException ex) {
                    Logger.getLogger(FileChooser.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        openBinary.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String path = FileChooser.fileChooser("Open", "Open", "Open a file").getAbsolutePath();
                    if (path.substring(path.lastIndexOf('.')).equals(".bAD")) {
                        FTCauto.points = Export.readBinaryFile(path);
                        MainGraphicsPanel.tool.history = new History(FTCauto.points);
                        MainGraphicsPanel.file = new File(path);
                    } else {
                        System.out.println("Invalid file type!");
                    }
                } catch (NullPointerException ex) {
                    Logger.getLogger(FileChooser.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //------------Tool Menu----------------

        tool.setMnemonic(KeyEvent.VK_B);
        tool.getAccessibleContext().setAccessibleDescription("Tool Menu");
        menuBar.add(tool);


        toolAdd.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        toolAdd.getAccessibleContext().setAccessibleDescription("Change the tool to add");
        tool.add(toolAdd);


        toolDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        toolDelete.getAccessibleContext().setAccessibleDescription("Change the tool to delete");
        tool.add(toolDelete);

        toolEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        toolEdit.getAccessibleContext().setAccessibleDescription("Change the tool to edit");
        tool.add(toolEdit);

        toolAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainGraphicsPanel.tool.toolType = 1;
                System.out.println("Add");
            }
        });

        toolDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainGraphicsPanel.tool.toolType = 2;
                System.out.println(MainGraphicsPanel.tool.toolType);
            }
        });

        toolEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainGraphicsPanel.tool.toolType = 3;
                System.out.println(MainGraphicsPanel.tool.toolType);
            }
        });

        menuItem4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        //--------Selected point menu-------
        JMenu selectedPoints = new JMenu("Selected point");
        menuBar.add(selectedPoints);

        JMenuItem changeExtraCode = new JMenuItem("Change Extra code", KeyEvent.VK_S);
        openText.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.ALT_MASK));
        openText.getAccessibleContext().setAccessibleDescription("Change the extra code");
        selectedPoints.add(changeExtraCode);

        changeExtraCode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FTCauto.points.get(FTCauto.points.selectedPoint).extraCode = JOptionPane.showInputDialog("Put your extra code here:",
                        FTCauto.points.get(FTCauto.points.selectedPoint).extraCode);
                MainGraphicsPanel.tool.history.addVersion(FTCauto.points);
            }
        });

        if (FTCauto.developing) {
            menuBar.add(testing);
            testingGetDistance.getAccessibleContext().setAccessibleDescription("Test the getDistance method\n" +
                    " prints the distance between the selected point and\n " +
                    "the clicked on point to the terminal");
            testing.add(testingGetDistance);

            testingGetDistance.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MainGraphicsPanel.tool.toolType = -1;
                    System.out.println(MainGraphicsPanel.tool.toolType);
                }
            });


        }

        return (menuBar);
    }
}
