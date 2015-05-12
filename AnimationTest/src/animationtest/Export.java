
package animationtest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Export {
    
    public static void writeFile(String code,String fileName) throws FileNotFoundException{
        PrintWriter outFile = new PrintWriter(fileName);
        
        outFile.write(code);
        outFile.close();
    }
    
    public static String pointsToString(){
        String out = "";
        
        for(int i = 0; i<FTCauto.points.size();  i++){
            out+=FTCauto.points.get(i).getX()+" "+FTCauto.points.get(i).getY()
                    +" "+FTCauto.points.get(i).extraCode+ "\n";
        }
        
        return(out);
    }

    public static PointArray fileToPoints(File f)
    {
        PointArray rtn = new PointArray();

        try {
            Scanner scan = new Scanner(f);
            String file = scan.toString();

        }catch (FileNotFoundException e)
        {
            System.out.print("file not found!");
            e.printStackTrace();
        }
        finally {
            return rtn;
        }
    }
    
}
