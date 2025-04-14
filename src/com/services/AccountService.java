
package com.services;

import com.app.util.DbConnection;
import com.model.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AccountService implements CrudService<Account> {
    private final DbConnection dbConnection = new DbConnection();

    @Override
    public void add(Account account) {
        String query = "INSERT INTO tbl_account (user_name, user_pass, user_firstname, user_lastname, " +
                       "user_address, user_contact_no, user_is_admin) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dbConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, account.getUserName());
            stmt.setString(2, account.getUserPass());
            stmt.setString(3, account.getUserFirstname());
            stmt.setString(4, account.getUserLastname());
            stmt.setString(5, account.getUserAddress());
            stmt.setString(6, account.getUserContactNo());
            stmt.setBoolean(7, account.isUserIsAdmin());

            stmt.executeUpdate();
            System.out.println("Account added to database.");
        } catch (Exception e) {
            System.out.println("Error adding account: " + e.getMessage());
        }
    }

    @Override
    public List<Account> getAll() {
        List<Account> accounts = new ArrayList<>();
        String query = "SELECT * FROM tbl_account";

        try (Connection conn = dbConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Account acc = new Account(
                    rs.getInt("user_id"),
                    rs.getString("user_name"),
                    rs.getString("user_pass"),
                    rs.getString("user_firstname"),
                    rs.getString("user_lastname"),
                    rs.getString("user_address"),
                    rs.getString("user_contact_no"),
                    rs.getBoolean("user_is_admin")
                );
                accounts.add(acc);
            }
        } catch (Exception e) {
            System.out.println("Error retrieving account: " + e.getMessage());
        }
        return accounts;
    }

    @Override
    public Account getById(int id) {
        String query = "SELECT * FROM tbl_account WHERE user_id = ?";
        try (Connection conn = dbConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Account(
                    rs.getInt("user_id"),
                    rs.getString("user_name"),
                    rs.getString("user_pass"),
                    rs.getString("user_firstname"),
                    rs.getString("user_lastname"),
                    rs.getString("user_address"),
                    rs.getString("user_contact_no"),
                    rs.getBoolean("user_is_admin")
                );
            }
        } catch (Exception e) {
            System.out.println("Error fetching account: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void updateItem(Account account) {
        String query = "UPDATE tbl_account SET user_name=?, user_pass=?, user_firstname=?, " +
                       "user_lastname=?, user_address=?, user_contact_no=?, user_is_admin=? WHERE user_id=?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, account.getUserName());
            stmt.setString(2, account.getUserPass());
            stmt.setString(3, account.getUserFirstname());
            stmt.setString(4, account.getUserLastname());
            stmt.setString(5, account.getUserAddress());
            stmt.setString(6, account.getUserContactNo());
            stmt.setBoolean(7, account.isUserIsAdmin());
            stmt.setInt(8, account.getUserId());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Account updated.");
            } else {
                System.out.println("Account not found.");
            }
        } catch (Exception e) {
            System.out.println("Error updating account: " + e.getMessage());
        }
    }

    @Override
    public void deleteItem(int id) {
        String query = "DELETE FROM tbl_account WHERE user_id = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Account deleted.");
            } else {
                System.out.println("Account not found.");
            }
        } catch (Exception e) {
            System.out.println("Error deleting book: " + e.getMessage());
        }
    }
}
