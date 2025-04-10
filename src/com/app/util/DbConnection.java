
package com.app.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public abstract class DbConnection {
    
    private final static String URL = "jdbc:mysql://localhost:3306/dbschoollib_ms";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "";
    private final static String DRIVER = "com.mysql.jdbc.Driver";
    public Connection connect;
    public Statement state;
    public ResultSet result;
    public PreparedStatement prepare;
    
    
    public void connect(){
        
        try {
            Class.forName(DRIVER);
            connect = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
