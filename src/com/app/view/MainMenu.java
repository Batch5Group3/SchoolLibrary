
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
        int choiceAcc;
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
            System.out.print(BLUE + "\t\t\t   Please enter your choice: " + RESET);
            
            choiceAcc = scanner.nextInt();
            switch(choiceAcc){
                case 1:
                    accView.addAccount();
                    break;
                case 2: 
                    clearScreen();
                    accView.addAccount();
                    break;
                case 3:
                    //exit
                  break;
                default:
                  System.out.println("⚠ Invalid choice. Please enter a number from the menu.");
                  break;
            }
            scanner.close();
        } 
        
    public void logInMenu() throws SQLException{
        Scanner scanner = new Scanner(System.in);
        int chocieMenu; 
        System.out.println("\t\t\t+=========================================+");
        System.out.println("\t\t\t|              MAIN MENU                  |");
        System.out.println("\t\t\t+=========================================+");
        String menu = 
            "\t\t\t   1. 📚 Access Books\n" +
            "\t\t\t   2. 👤 Manage Account\n" +
            "\t\t\t   3. 📖 View Borrowed Books\n" +
            "\t\t\t   4. 🔁 Return Books\n" +
            "\t\t\t   5. 🔒 Log Out\n" +
            "\t\t\t   6. ❌ Exit\n" +
            "\t\t\t   -----------------------------------\n";
        System.out.print(BLUE + "\t\t\tPlease choose an option:\n" + RESET);
            System.out.println(menu);
            chocieMenu = scanner.nextInt();
            switch (chocieMenu){
                case 1:
                    //book menu
                    break;
                case 2:
                    AccountView accView = new AccountView();
                    accView.accountMenu();
                    break;
                case 3:
                    // borrower books
                    break;
                case 4:
                    //return method
                    break;
                case 5:
                    //log out / back to log in menu
                    break;
                case 6:
                    //exit
                    System.out.println("Thank for using School Library Management System! :)");
                    break;
                default: 
                    System.out.println("⚠ Invalid choice. Please enter a number from the menu.");
                    break;
                    
            }
    }
    
    public void clearScreen() {
        for (int i = 0; i < 50; i++) {
        System.out.println();
        }
    }
   
}
