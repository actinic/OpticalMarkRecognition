/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opticalmarkrecognition;


import net.sourceforge.jiu.codecs.*;
import net.sourceforge.jiu.data.*;
import net.sourceforge.jiu.color.reduction.*;
import net.sourceforge.jiu.filters.*;
import net.sourceforge.jiu.geometry.*;

/**
 *
 * @author OMR
 */


public class ProcessTemplate {

    //public static void main(String args[]) {
    public ProcessTemplate(String filename, String filePath){
        //String filename = "template_trial.png";
        //String filename = "C:\\Users\\sushil\\Documents\\template_trial.png";

        Gray8Image grayimage = ImageUtil.readImage(filename);
        //Gray8Image grayimage = ImageUtil.readImage("2circle-org-colored-whole.jpeg");
        
        ImageManipulation image = new ImageManipulation(grayimage);
        image.locateConcentricCircles();
        image.locateMarks();
        
        //image.writeAscTemplate(filePath + "s.asc");
        image.writeAscTemplate(filePath + "a.txt");
        image.writeConfig(filePath + ".config");
//        image.writeAscTemplate("2circle-org-colored-whole.asc");
//        image.writeConfig("2circle-org-colored-whole.config");
    }    
}
