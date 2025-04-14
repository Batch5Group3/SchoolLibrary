
package com.app.controller;

import com.app.model.AccountModel;
import com.app.util.DbConnection;
import com.app.view.AccountView;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;


public class AccountController extends DbConnection implements CrudService<AccountModel> {

    
    @Override
    public void add(AccountModel account) {
        AccountView accView = new AccountView();
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
            prepare.setBoolean(7, account.isIsAdmin());
            
            if (account.getFirstName().isEmpty() || account.getLastName().isEmpty() || account.getUserName().isEmpty() || account.getPass().isEmpty())  {
                 String errorMsg = "Fields cannot be empty. Please try again!";
                 System.out.println(errorMsg);
                 accView.addAccount();
            } else {
                prepare.executeUpdate();
                System.out.println("Welcome " + account.getFirstName() + "! You successfully created your account.");
                
                connect.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public List<AccountModel> getAll() {
        return null;
    }


    @Override
    public void update(int id,String fieldName, String newInfo) {
        String query = "UPDATE tbl_account SET " + fieldName + " = ? WHERE user_id = ?";
        
        try {
            connect();
            // execute query
            prepare = connect.prepareStatement(query);
            prepare.setString(1, newInfo);
            prepare.setInt(2, id);
            prepare.executeUpdate();
            
            System.out.println("Updated Successfully!");
        } catch (Exception e) {
            System.out.println("Update Failed " + e);
        } 
    }

    @Override
    public void delete(int id) throws SQLException{
        Scanner sc = new Scanner (System.in);
        String query = "DELETE tbl_account WHERE user_id = ?";
            
        try {
            System.out.println("Are you sure you want to delete User ID " + id + "?");
            System.out.println("\t[Y] Yes\n\t[N] No");
            System.out.print("Enter choice: ");
            String choice = sc.nextLine();
                connect();
                prepare = connect.prepareStatement(query);
                prepare.setInt(1, id);
                prepare.executeUpdate();
            if ("Y".equals(choice) || "y".equals(choice)) {
                System.out.println("User ID " + id + " is successfully deleted!");
            } else {
                System.out.println("Failed to delete...");
            }
        } catch (Exception e) {
            System.out.println("Failed to delete " + e);
        } finally {
        connect.close();
    }
    }

    @Override
    public AccountModel getByFirstName(String userFirstName) {
        
        AccountModel accModel = new AccountModel();
        String query = "SELECT * FROM tbl_account where user_firstname like ?";
        try {
            connect();
            prepare = connect.prepareStatement(query);
            prepare.setString(1, "%" + userFirstName + "%");
            
            result = prepare.executeQuery();
            
           while(result.next()){
                accModel.setId(result.getInt("user_id"));
                accModel.setFirstName(result.getString("user_firstname"));
                accModel.setLastName(result.getString("user_lastname"));
                accModel.setAddress(result.getString("user_address"));
                accModel.setContactNo(result.getString("user_contact_no"));
                accModel.setIsAdmin(result.getBoolean("user_is_admin"));;
                accModel.setUserName(result.getString("user_name"));
                accModel.setPass(result.getString("user_pass"));
                return accModel;
           }
        } catch (Exception e) {
            System.out.println(e);
        } return null;
    }

    
}
