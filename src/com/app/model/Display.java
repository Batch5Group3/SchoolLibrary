
package com.app.model;

import com.app.util.DbConnection;
import com.app.view.AccountView;


public class Display extends DbConnection {
    public static void main(String[] args) {
        AccountView accountView = new AccountView();
        accountView.displayAddAccount();
    }
}
