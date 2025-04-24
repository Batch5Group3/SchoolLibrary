
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
    Scanner scanner = new Scanner(System.in);
    public static final String RED = "\033[1;31m";
    public static final String RESET = "\u001B[0m";
    public static final String PURPLE = "\u001B[35m";
    public static final String BLUE = "\u001B[34m";
    public static final String GREEN = "\u001B[32m";

    private final BookController bookController = new BookController();
    private final BookView bookView = new BookView();
    
    public static void main(String[] args) throws SQLException {
        MainMenu main = new MainMenu();
        main.welcomeMessage();
    }
    public void welcomeMessage() throws SQLException{
        
        AccountView accountView = new AccountView();
        int choiceAcc;
        String welcome =
                "\t\t\t\t__        __   _                            \n" +
                "\t\t\t\t\\ \\      / /__| | ___ ___  _ __ ___   ___  \n" +
                "\t\t\t\t \\ \\ /\\ / / _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\ \n" +
                "\t\t\t\t  \\ V  V /  __/ | (_| (_) | | | | | |  __/\n" +
                "\t\t\t\t   \\_/\\_/ \\___|_|\\___\\___/|_| |_| |_|\\___| \n" +
                PURPLE + "\t\t\t\t   📖 SCHOOL LIBRARY MANAGEMENT SYSTEM 📖\n" + RESET +
                "\t\t\t\t╔════════════════════════╗\n" +
                "\t\t\t\t║            🔐 ACCOUNT MENU               ║\n" +
                "\t\t\t\t╚════════════════════════╝\n" +
                "\t\t\t\t  [1] Log In to Your Account              \n" +
                "\t\t\t\t  [2] Sign Up for a New Account           \n" +
                "\t\t\t\t  [3] Exit Application                    \n" +
                "\t\t\t\t ════════════════════════\n";
        System.out.println("\n\n");
        System.out.print(welcome);
        System.out.print(BLUE + "\t\t\t\tPlease enter your choice: " + RESET);
        choiceAcc = scanner.nextInt();
        scanner.nextLine();
        switch(choiceAcc){
            case 1:
                accountView.logInHeader();
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
        int chocieMenu; 
        String menu =
                "\t\t\t\t╔═════════════════════════╗\n"+
                "\t\t\t\t║             👥 ADMIN MAIN MENU             ║\n"+
                "\t\t\t\t╚═════════════════════════╝\n"+
                "\t\t\t\t   [1] 📚 Manage Books\n" +
                "\t\t\t\t   [2] 👤 Manage Account\n" +
                "\t\t\t\t   [3] 📖 View Available Books\n" +
                "\t\t\t\t   [4] 📖 View Borrowed Books\n" +
                "\t\t\t\t   [5] 📚 Borrow Books\n" +
                "\t\t\t\t   [6] 🔁 Return Books\n" +
                "\t\t\t\t   [7] 📑 Manage Transactions\n" +
                "\t\t\t\t   [8] 🔒 Log Out\n" +
                "\t\t\t\t   [9] ❌ Exit\n" +
                "\t\t\t\t ════════════════════════\n";
        System.out.println("\n\n");
        System.out.print(menu);
        System.out.print(BLUE + "\t\t\t\tPlease enter your choice: " + RESET);
            chocieMenu = scanner.nextInt();
            scanner.nextLine();
            
            switch (chocieMenu){
                case 1:
                    bookView.bookMenu();
                    break;
                case 2:
                    accountView.accountMenu();
                    break;
                case 3:
                    bookView.displayAvailableBook();
                    accountView.waitForEnter(scanner);
                    adminLogInMenu();
                    break;
                case 4:
                    bookView.displayBorrowedBook();
                    adminLogInMenu();
                    break;
                case 5:
                    transactionView.borrowBookTransaction();
                    adminLogInMenu();
                    break;
                case 6:
                    transactionView.returnBookByTransactionId();
                    adminLogInMenu();
                    break;
                case 7:
                    transactionView.transactionMenu();
                    break;
                case 8:
                    System.out.println("\t\t\t\tAre you sure you want to log out? "
                        + "\n\t\t\t\t[Y] Yes \n\t\t\t\t[N] No");
                    System.out.print(BLUE + "\t\t\t\tPlease enter your choice: " + RESET);
                    char choice = scanner.nextLine().charAt(0);
                    if (choice == 'y' || choice == 'Y') {
                        System.out.println(GREEN+"\n\t\t\t\t🔒 Logout successful!"+RESET);
                        System.out.println("\t\t\t\t👋 See you next time!\n");
                        welcomeMessage();
                    } else {
                    System.out.println(RED+"\n\t\t\t\t❌ Logout failed."+RESET);
                    bookView.waitForEnter(scanner);
                    adminLogInMenu();
                    }
                    break;
                case 9:
                    exitProgram();
                    break;
                default: 
                    System.out.println("\t\t\t\t⚠ Invalid choice. Please enter a number from the menu.");
                    break;
            }
    }
    
    public void userLoginMenu() throws SQLException{
        System.out.println("\n\n");
        System.out.println("\t\t\t\t╔═════════════════════╗");
        System.out.println("\t\t\t\t║         📚 BORROWER MAIN MENU       ║");
        System.out.println("\t\t\t\t╚═════════════════════╝");
        System.out.println("\t\t\t\t  [1] 🔍 Check Book Availability");
        System.out.println("\t\t\t\t  [2] 📖 Book List");
        System.out.println("\t\t\t\t  [3] 🔐 Log Out");
        System.out.println("\t\t\t\t  [4] ❌ Exit Application");
        System.out.println("\t\t\t\t══════════════════════");
        System.out.print(BLUE + "\t\t\t\tPlease enter your choice: " + RESET);
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        switch (choice){
            case 1:
                bookView.displayAvailableBook();
                borrowChoice();
                userLoginMenu();
                break;
            case 2:
                bookView.displayBook();
                userLoginMenu();
                break;
            case 3:
                System.out.println("\t\t\t\tAre you sure you want to exit? "
                    + "\n\t\t\t\t[Y] Yes \n\t\t\t\t[N] No");
                System.out.print(BLUE + "\t\t\t\tPlease enter your choice: " + RESET);
                char userChoice = scanner.nextLine().charAt(0);
                if (userChoice == 'y' || userChoice == 'Y') {
                    System.out.println(GREEN+"\n\t\t\t\t🔒 Logout successful!"+RESET);
                    System.out.println("\t\t\t\t👋 See you next time!\n");
                    welcomeMessage();
                } else {
                    System.out.println(RED+"\n\t\t\t\t❌ Logout failed."+RESET);
                    bookView.waitForEnter(scanner);
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
        public void borrowChoice() throws SQLException{
            TransactionView transactionView = new TransactionView();
            System.out.println("\n\n\t\t\t\t  [1] 📖 Borrow Book");
            System.out.println("\t\t\t\t  [2] 🔙 Back");
            System.out.print(BLUE + "\t\t\t\tPlease enter your choice: " + RESET);
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    transactionView.borrowBookTransaction();
                    break;
                case 2:
                     userLoginMenu();
                    break;
                default:
                    System.out.println("Invalid option.");
            }
    }
    
    public void exitProgram() throws SQLException{
        System.out.println("\t\t\t\tAre you sure you want to exit? "
                + "\n\t\t\t\t[Y] Yes \n\t\t\t\t[N] No");
        System.out.print(BLUE + "\t\t\t\tPlease enter your choice: " + RESET);
        String input = scanner.nextLine().trim();
        if (!input.isEmpty()) {
            char choice = input.charAt(0);
            if (choice == 'y' || choice == 'Y') {
                System.out.println("\n\n\t\t\t\t📚 Thank you for using the School Library Management System!");
                System.out.println("\t\t\t\t👋 Have a great day! Exiting the program...");
                System.exit(0);
                scanner.close();
            } else {
                System.out.println(RED+"\n\t\t\t\t❌ Exit canceled.\n\n\n"+RESET);
                welcomeMessage();
            }
        } else {
            System.out.println(RED + "\n\t\t\t⚠ Invalid input. Please enter Y or N.\n" + RESET);
            exitProgram();
        }
        
    }
    
   public static void clearScreen() {
    for (int i = 0; i < 50; ++i) System.out.println();
   }
}
