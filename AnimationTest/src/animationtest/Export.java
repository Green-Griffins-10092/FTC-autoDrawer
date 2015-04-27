
package animationtest;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Export {
    
    public static String out = "";
    
    public static String credits = "";
    
    public static String autoDrive ="";
    
    //AutoDrive
    public static void prepareCode() throws FileNotFoundException{
        
        //getAutoDrive();
        //out = autoDrive;
        
        //System.out.println(out);
    }
    
    public static void getAutoDrive() throws FileNotFoundException{
        
        //Need to make tha autoDrive when the API comes out
        FileReader reader = new FileReader("autoDrive.txt");
        Scanner inFile = new Scanner(reader);
        
        autoDrive = inFile.next();
        
        System.out.println(autoDrive);
    }
    
    public static void export() throws FileNotFoundException{
        prepareCode();
    }
    
    public static void writeFile(String code,String fileName){
        //Stuff
    }
    
}
