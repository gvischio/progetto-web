/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.database;

/**
 *
 * @author Mike_TdT
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Database {  
    private String host = "95.244.184.6"; 
    private int port = 3306;
    private String schema = "ssr_project";
    private String username = "root"; 
    private String password = "webproject";
      
    private Connection conn = null;
    
    
    public Database() {  
        connect();
    }  
  
    public String getHost() {  
        return host;  
    }  
  
    public int getPort() {  
        return port;  
    }  
  
    public String getSchema() {  
        return schema;  
    }  
      
    public String connect() {  
        try {  
            Class.forName("com.mysql.jdbc.Driver");  
            conn = DriverManager.getConnection(  
             "jdbc:mysql://" + host + ":" + port + "/" + schema + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Rome", username, password);  
            return "ok";
        } catch (Exception ex) {  
            return ex.getMessage();
        }        
    }      
      
    public void close() {  
        if (conn != null) try {  
            conn.close();  
        } catch (SQLException ex) {  
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);  
        }  
    }     
    
    public ResultSet executeQuery(String query)
    {
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            return ps.executeQuery();
        }
        catch (Exception ex) { System.err.println(ex.getMessage()); }
        return null;
    }
    
    public boolean executeUpdate(String query)
    {
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.executeUpdate();
            return true;
        }
        catch (Exception ex) { System.err.println(ex.getMessage()); }
        return false;
    }
    
    public Connection getConnection() {
        return conn;
    }
}  
