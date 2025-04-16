
package com.app;

import com.controller.AccountController;
import com.controller.BookController;
import com.controller.TransactionController;
import com.view.AccountView;
import com.view.BookView;
import com.view.LoginView;
import com.view.TransactionView;
import java.util.Scanner;
        
public class Main {
    public static void main(String[] args) {
        // I think in this part... it's the dependency injection mechanism???
         LoginView loginView = new LoginView();
         initial(loginView);
    }
    
    public static void initial(LoginView loginView){
        if(loginView.showLogin()){
            BookController bookController = new BookController();
            BookView bookView = new BookView(bookController);
            TransactionController transactionController = new TransactionController();
            
            AccountController accountController = new AccountController();
            AccountView accountView = new AccountView(accountController);
            TransactionView transactionView = new TransactionView(transactionController);
            AppMenu appMenu = new AppMenu(bookView, accountView, transactionView);
            appMenu.start();
        } else {
            System.out.print("Retry? Y/N : ");
            Scanner sc = new Scanner(System.in);
            String choice = sc.nextLine();
            if(choice.toLowerCase().equals("y")){
                System.out.println("Please Input Correct Credential");
                initial(loginView);
            }else{
                System.out.println("Exiting Program...");
                System.exit(0);
            }
        }
    }
}
