
package com.app.view;

import java.util.Scanner;


public class MainMenu {
    
    AccountView accView = new AccountView();
    public static void main(String[] args) {
        MainMenu main = new MainMenu();
        main.accountMenu();
    }
    public void accountMenu(){
        Scanner scanner = new Scanner(System.in);
        int choice;
        
        do {            
            System.out.println("===================");
            System.out.println("📚 SCHOOL LIBRARY SYSTEM");
            System.out.println("===================");
            System.out.println("[1] Log In");
            System.out.println("[2] Sign Up");
            System.out.println("[3] Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            
            switch(choice){
                case 1:
                    accView.addAccount();
                    break;
                case 2: 
                    accView.addAccount();
                    break;
                case 3:
                    
            }
        } while (choice!= 0);
    }
}
