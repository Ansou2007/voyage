/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package voyage;

import com.mysql.jdbc.Connection;
import java.sql.*;


/**
 *
 * @author macair
 */
public class database {
    
    /*
    public static Connection connectDb(){
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/micda_voyage","admin","admin");
            return connect;
        }catch(Exception e){e.printStackTrace();}
            
            return null;
    }
*/
    public Connection con = null;
    
    public static Connection connexionDB(){
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:8889/micda_voyage","admin","admin");
            System.out.println("Connexion reussi");
            return con;
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Connexion echouée");
            return null;
        }
        
    } 
   
    
    
    
    
}
