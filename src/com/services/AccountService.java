package com.services;

import com.model.AccountModel;
import com.app.util.DbConnection;
import com.dao.AccountDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountService extends DbConnection implements AccountDAO {
    
    @Override
    public List<AccountModel> getAllAccounts() {
        String query = "SELECT * FROM tbl_account";
        List<AccountModel> accounts = new ArrayList<>();
        
        try {
            connect();
            state = connect.createStatement();
            result = state.executeQuery(query);
            while (result.next()) {
                accounts.add(new AccountModel(result.getInt("user_id"),
                        result.getString("user_name"),
                        result.getString("user_pass"),
                        result.getString("user_firstname"), 
                        result.getString("user_lastname"),
                        result.getString("user_address"),
                        result.getString("user_contact_no"),
                        result.getBoolean("user_is_admin")));
            }
        } catch (SQLException e) {
            System.out.println("AccountService: getAllAccounts() " + e.getMessage());
        } 
        return accounts;
    }
    
    @Override
    public void addAccount(AccountModel account) throws SQLException {
        String query = "INSERT INTO tbl_account (user_name, user_pass, user_firstname, user_lastname,"
                + "user_address, user_contact_no, user_is_admin)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try {
            connect();
            prepare = connect.prepareStatement(query);
            prepare.setString(1, account.getUserName());
            prepare.setString(2, account.getPass());
            prepare.setString(3, account.getFirstName());
            prepare.setString(4, account.getLastName());
            prepare.setString(5, account.getAddress());
            prepare.setString(6, account.getContactNo());
            prepare.setBoolean(7, account.isAdmin());
            
            prepare.executeUpdate();
        } catch (SQLException e) {
            System.out.println("AccountService: addAccount()" + e.getMessage());
        }
    }
    
    @Override
    public List<AccountModel> getByFirstName(String userFirstName) {
    List<AccountModel> accounts = new ArrayList<>();
        String query = "SELECT * FROM tbl_account where user_firstname like ?";
        try {
            connect();
            prepare = connect.prepareStatement(query);
            prepare.setString(1, "%" + userFirstName + "%");
            
            result = prepare.executeQuery();
            
           while(result.next()){
               AccountModel accModel = new AccountModel();
                accModel.setId(result.getInt("user_id"));
                accModel.setFirstName(result.getString("user_firstname"));
                accModel.setLastName(result.getString("user_lastname"));
                accModel.setAddress(result.getString("user_address"));
                accModel.setContactNo(result.getString("user_contact_no"));
                accModel.setIsAdmin(result.getBoolean("user_is_admin"));;
                accModel.setUserName(result.getString("user_name"));
                accModel.setPass(result.getString("user_pass"));
                accounts.add(accModel);
           }
        } catch (SQLException e) {
            System.out.println("AccountService: getByFirstName() " + e.getMessage());
        } 
        return accounts;
    }

    @Override
    public void updateAccount(int id,String fieldName, String newInfo) {
        String query = "UPDATE tbl_account SET " + fieldName + " = ? WHERE user_id = ?";
        
        try {
            connect();
            prepare = connect.prepareStatement(query);
            prepare.setString(1, newInfo);
            prepare.setInt(2, id);
            prepare.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("AccountService: updateAccount()" + e.getMessage());
        }
    }

    @Override
    public void deleteAccount(int id) throws SQLException{
        String query = "DELETE FROM tbl_account WHERE user_id = ?";
            
        try {
            connect();
            prepare = connect.prepareStatement(query);
            prepare.setInt(1, id);
            prepare.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("AccountService: deleteAccount()" + e.getMessage());
        }
    }

    @Override
    public AccountModel getUserByCredentials(String userName, String password) {
        String query = "SELECT * FROM tbl_account WHERE user_name = ? AND user_pass = ?";
        
        try  {
            connect();
            prepare = connect.prepareStatement(query);
            prepare.setString(1, userName);
            prepare.setString(2, password);
            result = prepare.executeQuery();
            if (result.next()) {
                return new AccountModel(
                        result.getString("user_name"),
                        result.getString("user_pass"), 
                        result.getBoolean("user_is_Admin"));
            }
        } catch (SQLException e) {
            System.out.println("AccountService: getUserByCredentials() " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean isUsernameTaken(String userName) {
        String query = "SELECT user_name FROM tbl_account WHERE user_name = ?";
        
        try {
            connect();
            prepare = connect.prepareStatement(query);
            prepare.setString(1, userName);
            result = prepare.executeQuery();
            return result.next();
            
        } catch (SQLException e) {
            System.out.println("AccountService: isUsernameTaken() " + e.getMessage());
        }
        return false;
    }
}
    
    