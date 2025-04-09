package com.app.util;

import java.sql.DriverManager;

public class DbConnection {
    String url = "jdbc:mysql://localhost:3306/dblib_ms";
    String passWord = "";
    String userName = "root";
    String jdbcDriver = "com.mysql.jdbc.Driver";
    
    public void connect() {
        try {
          Class.forName(jdbcDriver);
          DriverManager.getConnection(url, userName, passWord);
            System.out.println("Connected successfuly");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static void main(String[] args) {
        DbConnection db = new DbConnection();
        db.connect();
    }
}

