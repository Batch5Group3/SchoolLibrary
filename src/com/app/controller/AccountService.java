
package com.app.controller;

import com.app.model.AccountModel;
import com.app.util.DbConnection;
import com.app.view.AccountView;
import java.util.List;


public class AccountService extends DbConnection implements CrudService<AccountModel> {

    
    @Override
    public void add(AccountModel account) {
        AccountView accountView = new AccountView();
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
            prepare.setString(6, account.getcontactNo());
            prepare.setBoolean(7, account.isIsAdmin());
            
            if (account.getFirstName().isEmpty() || account.getLastName().isEmpty() || account.getUserName().isEmpty() || account.getPass().isEmpty())  {
                 String errorMsg = "Fields cannot be empty. Please try again!";
                 System.out.println(errorMsg);
                 accountView.displayAddAccount();
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
    public AccountModel getById() {
        return null;
    }

    @Override
    public void update(int id, AccountModel updatedItem) {
        
    }

    @Override
    public void delete(int id) {
    }

    
}
