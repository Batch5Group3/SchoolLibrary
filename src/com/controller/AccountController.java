
package com.controller;

import com.dao.AccountDAO;
import com.model.AccountModel;
import com.services.AccountService;
import java.sql.SQLException;
import java.util.List;

public class AccountController {
    private AccountService accountService = new AccountService();
    
    public AccountController() {
        this.accountService = new AccountService();
    }
    
    public List<AccountModel> showAllAccount() {
        return accountService.getAllAccounts();
    }
    
    public void addAccount(AccountModel item) throws SQLException {
        accountService.addAccount(item);
    }
    
    public void updateAccount(int id, String field, String newInfo) {
        accountService.updateAccount(0, field, newInfo);
    }
    
    public AccountModel getByFirstName(String item){
        List<AccountModel> accounts = accountService.getByFirstName(item);
        if (accounts != null && !accounts.isEmpty()) {
            return accounts.get(0); 
        }
        return null; 
    }    
    
    public void deleteAccount(int id) throws SQLException {
        accountService.deleteAccount(id);
    }
    
     public AccountModel loginAccount(String userName, String password) {
         AccountDAO accountDao = new AccountService();
         return accountDao.getUserByCredentials(userName, password);
    }
     
     public boolean isUsernameTaken(String userName){
         return accountService.isUsernameTaken(userName);
     }
}
