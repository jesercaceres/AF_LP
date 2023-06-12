package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class ConexaoDAO {
      
    /**
     *
     * @return
     */
    public Connection getconectaBD() {
        Connection conn = null;
        
        try {
            
            String url = "jdbc:mysql://localhost:3306/bancoteste?user=root&password=32471300";
            conn = DriverManager.getConnection(url);
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null,"ConexaoDAO" + erro.getMessage());
        }
        return conn;
    }
        
}
 