/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package opticalmarkrecognition;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;



/**
 *
 * @author sushil
 */
public class test {
    
    
    
    public static void main(String[] args) throws IOException, SQLException  {
        DBHelper dbh = new DBHelper();
        int d = dbh.counttest("sex","yes","wrc");
        System.out.println(d);
        
        
        
        
        
//        int p=0,i=0,j=0;
//        String a[] = new String[50];
//        String b[] = new String[50];
//        Scanner sc2 = null;
//        try {
//            sc2 = new Scanner(new File("sushilf.txt"));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        while (sc2.hasNextLine()) {
//            Scanner s2 = new Scanner(sc2.nextLine());
//            while (s2.hasNext()) {
//                if(p==3){
//                    a[j]=s2.next();
//                    j++;
//                    p++;
//                }
//                else if(p>=4){
//                    b[i]=s2.next();
//                    i++;
//                    p++;
//                }else{
//                s2.next();
//                p++;
//               }              
//            }
//            p=0;
//        }
//        
//        for(int k=0;k<=30;k++){
//            System.out.println(a[k]);
//        }
//        System.out.println("/n");
//        for(int k=0;k<=30;k++){
//            System.out.println(b[k]);
//        }
    }
}
