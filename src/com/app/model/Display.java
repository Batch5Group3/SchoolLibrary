
package com.app.model;

import com.app.util.DbConnection;
import com.app.view.AccountView;
import com.app.view.MainMenu;
import java.sql.SQLException;


public class Display extends DbConnection {
    public static void main(String[] args) throws SQLException {
//        AccountView accountView = new AccountView();
//        accountView.deleteAccount();
          MainMenu main = new MainMenu();
          main.logInMenu();
    }
}
