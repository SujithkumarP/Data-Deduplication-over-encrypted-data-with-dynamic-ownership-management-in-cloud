/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sujith;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
//import java.util.Properties;

/**
 *
 * @author Sujith
 */
public class Dbms {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Connection conn1 = null;
        Connection conn2 = null;
        Connection conn3 = null;
 
        try {
            // registers Oracle JDBC driver - though this is no longer required
            // since JDBC 4.0, but added here for backward compatibility
            Class.forName("oracle.jdbc.OracleDriver");
 
            // METHOD #1
            //DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
           /* String dbURL1 = "jjdbc:oracle:thin:@localhost:1521:XE";
            conn1 = DriverManager.getConnection(dbURL1);
            if (conn1 != null) {
                System.out.println("Connected with connection #1");
            }*/
          String dbURL2 = "jdbc:oracle:thin:@localhost:1521:XE";
            String username = "system";
            String password = "2606";
            conn2 = DriverManager.getConnection(dbURL2, username, password);
            if (conn2 != null) {
                System.out.println("Connected with connection #2");
            }
          /*  // METHOD #2
            String dbURL2 = "jdbc:oracle:thin:@localhost:1521:productDB";
            String username = "tiger";
            String password = "scott";
            conn2 = DriverManager.getConnection(dbURL2, username, password);
            if (conn2 != null) {
                System.out.println("Connected with connection #2");
            }
 
            // METHOD #3
            String dbURL3 = "jdbc:oracle:oci:@ProductDB";
            Properties properties = new Properties();
            properties.put("user", "tiger");
            properties.put("password", "scott");
            properties.put("defaultRowPrefetch", "20");
            conn3 = DriverManager.getConnection(dbURL3, properties);
 
            if (conn3 != null) {
                System.out.println("Connected with connection #3");
            }*/
        
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();}
        
    
  try
  { 
        Statement statement = conn2.createStatement();
  

	 

	        // Execute a SELECT query on Oracle Dummy DUAL Table. Useful for retrieving system values

	        // Enables us to retrieve values as if querying from a table

	        //ResultSet rs = statement.executeQuery("SELECT SYSDATE FROM DUAL");

                //ResultSet rss=statement.executeQuery("select * from bankcus");  

               //ResultSet rsss=statement.executeQuery("select * from bankcus");                  
                //if(rss!=null)
                //{
                //while(rss.next())  
                //System.out.println(rss.getInt(1)+"  "+rss.getString(2)+"  "+rss.getString(3));  }
              
              //rss.close();
              
  }catch(Exception ex)
  {
     System.out.println("Failed");
    }  
  ins aa=new ins();
  //aa.login();
  //aa.teacher();
}
}
class ins
{
     
        
    public void login(int idd,String hs)
    {
        Scanner sc=new Scanner(System.in);
     Connection con2=null;
     Connection con22=null;
            // int idd=1;
         //String hs="hello";
    
         try{
            
             
             System.out.println("hello");
 
            Class.forName("oracle.jdbc.OracleDriver");
 
            
             String dbURL2 = "jdbc:oracle:thin:@localhost:1521:XE";
            String username = "system";
            String password = "2606";
            con2 = DriverManager.getConnection(dbURL2, username, password);
            if (con2 != null) {
                System.out.println("Connected with connection #2");
            }
          
        
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();}
      String uname,password,utype;
      //System.out.println("Enter the Username,Password,Usertype");
      //uname=sc.next();
      //password=sc.next();
      //utype=sc.next();
      //String sql="insert into loginn(uname,password,utype) values(?,?,?)";
      String sql="insert into hashh(id,hs) values(?,?)";
      
      
      PreparedStatement pst = null;
         try {
             pst = con2.prepareStatement(sql);
             pst.setInt(1,idd);
              pst.setString(2,hs);
              //pst.setString(3,utype);
              pst.executeUpdate();
              
             
         } catch (Exception ex) {
             System.out.println("It failed");
             Logger.getLogger(ins.class.getName()).log(Level.SEVERE, null, ex);
         }
         
       
      
      
      
      
      
}
}