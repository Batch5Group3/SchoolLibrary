
package com.app.view;

import java.sql.SQLException;
import java.util.Scanner;


public class MainMenu {
    public static final String RESET = "\u001B[0m";
    public static final String PURPLE = "\u001B[35m";
    public static final String BLUE = "\u001B[34m";
    AccountView accView = new AccountView();
    
    public static void main(String[] args) throws SQLException {
        MainMenu main = new MainMenu();
        main.welcomeMessage();
    }
    public void welcomeMessage() throws SQLException{
        Scanner scanner = new Scanner(System.in);
        int choice;
        
        do {
            

           String welcome =
                "\t\t\t__        __   _                            \n" +
                "\t\t\t\\ \\      / /__| | ___ ___  _ __ ___   ___  \n" +
                "\t\t\t \\ \\ /\\ / / _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\ \n" +
                "\t\t\t  \\ V  V /  __/ | (_| (_) | | | | | |  __/\n" +
                "\t\t\t   \\_/\\_/ \\___|_|\\___\\___/|_| |_| |_|\\___| \n";
            System.out.println(welcome);
            System.out.println(PURPLE + "\t\t\t   ~ SCHOOL LIBRARY MANAGEMENT SYSTEM ~\n" + RESET);
            System.out.println("\t\t\t+=========================================+");
            System.out.println("\t\t\t|         🔐 ACCOUNT MENU                 |");
            System.out.println("\t\t\t+=========================================+");
            System.out.println("\t\t\t| [1] Log In to Your Account              |");
            System.out.println("\t\t\t| [2] Sign Up for a New Account           |");
            System.out.println("\t\t\t| [3] Exit Application                    |");
            System.out.println("\t\t\t+-----------------------------------------+");
            System.out.print("\t\t\t   Please enter your choice: ");
            
            choice = scanner.nextInt();
            switch(choice){
                case 1:
                    accView.addAccount();
                    break;
                case 2: 
                    clearScreen();
                    accView.addAccount();
                    break;
                case 3:
                    
            }
        } while (choice!= 0);
        scanner.close();
    }
    
    public void logInMenu(){
        
        
        String menu = BLUE +
            "   Please choose an option:\n" +
            "   -----------------------------------\n" +
            "   1. 📚 Access Books\n" +
            "   2. 👤 Manage Account\n" +
            "   3. 📖 View Borrowed Books\n" +
            "   4. 🔁 Return Books\n" +
            "   5. 🔒 Log Out\n" +
            "   6. ❌ Exit\n" +
            "   -----------------------------------\n" + RESET;
            System.out.println(menu);
    }
    
    public void clearScreen() {
        for (int i = 0; i < 50; i++) {
        System.out.println();
        }
    }
   
}
