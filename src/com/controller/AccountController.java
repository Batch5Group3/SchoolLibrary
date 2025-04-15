
package com.controller;

import com.model.Account;
import com.services.AccountService;
import com.services.CrudService;
import java.util.List;

public class AccountController {
    private final CrudService<Account> accountService;
    
    public AccountController() {
        // initializes this.bookService with BookService objects
        this.accountService = new AccountService();
    }
    
     // Read
    public List<Account> showAllAccount() {
        return accountService.getAll();
    }
    
    // Create
    public void addAccount(Account book) {
        accountService.add(book);
    }
    
    public void updateAccount(Account item) {
        accountService.updateItem(item);
    }
    
    public Account findAccountById(int id) {
        return accountService.getById(id);
    }
    
    public void deleteAccount(int id) {
        accountService.deleteItem(id);
    }
}
