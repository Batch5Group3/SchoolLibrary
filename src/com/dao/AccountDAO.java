
package com.dao;

import com.model.AccountModel;
import java.sql.SQLException;
import java.util.List;


public interface AccountDAO {
    
    public List<AccountModel> getAllAccounts();
    public void addAccount(AccountModel item) throws SQLException;
    public List<AccountModel> getByFirstName(String item);
    public void updateAccount(int id, String field, String newInfo);
    public void deleteAccount(int id) throws SQLException;
    public AccountModel getUserByCredentials (String userName, String password);
    public boolean isUsernameTaken(String userName);
}
