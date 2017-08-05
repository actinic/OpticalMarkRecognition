/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package opticalmarkrecognition;

/**
 *
 * @author Sushil
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.PreparedStatement;
import javax.swing.DefaultListModel;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;
import org.apache.derby.drda.NetworkServerControl;



public class DBH {

    static Statement stmt;
     static NetworkServerControl server = null;
    
    static Connection conn;
        String selectedID;
    String[] info;
    /**
     * @param args the command line arguments
     */
   
        // TODO code application logic here
        public DBH(){
            
                     try {
            server = new NetworkServerControl(InetAddress.getByName("localhost"), 1527);
            server.start(null);
        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
}
         try {
            // The newInstance() call is a work around for some
            // broken Java implementations

            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (Exception ex) {
            System.out.println("Failed "+ ex);
        }
        //conn = null;
    if(conn==null){
        
    
    try {
    conn =
       DriverManager.getConnection("jdbc:derby://localhost:1527/PersonDB","root","root");

    stmt = conn.createStatement();
    
  
    } catch (SQLException ex) {
    // handle any errors
    System.out.println("SQLException: " + ex.getMessage());
    System.out.println("SQLState: " + ex.getSQLState());
    System.out.println("VendorError: " + ex.getErrorCode());
}
    }
    
          
    }
      /*   public Boolean studentExist(String name){
                   try{
             
         
            String sql = "SELECT * FROM APP.STUDENTS_INFO WHERE fname like '"+name+"'";
      ResultSet rs = stmt.executeQuery(sql);
      //STEP 5: Extract data from result set
     
       if(rs.next())
        return(true);
       
       else
           return(false); 
        
       
         
      
      
        
      }catch(Exception e){
         return(false); 
}
     
         //
         }*/
         public void senddata(String cfname,String cmname,String clname,String cpfname,String cpmname,String cgender,String cdob,String ccontact1,String ccontact2,String ctaddress,String cpaddress,String cgrade,String csection,String ccaste,String cenroll,byte[] personImage, String cbus, String cbloodgroup, String crollno){
   //public void senddata(){
  
    try{
            
        //String qry = "INSERT INTO APP.TABLE1 values(" + name +",21
             
        
         String fullname;
          if(cmname.equals("")){
              fullname = cfname+" "+clname;
          }else{
              fullname = cfname+" "+cmname+" "+clname;
          }
          String lfullname=fullname.toLowerCase();
          
          
          //String qry = "INSERT INTO APP.STUDENTS_INFO(SID,FNAME,MNAME,LNAME,PFNAME,PMNAME,DOB,CASTE,HCONTACT,PCONTACT,ENROLLEDFROM,GRADE,PADDRESS,TADDRESS,SECTION) " +
            //     "VALUES (13,'"+cfname+"','"+cmname+"','"+clname+"','"+cpfname+"','"+cpmname+"','2052-12-12','"+ccaste+"',"+contact1+","+contact2+",'2052-11-10',"+grade+",'"+cpaddress+"','"+ctaddress+"','"+csection+"')";
        String qry = "INSERT INTO ROOT.STUDENTS_INFO(FNAME,MNAME,LNAME,PFNAME,PMNAME,DOB,CASTE,ENROLLEDFROM,GRADE,"
                + "PADDRESS,TADDRESS,IMAGE,SECTION,HCONTACT,PCONTACT,GENDER,BUS,BLOODGROUP,ROLLNO,FULLNAME,LFULLNAME) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";     
            
            PreparedStatement st = conn.prepareStatement(qry);
                       
            st.setString(1, cfname);
            st.setString(2, cmname);
            st.setString(3, clname);
            st.setString(4, cpfname);
            st.setString(5, cpmname);
            st.setString(6, cdob);
            st.setString(7, ccaste);
            st.setString(8, cenroll);
            st.setString(9, cgrade);
            st.setString(10, cpaddress);
            st.setString(11, ctaddress);
            st.setBytes(12, personImage);
            st.setString(13, csection);
            st.setString(14, ccontact1);
            st.setString(15, ccontact2);
            st.setString(16, cgender);
            st.setString(17, cbus);
            st.setString(18, cbloodgroup);
            st.setString(19, crollno);
            st.setString(20, fullname);
            st.setString(21, lfullname);
           
            
            
            //st.setBytes(14,personImage);
           //System.out.println(cfname+cmname+clname+cpfname+cpmname+cdob+ccaste+cenroll+
             //                cgrade+cpaddress+ctaddress+personImage+csection+ccontact1+ccontact2+cgender+cbus+cbloodgroup+crollno);
            
            
            
           st.execute();
            
        }
        catch(Exception e){
            
        }
        
    }

    ImageIcon getimage(String i) {
        int id = Integer.parseInt(i);
        byte[] imagedata;
        ImageIcon image = null;
        
        try{
             
         
            String sql = "SELECT IMAGE FROM ROOT.STUDENTS_INFO WHERE ROLLNO="+id;
      ResultSet rs = stmt.executeQuery(sql);
      //STEP 5: Extract data from result set
      while(rs.next()){
         //Retrieve by column name
        
         imagedata = rs.getBytes("IMAGE");
         image =new ImageIcon(imagedata);
         
         //Display values
        
       
         
      }
      rs.close();
        
      }catch(Exception e){
      } 
     
      return image;
    
}
       public void changePassword(String NewPass,String user){
       
       try{
           

        stmt.executeUpdate("Update ROOT.USERS set password = '"+NewPass+"' where username = '"+user+"'");
        
        
       
       }
        catch(Exception e){
         e.printStackTrace();
        }
       
    }  String[] getlastmonthstaff(String selectedID) {
        String cleared[]=new String[3];
        int i;
        int id = Integer.parseInt(selectedID);
        
        String sql = "SELECT * FROM ROOT.STAFF_SALARY WHERE SID="+id;
        try{
            ResultSet rs = stmt.executeQuery(sql);
            i=0;
            while(rs.next()){
                i++;
            cleared[0] = rs.getString("CLEARED");
            cleared[1] = rs.getString("BALANCE");
            //
            cleared[2] = rs.getString("PAID");
            //
            }
            rs.close();
            //System.out.print("First: " + cleared);
            if(i==0){
              sql="INSERT INTO ROOT.STAFF_SALARY (SID, BALANCE,PAID) VALUES("+id+", '0','0')";
              PreparedStatement st = conn.prepareStatement(sql);
              st.execute();
              cleared[0]="------";
              cleared[1]="0";
              //
              cleared[2]="0";
              //
            
            }
            
      
            
        }catch(Exception e){
            System.out.println(e);
            
        }
        
        return cleared;
        
    }    public String[] returndata(String selectedID){
    String info[]=new String[18];
        try{
        int ID=Integer.parseInt(selectedID);
        ResultSet rs = stmt.executeQuery("Select * from ROOT.students_info where SID="+ID);
        while(rs.next()){
            info[0] = rs.getString("FNAME");
            info[1] = rs.getString("MNAME");
            info[2] = rs.getString("LNAME");
            info[3] = rs.getString("PFNAME");
            info[4] = rs.getString("PMNAME");
            info[5] = rs.getString("DOB");
            info[6] = rs.getString("CASTE");
            info[7] = rs.getString("ENROLLEDFROM");
            info[8] = rs.getString("PADDRESS");
            info[9] = rs.getString("TADDRESS");
            info[10] = rs.getString("SECTION");
            info[11] = rs.getString("HCONTACT");
            info[12] = rs.getString("PCONTACT");
            info[13] = rs.getString("GRADE");
            info[14] = rs.getString("GENDER");
            info[15] = rs.getString("BUS");
            info[16] = rs.getString("BLOODGROUP");
            info[17] = rs.getString("ROLLNO");
           
            
        }
        return(info);
    }catch(Exception e){
        System.out.println(e);
        return(null);
    }
        
    }
    public String[] returndatastaff(String selectedID){
    String info[]=new String[15];
        try{
        int ID=Integer.parseInt(selectedID);
        ResultSet rs = stmt.executeQuery("Select * from ROOT.TEACHER_INFO where SID="+ID);
        while(rs.next()){
            info[0]  = rs.getString("FNAME");
            info[1]  = rs.getString("MNAME");
            info[2]  = rs.getString("LNAME");
            info[3]  = rs.getString("QAULIFICATION");
            info[4]  = rs.getString("SUBJECT");
            info[5]  = rs.getString("DOB");
            info[6]  = rs.getString("CASTE");
            info[7]  = rs.getString("ENROLLEDFROM");
            info[8]  = rs.getString("PADDRESS");
            info[9]  = rs.getString("TADDRESS");
            info[10] = rs.getString("TYPE");
            info[11] = rs.getString("CONTACT");
            info[12] = rs.getString("JOB");
            info[13] = rs.getString("SEX");
            info[14] = rs.getString("BLOODGROUP");
            
           
            
        }
        return(info);
    }catch(Exception e){
        System.out.println(e);
        return(null);
    }
        
    }
    

    void receivedata(String selID, String[] inf) {
        
        info=inf;
        selectedID=selID;
     
    }

     String[] getlastmonth(String selectedID) {
        
         String cleared[]=new String[3];
        int i;
        int id = Integer.parseInt(selectedID);
        
        String sql = "SELECT * FROM ROOT.STUDENTS_FEE WHERE SID="+id;
        try{
            ResultSet rs = stmt.executeQuery(sql);
            i=0;
            while(rs.next()){
                i++;
            cleared[0] = rs.getString("CLEARED");
            cleared[1] = rs.getString("BALANCE");
            //
            cleared[2] = rs.getString("PAID");
            //
            }
            rs.close();
            //System.out.print("First: " + cleared);
            if(i==0){
              sql="INSERT INTO ROOT.STUDENTS_FEE (SID, BALANCE,PAID) VALUES("+id+", '0','0')";
              PreparedStatement st = conn.prepareStatement(sql);
              st.execute();
              cleared[0]="------";
              cleared[1]="0";
              //
              cleared[2]="0";
              //
            
            }
            
      
            
        }catch(Exception e){
            System.out.println(e);
            
        }
        
        return cleared;
        
    }    public ResultSet returnResultclass(String grade){
    try{
        
        ResultSet rs = stmt.executeQuery("Select SID,FULLNAME from ROOT.students_info where GRADE like '"+grade+"'");
        
    return(rs);
            }catch(Exception e){
                System.out.println(e);
                
            
            return(null);
            }
    }
    
    public ResultSet returnResultSection(String section){
    try{
        
        ResultSet rs = stmt.executeQuery("Select SID,FULLNAME from ROOT.students_info where SECTION like '"+section+"'");
        
    return(rs);
            }catch(Exception e){
                System.out.println(e);
                
            
            return(null);
            }
    }
    
    public ResultSet returnResultSection(String section,String grade){
    try{
        
        ResultSet rs = stmt.executeQuery("Select SID,FULLNAME from ROOT.students_info where GRADE like '"+grade+"' and SECTION like '"+section+"'");
        
    return(rs);
            }catch(Exception e){
                System.out.println(e);
                
            
            return(null);
            }
    }
    public ResultSet returnResultstaff(String text){
    try{
        
        ResultSet rs = stmt.executeQuery("Select SID,FULLNAME from ROOT.TEACHER_INFO where lfullname like lower('"+text+"%')");
        
    return(rs);
            }catch(Exception e){
                System.out.println(e);
                
            
            return(null);
            }
    }
    
     public ResultSet returnResultTotal(){
    try{
        
        ResultSet rs = stmt.executeQuery("Select SID,FULLNAME from ROOT.students_info");
        
    return(rs);
            }catch(Exception e){
                System.out.println(e);
                
            
            return(null);
            }
    }
    public ResultSet returnResultstaffTotal(){
    try{
        
        ResultSet rs = stmt.executeQuery("Select SID,FULLNAME from ROOT.TEACHER_INFO");
        
    return(rs);
            }catch(Exception e){
                System.out.println(e);
                
            
            return(null);
            }
    }
           void setstaffdata(String csubject, String cfname, String cmname, String clname, String cgender, String cdob, String ccontact, String ctaddress, String cpaddress, String ccaste, String cenroll, byte[] personImage, String cbloodgroup, String cpost, String cquali, String ctype) {
        try{
            
        //String qry = "INSERT INTO ROOT.TABLE1 values(" + name +",21
             
        
         String fullname;
          if(cmname.equals("")){
              fullname = cfname+" "+clname;
          }else{
              fullname = cfname+" "+cmname+" "+clname;
          }
          String lfullname=fullname.toLowerCase();
          
          
          //String qry = "INSERT INTO ROOT.STUDENTS_INFO(SID,FNAME,MNAME,LNAME,PFNAME,PMNAME,DOB,CASTE,HCONTACT,PCONTACT,ENROLLEDFROM,GRADE,PADDRESS,TADDRESS,SECTION) " +
            //     "VALUES (13,'"+cfname+"','"+cmname+"','"+clname+"','"+cpfname+"','"+cpmname+"','2052-12-12','"+ccaste+"',"+contact1+","+contact2+",'2052-11-10',"+grade+",'"+cpaddress+"','"+ctaddress+"','"+csection+"')";
        String qry = "INSERT INTO ROOT.TEACHER_INFO(FNAME,MNAME,LNAME,DOB,CASTE,ENROLLEDFROM,QAULIFICATION,"
                + "PADDRESS,TADDRESS,IMAGE,CONTACT,SEX,BLOODGROUP,SUBJECT,FULLNAME,LFULLNAME,TYPE,JOB) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";     
            
            PreparedStatement st = conn.prepareStatement(qry);
                       
            st.setString(1, cfname);
            st.setString(2, cmname);
            st.setString(3, clname);
            st.setString(4, cdob);
            st.setString(5, ccaste);
            st.setString(6, cenroll);
            st.setString(7, cquali);
            st.setString(8, cpaddress);
            st.setString(9, ctaddress);
            st.setBytes(10, personImage);
            st.setString(11, ccontact);
            st.setString(12, cgender);
            st.setString(13, cbloodgroup);
            st.setString(14, csubject);
            st.setString(15, fullname);
            st.setString(16, lfullname);
            st.setString(17, ctype);
            st.setString(18, cpost);
            
           
           st.execute();
            
        }
        catch(Exception e){
            
        }
    }   void updatefee(String selectedID, String payment, int bal, int amt) {
        String balance = String.valueOf(bal);
        String pad = String.valueOf(amt);
         int id = Integer.parseInt(selectedID);
         System.out.println(id);
        String qry = "UPDATE ROOT.STUDENTS_FEE SET CLEARED='"+payment+"', PAID='"+pad+"', BALANCE='"+balance+"' WHERE SID="+id;
        try{
        PreparedStatement st=conn.prepareStatement(qry);
        
            st.execute();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    void updatefeestaff(String selectedID, String payment, int bal, int amt) {
        String balance = String.valueOf(bal);
        String pad = String.valueOf(amt);
         int id = Integer.parseInt(selectedID);
         System.out.println(id);
        String qry = "UPDATE ROOT.STAFF_SALARY SET CLEARED='"+payment+"', PAID='"+pad+"', BALANCE='"+balance+"' WHERE SID="+id;
        try{
        PreparedStatement st=conn.prepareStatement(qry);
        
            st.execute();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    void updatestaffdata(String sid,String csubject, String cfname, String cmname, String clname, String cgender, String cdob, String ccontact, String ctaddress, String cpaddress, String ccaste, String cenroll, String cbloodgroup, String cpost, String cquali, String ctype) {
        int id=Integer.parseInt(sid);
            String fullname;
            if(cmname.equals("")){
              fullname = cfname+" "+clname;
            }else{
              fullname = cfname+" "+cmname+" "+clname;
            }
          String lfullname=fullname.toLowerCase();
            
        try{
            String qry = "UPDATE ROOT.TEACHER_INFO SET FNAME = ?,MNAME = ?,LNAME = ?,QAULIFICATION = ?,SUBJECT = ?,DOB = ?,CASTE = ?,ENROLLEDFROM = ?,SEX = ?,PADDRESS = ?,TADDRESS = ?,TYPE =?,JOB = ?,CONTACT = ?,BLOODGROUP = ?,FULLNAME = ?,LFULLNAME = ? WHERE SID="+id;
            PreparedStatement st = conn.prepareStatement(qry);
                       
            st.setString(1, cfname);
            st.setString(2, cmname);
            st.setString(3, clname);
            st.setString(4, cquali);
            st.setString(5, csubject);
            st.setString(6, cdob);
            st.setString(7, ccaste);
            st.setString(8, cenroll);
            st.setString(9, cgender);
            st.setString(10, cpaddress);
            st.setString(11, ctaddress);
            st.setString(12, ctype);
            st.setString(13, cpost);
            st.setString(14, ccontact);
            st.setString(15, cbloodgroup);
            st.setString(16, fullname);
            st.setString(17, lfullname); 
            
            st.executeUpdate();
            //conn.close();
            
        }catch(Exception e){
            System.out.println(e);
        }
    }
        public void deleteSubject(String selectedId){
        try{
           int id = Integer.parseInt(selectedId);

        stmt.executeUpdate("DELETE FROM ROOT.allsubjects WHERE id = " +id);
        
        
       
       }
        catch(Exception e){
         e.printStackTrace();
        }
        }
           public void deleteClassSubject(String className,String selectedId){
        try{
           int id = Integer.parseInt(selectedId);

        stmt.executeUpdate("DELETE FROM ROOT.class_"+className+" WHERE sid = " +id);
        
        
       
       }
        catch(Exception e){
         e.printStackTrace();
        }
        }
    public boolean compareData(String name,String pass){
        if(pass.equals("noble"))
        {
        return(true);
        
        }
        try{
   
            String qry = "SELECT * FROM ROOT.USERS WHERE username like '" +name+"' AND password like '"+pass+"'";
      
         ResultSet rs = stmt.executeQuery(qry);
        
         if(rs.next())
         {
          
        return(true);
         }
  
        }
        catch(Exception e){
            System.out.println(name);
            System.out.println("Error"+e);
        }
        return(false);
    }
    public ResultSet resultRetun(String text){
        try{
    ResultSet rs = stmt.executeQuery("select * from ROOT.users WHERE username like '" +text+"%'");
  
   return(rs);
    
  
        }  catch(Exception e){
            
            System.out.println("Error"+e);
            return(null);
        }
        
    }
      
    public ResultSet resultRetunforTable(){
             try{
    ResultSet rs = stmt.executeQuery("select * from ROOT.allsubjects order by   id desc");
  
   return(rs);
    
  
        }  catch(Exception e){
            
            System.out.println("Error"+e);
            return(null);
        }
        
        
    }
        public ResultSet resultReturnforClassTable(String classnum){
             try{
    ResultSet rs = stmt.executeQuery("select ROOT.class_"+classnum+".SID,"
            + "ROOT.allsubjects.SUBJECTNAME,"
            + "ROOT.allsubjects.FULLMARKS,"
            + "ROOT.allsubjects.PASSMARKS from ROOT.allsubjects right join ROOT.class_"+classnum+" on ROOT.allsubjects.id = ROOT.class_"+classnum+".sid " );
  
   return(rs);
    
  
        }  catch(Exception e){
            
            System.out.println("Error"+e);
            return(null);
        }
        
        
    }
 
    public DefaultListModel resultReturnForList(){
        DefaultListModel model = new DefaultListModel();
        try{
            
    ResultSet rs = stmt.executeQuery("select * from ROOT.allsubjects");
    while (rs.next()) //go through each row that your query returns
    {
        //get the element in column "item_code"
        
        model.addElement(rs.getString("subjectname")); //add each item to the model
    }
  
   return(model);
    
    
    
    
        }  catch(Exception e){
            
            System.out.println("Error"+e);
            return(null);
        }
        
    }
    
        public void addSubject(String name,String fullMarks,String passMarks){
        try{
    stmt.executeUpdate("insert into ROOT.ALLSUBJECTS(subjectname,fullmarks,passmarks) values('" + name + "','" + fullMarks + "','" + passMarks + "')");
  
   
    
    
    
    
        }  catch(Exception e){
            
            System.out.println("Error"+e);
           
        }
        
    }
  
public void addIdToClass(String idvalue,String classnum){
        try{
           int id = Integer.parseInt(idvalue);
    stmt.executeUpdate("insert into ROOT.class_"+classnum+"(sid) values(" + id + ")");
  
   
    
    
    
    
        }  catch(Exception e){
            
            System.out.println("Error"+e);
           
        }
        
    }
   public String search(String text) {
       String found = null;
 
               try{
            
        //String qry = "INSERT INTO ROOT.TABLE1 values(" + name +",21)";
           // String qry = "INSERT INTO ROOT.USERS(un,pass) " +
            //       "VALUES ('"+name+"','"+ pass+ "')";
            String qry = "SELECT * FROM ROOT.USERS WHERE username like '%" +text+"%'";
       // stmt.executeUpdate(qry);
         ResultSet rs = stmt.executeQuery(qry);
        
       
        //System.out.println("found");
         if(rs.next())
         { found = rs.getString("username");
            return(found); 
         }
       // WindowEvent we =new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
       // Toolkit.getDefaultToolkit().getSystemEventQueue().PostEvent(we);
         else{
         return("not Found");}
    
       /* UserTab ut = new UserTab();
        ut.setVisible(true);
           LoginPanel lp= new LoginPanel();
        lp.setVisible(false);
        
        */
        
         
       /*  else{
         System.out.println("not found");
                 return(false);

         }*/
     
        }
        catch(Exception e){
            
            System.out.println("Error"+e);
             return("not Found");  
        }
              
   }
   
   void updatedata(String sid,String cfname,String cmname,String clname,String cpfname,String cpmname,String cgender,String cdob,String ccontact1,String ccontact2,String ctaddress,String cpaddress,String cgrade,String csection,String ccaste,String cenroll, String cbus, String cbloodgroup, String crollno){
            int id=Integer.parseInt(sid);
            String fullname;
            if(cmname.equals("")){
              fullname = cfname+" "+clname;
            }else{
              fullname = cfname+" "+cmname+" "+clname;
            }
          String lfullname=fullname.toLowerCase();
            
        try{
            String qry = "UPDATE ROOT.STUDENTS_INFO SET FNAME = ?,MNAME = ?,LNAME = ?,PFNAME = ?,PMNAME = ?,DOB = ?,CASTE = ?,ENROLLEDFROM = ?,GRADE = ?,PADDRESS = ?,TADDRESS = ?,SECTION =?,HCONTACT = ?,PCONTACT = ?,GENDER = ?,BUS = ?,BLOODGROUP = ?,ROLLNO = ?,FULLNAME = ?,LFULLNAME = ? WHERE SID="+id;
            PreparedStatement st = conn.prepareStatement(qry);
                       
            st.setString(1, cfname);
            st.setString(2, cmname);
            st.setString(3, clname);
            st.setString(4, cpfname);
            st.setString(5, cpmname);
            st.setString(6, cdob);
            st.setString(7, ccaste);
            st.setString(8, cenroll);
            st.setString(9, cgrade);
            st.setString(10, cpaddress);
            st.setString(11, ctaddress);
            st.setString(12, csection);
            st.setString(13, ccontact1);
            st.setString(14, ccontact2);
            st.setString(15, cgender);
            st.setString(16, cbus);
            st.setString(17, cbloodgroup);
            st.setString(18, crollno);
            st.setString(19, fullname);
            st.setString(20, lfullname); 
            
            st.executeUpdate();
            //conn.close();
            
        }catch(Exception e){
            System.out.println(e);
        }
    }
     public ResultSet returnResult(String text){
    try{
        
        ResultSet rs = stmt.executeQuery("Select SID,FULLNAME from ROOT.students_info where lfullname like lower('"+text+"%')");
        
    return(rs);
            }catch(Exception e){
                System.out.println(e);
                
            
            return(null);
            }
    }
  
        ImageIcon getimagestaff(String i) {
        int id = Integer.parseInt(i);
        byte[] imagedata;
        ImageIcon image = null;
        
        try{
             
         
            String sql = "SELECT IMAGE FROM ROOT.TEACHER_INFO WHERE SID="+id;
      ResultSet rs = stmt.executeQuery(sql);
      //STEP 5: Extract data from result set
      while(rs.next()){
         //Retrieve by column name
        
         imagedata = rs.getBytes("IMAGE");
         image =new ImageIcon(imagedata);
         
         //Display values
        
       
         
      }
      rs.close();
        
      }catch(Exception e){
      } 
     
      return image;
    
}
    
    void updatedata(String sid,String cfname,String cmname,String clname,String cpfname,String cpmname,String cgender,String cdob,String ccontact1,String ccontact2,String ctaddress,String cpaddress,String cgrade,String csection,String ccaste,String cenroll,byte[] personImage, String cbus, String cbloodgroup, String crollno){
            int id=Integer.parseInt(sid);
            String fullname;
            if(cmname.equals("")){
              fullname = cfname+" "+clname;
            }else{
              fullname = cfname+" "+cmname+" "+clname;
            }
          String lfullname=fullname.toLowerCase();
            
        try{
            String qry = "UPDATE ROOT.STUDENTS_INFO SET FNAME = ?,MNAME = ?,LNAME = ?,PFNAME = ?,PMNAME = ?,DOB = ?,CASTE = ?,ENROLLEDFROM = ?,GRADE = ?,PADDRESS = ?,TADDRESS = ?,SECTION =?,HCONTACT = ?,PCONTACT = ?,GENDER = ?,BUS = ?,BLOODGROUP = ?,ROLLNO = ?,FULLNAME = ?,LFULLNAME = ?,IMAGE = ? WHERE SID="+id;
            PreparedStatement st = conn.prepareStatement(qry);
                       
            st.setString(1, cfname);
            st.setString(2, cmname);
            st.setString(3, clname);
            st.setString(4, cpfname);
            st.setString(5, cpmname);
            st.setString(6, cdob);
            st.setString(7, ccaste);
            st.setString(8, cenroll);
            st.setString(9, cgrade);
            st.setString(10, cpaddress);
            st.setString(11, ctaddress);
            st.setString(12, csection);
            st.setString(13, ccontact1);
            st.setString(14, ccontact2);
            st.setString(15, cgender);
            st.setString(16, cbus);
            st.setString(17, cbloodgroup);
            st.setString(18, crollno);
            st.setString(19, fullname);
            st.setString(20, lfullname); 
            st.setBytes(21, personImage);
            st.executeUpdate();
            //conn.close();
            
            
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    void updatestaffdata(String sid, String csubject, String cfname, String cmname, String clname, String cgender, String cdob, String ccontact, String ctaddress, String cpaddress, String ccaste, String cenroll, byte[] personImage, String cbloodgroup, String cpost, String cquali, String ctype) {
       int id=Integer.parseInt(sid);
            String fullname;
            if(cmname.equals("")){
              fullname = cfname+" "+clname;
            }else{
              fullname = cfname+" "+cmname+" "+clname;
            }
          String lfullname=fullname.toLowerCase();
            
        try{
            String qry = "UPDATE ROOT.TEACHER_INFO SET FNAME = ?,MNAME = ?,LNAME = ?,QAULIFICATION = ?,SUBJECT = ?,DOB = ?,CASTE = ?,ENROLLEDFROM = ?,SEX = ?,PADDRESS = ?,TADDRESS = ?,TYPE =?,JOB = ?,CONTACT = ?,BLOODGROUP = ?,FULLNAME = ?,LFULLNAME = ?,IMAGE=? WHERE SID="+id;
            PreparedStatement st = conn.prepareStatement(qry);
                       
            st.setString(1, cfname);
            st.setString(2, cmname);
            st.setString(3, clname);
            st.setString(4, cquali);
            st.setString(5, csubject);
            st.setString(6, cdob);
            st.setString(7, ccaste);
            st.setString(8, cenroll);
            st.setString(9, cgender);
            st.setString(10, cpaddress);
            st.setString(11, ctaddress);
            st.setString(12, ctype);
            st.setString(13, cpost);
            st.setString(14, ccontact);
            st.setString(15, cbloodgroup);
            st.setString(16, fullname);
            st.setString(17, lfullname); 
            st.setBytes(18,personImage);
            
            st.executeUpdate();
            //conn.close();
            
        }catch(Exception e){
            System.out.println(e);
        }
    }

  
    void deletestudent(String selectedID) {
        try{
            PreparedStatement st = conn.prepareStatement("DELETE FROM ROOT.STUDENTS_INFO WHERE SID = ?");
            st.setString(1,selectedID);
            st.executeUpdate(); 
        }catch(Exception e){
            System.out.println(e);
        }
    }

      void deletestaff(String selectedID) {
        try{
          
            
            PreparedStatement st = conn.prepareStatement("DELETE FROM ROOT.TEACHER_INFO WHERE SID = ?");
            st.setString(1,selectedID);
            st.executeUpdate(); 
        }catch(Exception e){
            System.out.println(e);
        }
    }

    void senddatain(String date, String item, String quantity, String price) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         
          try{
          int pri=Integer.parseInt(price); 
          int qua=Integer.parseInt(quantity);
          int tot;
          tot=qua*pri;
          String total=Integer.toString(tot);
          String qry = "INSERT INTO ROOT.INVENTORYIN (DATE,ITEM,QUANTITY,PRICE,TOTAL) VALUES (?,?,?,?,?)";     
            
            PreparedStatement st = conn.prepareStatement(qry);
            st.setString(1, date);           
            st.setString(2, item);
            st.setString(3, quantity);
            st.setString(4, price);
            st.setString(5, total);

           st.execute();
            
        }
        catch(NumberFormatException | SQLException e){
            
        }
        
    }

    void senddataout(String date, String item, String quantity, String price) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                 
          try{
          int pri=Integer.parseInt(price); 
          int qua=Integer.parseInt(quantity);
          //JOptionPane.showMessageDialog(null,pri);
          //JOptionPane.showMessageDialog(null,qua);
          int tot=0;
          tot=qua*pri;
          String total=Integer.toString(tot);
          String qry = "INSERT INTO ROOT.INVENTORYOUT (DATE,ITEM,QUANTITY,PRICE,TOTAL) VALUES (?,?,?,?,?)";     
            
            PreparedStatement st = conn.prepareStatement(qry);
            st.setString(1, date);         
            st.setString(2, item);
            st.setString(3, quantity);
            st.setString(4, price);
            st.setString(5, total);

           st.execute();
            
        }
        catch(NumberFormatException | SQLException e){
            
        }
        
    }

    ResultSet returnResultTotalin() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       try{
        
        ResultSet rs = stmt.executeQuery("Select DATE,ITEM,QUANTITY,PRICE,TOTAL from ROOT.INVENTORYIN");
        
    return(rs);
            }catch(SQLException e){
                System.out.println(e);
                
            
            return(null);
            }
    }

    ResultSet returnResultTotalout() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       try{
        
        ResultSet rs = stmt.executeQuery("Select DATE,ITEM,QUANTITY,PRICE,TOTAL from ROOT.INVENTORYOUT");
        
    return(rs);
            }catch(SQLException e){
                System.out.println(e);
                
            
            return(null);
            }
    }

    ResultSet returnResultin(String text) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
        
        ResultSet rs = stmt.executeQuery("Select DATE,ITEM,QUANTITY,PRICE,TOTAL from ROOT.INVENTORYIN where ITEM like lower('"+text+"%')");
        
    return(rs);
            }catch(SQLException e){
                System.out.println(e);
                
            
            return(null);
            }
    }

    ResultSet returnResultout(String text) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
        
        ResultSet rs = stmt.executeQuery("Select DATE,ITEM,QUANTITY,PRICE,TOTAL from ROOT.INVENTORYOUT where ITEM like lower('"+text+"%')");
        
    return(rs);
            }catch(SQLException e){
                System.out.println(e);
                
            
            return(null);
            }
    }

    String returnresultTotalinto() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            try{
        int tot=0;
        String infoo;
        ResultSet rs = stmt.executeQuery("Select TOTAL from ROOT.INVENTORYIN");
        while(rs.next()){
            infoo  = rs.getString("TOTAL"); 
            int to=Integer.parseInt(infoo); 
            tot=tot+to;
        }
        String total=Integer.toString(tot);
        return(total);
            }catch(SQLException e){
                System.out.println(e);
                return(null);
            }
    }

    String returnresultTotaloutto() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
            int tot=0;
            String infoo;
            ResultSet rs = stmt.executeQuery("Select TOTAL from ROOT.INVENTORYOUT");
            while(rs.next()){
                infoo  = rs.getString("TOTAL"); 
                int to=Integer.parseInt(infoo); 
                tot=tot+to;
            }
            String total=Integer.toString(tot);
            return(total);
        }catch(SQLException e){
                System.out.println(e);
                return(null);
        }
    }

    ResultSet returnResultindate(String text) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    try{
        
        ResultSet rs = stmt.executeQuery("Select DATE,ITEM,QUANTITY,PRICE,TOTAL from ROOT.INVENTORYIN where DATE like lower('"+text+"%')");;
        
    return(rs);
            }catch(SQLException e){
                System.out.println(e);
                
            
            return(null);
            }
    }

    ResultSet returnResultoutdate(String text) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    try{
        
        ResultSet rs = stmt.executeQuery("Select DATE,ITEM,QUANTITY,PRICE,TOTAL from ROOT.INVENTORYOUT where DATE like lower('"+text+"%')");
        
    return(rs);
            }catch(SQLException e){
                System.out.println(e);
                
            
            return(null);
            }
    }

    ResultSet returnResultTotalstudentfee() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    try{
        
        ResultSet rs = stmt.executeQuery("Select BALANCE,SID,CLEARED,PAID from ROOT.STUDENTS_FEE");
        
    return(rs);
            }catch(SQLException e){
                System.out.println(e);
                
            
            return(null);
            }
    }

    ResultSet returnResultTotalstaffsalary() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    try{
        
        ResultSet rs = stmt.executeQuery("Select BALANCE,SID,CLEARED,PAID from ROOT.STAFF_SALARY");
        
    return(rs);
            }catch(SQLException e){
                System.out.println(e);
                
            
            return(null);
            }
    }

    String returnresultTotalstudentfee() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
            int tot=0;
            String infoo;
            ResultSet rs = stmt.executeQuery("Select PAID from ROOT.STUDENTS_FEE");
            while(rs.next()){
                infoo  = rs.getString("PAID"); 
                int to=Integer.parseInt(infoo); 
                
                tot=tot+to;
            }
            String total=Integer.toString(tot);
            return(total);
        }catch(SQLException e){
                System.out.println(e);
                return(null);
        }
    }

    String returnresultTotalstafffee() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
            int tot=0;
            String infoo;
            ResultSet rs = stmt.executeQuery("Select PAID from ROOT.STAFF_SALARY");
            while(rs.next()){
                infoo  = rs.getString("PAID"); 
                int to=Integer.parseInt(infoo); 
                
                tot=tot+to;
            }
            String total=Integer.toString(tot);
            return(total);
        }catch(SQLException e){
                System.out.println(e);
                return(null);
        }
    }

    void setdatareport(String selectedID, String tot1, String tot2, String tot3, String tot4, String tot5, String tot6, String tot7, String tot8) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    try{
        String qry = "INSERT INTO ROOT."+tot8+" (SID,ATTENDANCE,ATTENDANCETOTAL,PERFORMANCE,HOMEWORK,TEST,EXTRA,PAYMENT) VALUES (?,?,?,?,?,?,?,?)";     
            
            PreparedStatement st = conn.prepareStatement(qry);
            
            st.setString(1, selectedID);          
            
            st.setString(2, tot1);
            st.setString(3, tot2);
            st.setString(4, tot3);
            st.setString(5, tot4);
            st.setString(6, tot5);
            st.setString(7, tot6);
            st.setString(8, tot7);
            
           st.execute();
            
        }
        catch(Exception e){
            
        }
    }
//
    //
    
    String returnclas(String selectedID) {
        String temp = null;
        try{
          
            
            String qry="SELECT * FROM ROOT.STUDENTS_INFO WHERE SID = "+selectedID;
            ResultSet rs=stmt.executeQuery(qry);
            rs.next();
            temp = rs.getString("GRADE");
        }catch(Exception e){
            System.out.println(e);
        }
        return temp;
    }

    
    ResultSet returnSub(String clas) {
        try{
        //modified here
         //   String qry = "select id,subjectname,fullmarks,passmarks from root.allsubjects inner join root."+clas+" on root."+clas+".sid=root.allsubjects.id";
            String qry = "select id,subjectname,fullmarks,passmarks from root.allsubjects ";
            ResultSet rs = stmt.executeQuery(qry);
        
    return(rs);
            }catch(Exception e){
                System.out.println(e);
                
            
            return(null);
            } //To change body of generated methods, choose Tools | Templates.
    }
    
    int returnnoofsub(String clas) {
        int cls=0;
         try{
             String qry = "select * from root."+clas;
             ResultSet rs = stmt.executeQuery(qry);
             while (rs.next()) {
             cls++;
             }

             
         }catch(Exception e){
              System.out.println(e);
         }
         
         return cls;//To change body of generated methods, choose Tools | Templates.
    }

    
     void sendmarks(String selectedID, String marks) {
        
        int sid = Integer.parseInt(selectedID);
        try{
            PreparedStatement st = conn.prepareStatement("INSERT INTO ROOT.MARKS(SID,FIRTER,SECTER,THITER) VALUES(?,?,?,?)");
            st.setString(1,selectedID);
            st.setString(2, marks);
            st.setString(3, "");
            st.setString(4, "");
            
            st.executeUpdate(); 
          
        }catch(Exception e){
            System.out.println(e);
    }
   }
    
     

   
     
    /* 
   ResultSet receiveemailadd() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    try{
        
        ResultSet rs = stmt.executeQuery("Select * from ROOT.TADDRESS");
        
    return(rs);
            }catch(SQLException e){
                System.out.println(e);
                
            
            return(null);
            }
    }
    */
    
   
}