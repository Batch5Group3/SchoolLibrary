
package com.view;

import com.controller.BookController;
import com.controller.TransactionController;
import com.model.BookModel;
import com.model.TransactionModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class MainMenu {
    public static final String RESET = "\u001B[0m";
    public static final String PURPLE = "\u001B[35m";
    public static final String BLUE = "\u001B[34m";
    private final BookController bookController = new BookController();
    private final BookView bookView = new BookView();
    
    public static void main(String[] args) throws SQLException {
        MainMenu main = new MainMenu();
        main.welcomeMessage();
    }
    public void welcomeMessage() throws SQLException{
        
        AccountView accountView = new AccountView();
        Scanner scanner = new Scanner(System.in);
        int choiceAcc;
        String welcome =
                "\t\t\t\t__        __   _                            \n" +
                "\t\t\t\t\\ \\      / /__| | ___ ___  _ __ ___   ___  \n" +
                "\t\t\t\t \\ \\ /\\ / / _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\ \n" +
                "\t\t\t\t  \\ V  V /  __/ | (_| (_) | | | | | |  __/\n" +
                "\t\t\t\t   \\_/\\_/ \\___|_|\\___\\___/|_| |_| |_|\\___| \n" +
                PURPLE + "\t\t\t\t   ~ SCHOOL LIBRARY MANAGEMENT SYSTEM ~\n" + RESET +
                "\t\t\t\t╔═════════════════════════╗\n" +
                "\t\t\t\t║            🔐 ACCOUNT MENU             ║\n" +
                "\t\t\t\t╚═════════════════════════╝\n" +
                "\t\t\t\t  [1] Log In to Your Account              \n" +
                "\t\t\t\t  [2] Sign Up for a New Account           \n" +
                "\t\t\t\t  [3] Exit Application                    \n" +
                "\t\t\t\t══════════════════════════\n";
        System.out.println("\n\n");
        System.out.print(welcome);
        System.out.print(BLUE + "\t\t\t\tPlease enter your choice: " + RESET);
        choiceAcc = scanner.nextInt();
        switch(choiceAcc){
            case 1:
                accountView.loginAccount();
                break;
            case 2: 
                accountView.addAccount();
                break;
            case 3:
                exitProgram();
                break;
            default:
              System.out.println("\t\t\t\t⚠ Invalid choice. Please enter a number from the menu.");
              break;
        }
    } 
        
    public void adminLogInMenu() throws SQLException{
        AccountView accountView = new AccountView();
        TransactionView transactionView = new TransactionView();
        Scanner scanner = new Scanner(System.in);
        int chocieMenu; 
        String menu =
                "\t\t\t\t╔═════════════════════════╗\n"+
                "\t\t\t\t║          👥 ADMIN MAIN MENU            ║\n"+
                "\t\t\t\t╚═════════════════════════╝\n"+
                "\t\t\t\t   [1] 📚 Manage Books\n" +
                "\t\t\t\t   [2] 👤 Manage Account\n" +
                "\t\t\t\t   [3] 📖 View Borrowed Books\n" +
                "\t\t\t\t   [4] 🔁 Return Books\n" +
                "\t\t\t\t   [5] 📑 Manage Transactions\n" +
                "\t\t\t\t   [6] 🔒 Log Out\n" +
                "\t\t\t\t   [7] ❌ Exit\n" +
                "\t\t\t\t   ═════════════════════════\n";
        System.out.println("\n\n");
        System.out.print(menu);
        System.out.print(BLUE + "\t\t\t\tPlease enter your choice: " + RESET);
            chocieMenu = scanner.nextInt();
            scanner.nextLine();
            
            switch (chocieMenu){
                case 1:
                    bookView.showMainMenu();
                    break;
                case 2:
                    accountView.accountMenu();
                    break;
                case 3:
                    bookView.displayBorrowedBook();
                    break;
                case 4:
                    transactionView.viewAllTransactions();
                    break;
                case 5:
                    transactionView.transactionMenu();
                    break;
                case 6:
                    System.out.println("\t\t\t\tAre you sure you want to log out? "
                        + "\n\t\t\t\t[Y] Yes \n\t\t\t\t[N] No");
                    System.out.print(BLUE + "\t\t\t\tPlease enter your choice: " + RESET);
                    char choice = scanner.nextLine().charAt(0);
                    if (choice == 'y' || choice == 'Y') {
                        System.out.println("\n\t\t\t\t🔒 Logout successful!");
                        System.out.println("\t\t\t\t👋 See you next time!\n");
                        welcomeMessage();
                    } else {
                    System.out.println("\n\t\t\t\t❌ Logout failed. Please try again.");
                    adminLogInMenu();
                    }
                    break;
                case 7:
                    exitProgram();
                    break;
                default: 
                    System.out.println("\t\t\t\t⚠ Invalid choice. Please enter a number from the menu.");
                    break;
            }
    }
    
    public void userLoginMenu() throws SQLException{
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\n");
        System.out.println("\t\t\t\t╔═════════════════════╗");
        System.out.println("\t\t\t\t║       📚 BORROWER MAIN MENU     ║");
        System.out.println("\t\t\t\t╚═════════════════════╝");
        System.out.println("\t\t\t\t  [1] 🔍 Check Book Availability");
        System.out.println("\t\t\t\t  [2] 📖 Book List");
        System.out.println("\t\t\t\t  [3] 🔐 Log Out");
        System.out.println("\t\t\t\t  [4] ❌ Exit Application");
        System.out.println("\t\t\t\t╚═════════════════════╝");
        System.out.print(BLUE + "\t\t\t\tPlease enter your choice: " + RESET);
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        switch (choice){
            case 1:
                bookView.displayAvailableBook();
                
                break;
            case 2:
                bookView.displayBook();
                break;
            case 3:
                System.out.println("\t\t\t\tAre you sure you want to exit? "
                    + "\n\t\t\t\t[Y] Yes \n\t\t\t\t[N] No");
                System.out.print(BLUE + "\t\t\t\tPlease enter your choice: " + RESET);
                char userChoice = scanner.nextLine().charAt(0);
                if (userChoice == 'y' || userChoice == 'Y') {
                    System.out.println("\n\t\t\t\t🔒 Logout successful!");
                    System.out.println("\t\t\t\t👋 See you next time!\n");
                    welcomeMessage();
                } else {
                    System.out.println("\n\t\t\t\t❌ Logout failed. Please try again.");
                    userLoginMenu();
                }
                break;
            case 4:
                exitProgram();
                break;
            default:
                System.out.println("\t\t\t\t⚠ Invalid choice. Please enter a number from the menu.");
                break;       
        }
    }
    
    public void exitProgram() throws SQLException{
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t\t\t\tAre you sure you want to exit? "
                + "\n\t\t\t\t[Y] Yes \n\t\t\t\t[N] No");
        System.out.print(BLUE + "\t\t\t\tPlease enter your choice: " + RESET);
        char choice = scanner.nextLine().charAt(0);
        if (choice == 'y' || choice == 'Y') {
            System.out.println("\n\n\n\t\t\t\tThank you for using School Library Management System!");
            System.out.println("\t\t\t\tExiting.....");
            System.exit(0);
            scanner.close();
        } else {
            System.out.println("\t\t\t\tFailed to Exit\n\n\n");
            welcomeMessage();
        }
    }
    
   public static void clearScreen() {
    for (int i = 0; i < 50; ++i) System.out.println();
   }
}
