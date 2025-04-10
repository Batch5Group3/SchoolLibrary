
package com.services;

import com.model.Account;
import java.util.ArrayList;
import java.util.List;

public class AccountService implements CrudService<Account> {
    private final List<Account> accounts = new ArrayList<>();

    @Override
    public void add(Account item) {
        accounts.add(item);
    }

    @Override
    public List<Account> getAll() {
        return accounts;
    }

    @Override
    public Account getById(int id) {
        for (Account account : accounts) {
            if (account.getUsrId() == id) {
                return account;
            }
        }
        return null;
    }

    @Override
    public void deleteBook(int id) {
        accounts.removeIf(account -> account.getUsrId() == id);
    }
    
}
