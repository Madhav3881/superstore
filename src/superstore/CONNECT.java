/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superstore;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author madha
 */
public class CONNECT {
    
     Connection conn = null;

    public static Connection ConnecterDb() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smh","root","root");
            return conn;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
            
        }
        return null;
        
    }

}
