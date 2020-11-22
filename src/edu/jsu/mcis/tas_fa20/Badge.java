/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.jsu.mcis.tas_fa20;
import java.sql.*;
/**
 * @author John
 */
public class Badge {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost:3306/Tas";
   static final String USER = "tasuser";
   static final String PASS = "cs310groupf";
   
    public static void main(String args[]){
    Connection conn = null;
   Statement stmt = null;
   try{
       Class.forName("com.mysql.jdbc.Driver");
       System.out.println("Connecting to a selected database...");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      System.out.println("Connected database successfully...");
      System.out.println("Creating statement...");
      stmt = conn.createStatement();
      String sql = "SELECT firstname, middlename, lastname FROM employee";
      ResultSet rs = stmt.executeQuery(sql);
      while(rs.next()){
         //Retrieve by column name
         String first  = rs.getString("firstname");
         String middle = rs.getString("middlename");
         String last = rs.getString("lastname");

         //Display values
         System.out.print("Firstname: " + first);
         System.out.print(", Middlename: " + middle);
         System.out.println(", Lastname: " + last);
      }
      rs.close();
   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            conn.close();
      }catch(SQLException se){
      }
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
    }
}
