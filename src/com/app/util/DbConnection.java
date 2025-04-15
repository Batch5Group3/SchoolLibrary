package com.app.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    String url = "jdbc:mysql://localhost:3306/dblib_ms";
    String passWord = "";
    String userName = "root";
    String jdbcDriver = "com.mysql.jdbc.Driver";
    
    public Connection connect() {
        Connection connection = null;
        try {
          Class.forName(jdbcDriver);
          connection = DriverManager.getConnection(url, userName, passWord);
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error establishing the connection: " + e.getMessage());
        }
        return connection;
    }  
}

