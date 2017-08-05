/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2013, by Object Refinery Limited and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 * [Oracle and Java are registered trademarks of Oracle and/or its affiliates. 
 * Other names may be trademarks of their respective owners.]
 *
 * ------------------------------
 * CombinedCategoryPlotDemo1.java
 * ------------------------------
 * (C) Copyright 2008, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   ;
 *
 * Changes
 * -------
 * 05-May-2008 : Version 1 (DG);
 *
 */

package opticalmarkrecognition;

import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.experimental.chart.plot.CombinedCategoryPlot;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A demo for the {@link CombinedCategoryPlot} class.
 */
public class CombinedCategoryPlotDemo1 extends ApplicationFrame {

    /**
     * Creates a new demo instance.
     *
     * @param title  the frame title.
     */
    DBHelper dbh = new DBHelper();
    int info[] = new int[50];
    int infoo[] = new int[50];
    int count=0;
    static String filename;
    public CombinedCategoryPlotDemo1(String title) throws SQLException, IOException {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }  

    
    /**
     * Creates a dataset.
     *
     * @return A dataset.
     */
    public CategoryDataset createDataset2() throws SQLException, IOException {
        
        DefaultCategoryDataset result = new DefaultCategoryDataset();
        String[] series = {"","first option", "second option", "third option","fourth option","fifth option","sixth option","seventh option"};       
        //String[] typee= {"","Type1", "Type2", "Type3","Type4","Type5","Type6","Type7","Type8"};
        
        ResultSet res = dbh.getXnamevalue();
        ResultSetMetaData metaData = res.getMetaData();
            count = metaData.getColumnCount(); //number of column
            String columnName[] = new String[count];
            String[] typee = new String[count];
            for (int i = 1; i <= count; i++)
            {
            columnName[i-1] = metaData.getColumnLabel(i);
            //System.out.println(columnName[i-1]);
            typee[i-1]=columnName[i-1];
            
            }
//count option
            FileReader inputStream = null;
        FileWriter outputStream = null;
        try {
            if (filename==null){
                filename = "wrc";
            }
            inputStream = new FileReader("Storage/"+filename+"/"+filename+"f.txt");
            int c;
            int i=0,j=1,n=1,f;
            String item[] = new String[50];
            
            info[0]=0;
            c = inputStream.read();
            item[1]=Character.toString ((char) c);
            while ((c = inputStream.read()) != -1) {
                if (c==32){ i++;}
                if (c==10){
                    info[j]=i-3;
                    infoo[j]=i-3;
                    infoo[j]=infoo[j]+infoo[j-1];
                    i=0;j++;
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
            //System.out.println("tot"+infoo[6]);
//            for(int i=1;i<=6;i++){
//            System.out.println(infoo[i]+" "+series[i]);
//            }
            //for value
            int p=0,i=0,j=0;
        String a[] = new String[50];
        String b[] = new String[50];
        String c[] = new String[50];
        Scanner sc2 = null;
        try {
            sc2 = new Scanner(new File("Storage/"+filename+"/"+filename+"f.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (sc2.hasNextLine()) {
            Scanner s2 = new Scanner(sc2.nextLine());
            while (s2.hasNext()) {
                if(p==3){
                    a[j]=s2.next();
                    j++;
                    p++;
                }
                else if(p>=4){
                    b[i]=s2.next();
                    i++;
                    p++;
                }else{
                s2.next();
                p++;
               }              
            }
            p=0;
        }
        int f=1;
        //System.out.println(infoo[count-1]-1);
        for(int k=0;k<=infoo[count-1]-1;k++){
            if (k<infoo[f]){
            c[k]=a[f-1];
            }
            else{
            f++;
            c[k]=a[f-1];
            }
        }
        for(int k=0;k<=infoo[count-1]-1;k++){
            System.out.println(b[k]);
        }
        int value[] = new int[50];
        for(int k=0;k<=infoo[count-1]-1;k++){
            value[k] = dbh.count(c[k],b[k],filename);
        }
        for(int k=0;k<=infoo[count-1]-1;k++){
            System.out.println(value[k]);
        }
        //d[0] = dbh.counttest("sex","yes",filename);
        //System.out.println(d[0]);
        
        
            //end value
            i=1;j=1;
            //int[] value = {7,4,7,8,5,6,8,9,6,5,4,3,5,7,8,9,5,5,7,8,9,7,6,5,5,5,4,3,7,9,9};  //make a[]=sex sex married married     
            for(int k=1;k<=infoo[count-1];k++){                                             //make b[]=yes no yes no
                if(i<=info[j]){ 
                        result.addValue(value[k-1], series[i], typee[j]);
                        //System.out.println("result.addValue(11.0, "+series[i]+", "+typee[j]); 
                        i++;
                    }
                    else{
                        j++;
                        i=1;
                        result.addValue(value[k-1], series[i], typee[j]);
                        //System.out.println("result.addValue(11.0, "+series[i]+", "+typee[j]);
                        i++;
                    }
            }

        return result;

    }

    /**
     * Creates a chart.
     *
     * @return A chart.
     */
    private JFreeChart createChart() throws SQLException, IOException {

        CategoryDataset dataset2 = createDataset2();
        NumberAxis rangeAxis2 = new NumberAxis("Value");
        rangeAxis2.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        BarRenderer renderer2 = new BarRenderer();
        renderer2.setBaseToolTipGenerator(
                new StandardCategoryToolTipGenerator());
        CategoryPlot subplot2 = new CategoryPlot(dataset2, null, rangeAxis2,
                renderer2);
        subplot2.setDomainGridlinesVisible(true);

        CategoryAxis domainAxis = new CategoryAxis("Category");
        CombinedCategoryPlot plot = new CombinedCategoryPlot(
                domainAxis, new NumberAxis("Range"));
        //plot.add(subplot1, 2);
        plot.add(subplot2, 1);

        JFreeChart result = new JFreeChart(
                "Survey Result",
                new Font("SansSerif", Font.BOLD, 12), plot, true);
        return result;

    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public JPanel createDemoPanel() throws SQLException, IOException {
        JFreeChart chart = createChart();
        return new ChartPanel(chart);
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) throws SQLException, IOException {
        String title = "Survey Result";
        CombinedCategoryPlotDemo1 demo = new CombinedCategoryPlotDemo1(title);
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
    
    public static void start(String file) throws SQLException, IOException{
        filename=file;
    String title = "Survey Result";
        CombinedCategoryPlotDemo1 demo = new CombinedCategoryPlotDemo1(title);
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
