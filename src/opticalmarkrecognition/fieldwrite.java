/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package opticalmarkrecognition;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sushil
 */
public class fieldwrite {
    public static void main(String[] args) throws IOException {
        FileReader inputStream = null;
        FileWriter outputStream = null;
        //get first field and total option from .field file
        try {
            inputStream = new FileReader("testt.txt");
            int c;
            int i=0,j=1,f;
            int temp=0;
            String item[] = new String[50];
            int info[] = new int[50];
            info[0]=0;
            c = inputStream.read();
            item[1]=Character.toString ((char) c);
            while ((c = inputStream.read()) != -1) {
                if (c==32){ i++;}
                if (c==10){
                    info[j]=i-3;
                    info[j]=info[j]+info[j-1];
                    i=0;j++;
                    f = inputStream.read();
                    item[j]=Character.toString ((char) f);
                }
            }
            for(int q=1;q<=6;q++){
                System.out.println(item[q]+" "+info[q]);
            }
            //asc 0 to a b c
            inputStream = new FileReader("test.txt");
            outputStream = new FileWriter("test1.txt");
            i=1;
            j=1;
            while ((c = inputStream.read()) != -1) {
                
                                 
                if(c==48){
                   
                    if(i<=info[j]){                    
                        outputStream.write(item[j]); 
                        i++;
                    }
                    else{
                        j++;
                        outputStream.write(item[j]);
                        i++;
                    }
                        
                }else{
                outputStream.write(c);
                }
                
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(fieldwrite.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(fieldwrite.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}
