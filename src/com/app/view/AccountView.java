
package com.app.view;


import com.app.controller.AccountService;
import com.app.model.AccountModel;
import com.app.util.DbConnection;
import java.util.Scanner;


public class AccountView extends DbConnection {
    
    public void displayAddAccount(){
        try {
        Scanner sc = new Scanner(System.in);
        AccountModel account = new AccountModel();
        System.out.println("Create User Account");
        
        System.out.print("Enter first name: ");
        account.setFirstName(sc.nextLine());
        System.out.print("Enter last name: ");
        account.setLastName(sc.nextLine());
        System.out.print("Enter address: ");
        account.setAddress(sc.nextLine());
        System.out.print("Enter contact number: ");
        account.setcontactNo(sc.nextLine());
        System.out.print("User Type:\n\t[0]Student\n\t[1]Libraian\nEnter user type: ");
        account.setIsAdmin(true);
        sc.nextLine();
        System.out.print("Enter username: ");
        account.setUserName(sc.nextLine());
        System.out.print("Enter password: ");
        account.setPass(sc.nextLine());
        
        System.out.println(account);
        
        AccountService accountService = new AccountService();
        accountService.add(account);
        } catch (Exception e) {
            System.out.println("Failed " + e);
        }
    }

}
