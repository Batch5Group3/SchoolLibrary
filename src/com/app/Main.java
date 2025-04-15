
package com.app;

import com.controller.AccountController;
import com.controller.BookController;
import com.view.AccountView;
import com.view.BookView;

public class Main {
    public static void main(String[] args) {
        // I think in this part... it's the dependency injection mechanism???
        BookController bookController = new BookController();
        BookView bookView = new BookView(bookController);
        //bookView.showMainMenu();
        
        AccountController accountController = new AccountController();
        AccountView accountView = new AccountView(accountController);
        AppMenu appMenu = new AppMenu(bookView, accountView);
        appMenu.start();
    }
}
