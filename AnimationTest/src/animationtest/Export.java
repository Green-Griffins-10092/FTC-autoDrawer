
package animationtest;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Export {
    
    public static void writeFile(String code,String fileName) throws FileNotFoundException{
        PrintWriter outFile = new PrintWriter(fileName);
        
        outFile.write(code);
        outFile.close();
    }
    
    public static String pointsToString(){
        String out = "";
        
        for(int i = 0; i<FTCauto.points.size();  i++){
            out+=FTCauto.points.get(i).x+" "+FTCauto.points.get(i).y
                    +" \""+FTCauto.points.get(i).extraCode+ "\"\n";
        }
        
        return(out);
    }
    
}
