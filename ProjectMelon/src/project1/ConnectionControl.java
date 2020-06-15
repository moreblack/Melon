package project1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionControl {
    Connection conn = null;
    public static Connection conDB()
    {
        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/keeptoo_systems", "root", "");
        	Class.forName("oracle.jdbc.driver.OracleDriver");
        	Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
            return con;
        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println("ConnectionUtil : "+ex.getMessage());
           return null;
        }
    }
   
}
