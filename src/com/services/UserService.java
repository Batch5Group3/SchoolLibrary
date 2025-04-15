package com.services;

import com.model.User;
import com.app.util.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserService {
    private final DbConnection db = new DbConnection();

    public User getUserByCredentials(String username, String password) {
        String query = "SELECT * FROM tbl_account WHERE user_name = ? AND user_pass = ?";
        try (Connection conn = db.connect();
             PreparedStatement ps = conn.prepareStatement(query)) {
             
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new User(rs.getString("user_name"), rs.getString("user_name"));
            }

        } catch (Exception e) {
            System.out.println("Error fetching user: " + e.getMessage());
        }

        return null;
    }
}
