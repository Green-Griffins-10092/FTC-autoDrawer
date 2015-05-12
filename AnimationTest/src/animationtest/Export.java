
package animationtest;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Export {
    
    public static void writeFile(String code,String fileName) throws FileNotFoundException{
        PrintWriter outFile = new PrintWriter(fileName);
        
        outFile.print(code);
        
        outFile.close();
    }
    
}
