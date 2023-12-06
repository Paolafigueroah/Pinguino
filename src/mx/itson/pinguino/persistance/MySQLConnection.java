/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.pinguino.persistance;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author Paola Figueroa
 */
public class MySQLConnection {
    
   public static Connection get(){
      Connection connection = null;
      try{
        connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/pinguinodb?user=root&password=262712paofh");
          
      } catch (Exception ex){
          System.err.print("Error: "+ ex.getMessage());
      }  
      return connection;
   } 
    
}