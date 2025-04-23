
package com.view;


import com.controller.AccountController;
import com.model.AccountModel;
import com.app.util.DbConnection;
import com.services.AccountService;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;


public class AccountView extends DbConnection {
    Scanner sc = new Scanner(System.in);
    private final AccountService accountService = new AccountService();
    private final AccountController accController = new AccountController();
    private final MainMenu main = new MainMenu();
    
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\033[1;31m";
    public static final String RESET = "\u001B[0m";
    public static final String BLUE = "\u001B[34m";
    
    AccountModel accModel = new AccountModel();
    
    public void accountMenu() throws SQLException {
        System.out.println("\n\n");
        System.out.println("\t\t\t\t╔═════════════════════════╗");
        System.out.println("\t\t\t\t║            👥 ACCOUNT MANAGEMENT           ║");
        System.out.println("\t\t\t\t╚═════════════════════════╝");
        System.out.println("\t\t\t\t  [1] 📜 List of Accounts");
        System.out.println("\t\t\t\t  [2] ✏️ Update Account");
        System.out.println("\t\t\t\t  [3] 🔍 Search Account");
        System.out.println("\t\t\t\t  [4] 🗑️ Delete Account");
        System.out.println("\t\t\t\t  [5] 🔙 Back to Main Menu");
        System.out.println("\t\t\t\t  [6] 🔐 Log Out");
        System.out.println("\t\t\t\t  [7] ❌ Exit Application");
        System.out.println("\t\t\t\t══════════════════════════");
        System.out.print(BLUE + "\t\t\t\tPlease enter your choice: " + RESET);
        int choice = sc.nextInt();
        sc.nextLine();
        
        switch(choice){
            case 1:
                displayAccount();
                break;
            case 2:
                updateAccount();
                break;
            case 3:
                searchAccount();
                break;
            case 4:
                deleteAccount();
                break;
            case 5:
                main.adminLogInMenu();
                break;
            case 6:
                System.out.println("\t\t\t\tAre you sure you want to exit? "
                    + "\n\t\t\t\t[Y] Yes \n\t\t\t\t[N] No");
                System.out.print(BLUE + "\t\t\t\tPlease enter your choice: " + RESET);
                char accChoice = sc.nextLine().charAt(0);
                    if (accChoice == 'y' || accChoice == 'Y') {
                    System.out.println(GREEN + "\n\t\t\t\t🔒 Logout successful!" + RESET);
                    System.out.println("\t\t\t\t👋 See you next time!\n");
                    main.welcomeMessage();
                } else {
                System.out.println(RED + "\n\t\t\t\t❌ Logout failed. Please try again." + RESET);
                accountMenu();
                }
                break;
            case 7:
                main.exitProgram();
                break;
            default:
                System.out.println(RED + "\t\t\t\t⚠ Invalid choice. Please enter a number from the menu." + RESET);
                accountMenu();
                break;
        }
    }
    
    public void displayAccount() throws SQLException {
            List<AccountModel> accounts = accountService.getAllAccounts();
            
            System.out.println();
            System.out.println(BLUE +"════════════════════════════ ACCOUNT LIST ═══════════════════════════════════" + RESET);
            System.out.println("═══════════════════════════════════════════════════════════════════════");
            System.out.printf("| %-5s | %-15s | %-15s | %-12s | %-12s | %-20s | %-15s | %-6s |\n",
                "ID", "Username", "Password", "First Name", "Last Name", "Address", "Contact No", "Admin");
            System.out.println("═══════════════════════════════════════════════════════════════════════");
            for (AccountModel acc : accounts) {
                System.out.printf("| %-5d | %-15s | %-15s | %-12s | %-12s | %-20s | %-15s | %-6s |\n",
                        acc.getId(),
                        acc.getUserName(),
                        acc.getPass(),
                        acc.getFirstName(),
                        acc.getLastName(),
                        acc.getAddress(),
                        acc.getContactNo(),
                        acc.isAdmin() ? "Yes" : "No");
            }
            System.out.println("═══════════════════════════════════════════════════════════════════════");
            waitForEnter(sc);
            accountMenu();
    }
    
    public void addAccount() throws SQLException{
       
            System.out.println("\n\n");
            System.out.println("\t\t\t\t╔═════════════════════════╗");
            System.out.println("\t\t\t\t║         🔐 CREATE YOUR NEW ACCOUNT     ║");
            System.out.println("\t\t\t\t╚═════════════════════════╝");
            System.out.print("\t\t\t\tEnter first name: ");
            accModel.setFirstName(sc.nextLine());
            System.out.print("\t\t\t\tEnter last name: ");
            accModel.setLastName(sc.nextLine());
            System.out.print("\t\t\t\tEnter address: ");
            accModel.setAddress(sc.nextLine());
            System.out.print("\t\t\t\tEnter contact number: ");
            accModel.setContactNo(sc.nextLine());
            
            System.out.print("\t\t\t\tUser Type:"
                    + "\n\t\t\t\t\t[0]Student\n\t\t\t\t\t[1]Libraian"
                    + "\n\t\t\t\tEnter user type: ");
            int userType = sc.nextInt();
            accModel.setIsAdmin(userType == 1);
            sc.nextLine();
            
            String username;
             while (true)    {
                 System.out.print("\t\t\t\tEnter username: ");
                 username = sc.nextLine();
                if (accountService.isUsernameTaken(username)) {
                    System.out.println("\n\n\t\t\t\tSorry, username is already taken.");
                    System.out.println("\t\t\t\tPlease enter a new username: ");
                } else{
                     break;
                 }
                 }   
                 accModel.setUserName(username);
                System.out.print("\t\t\t\tEnter password: ");
                accModel.setPass(sc.nextLine());
                
                if (accModel.getFirstName().isEmpty() || accModel.getLastName().isEmpty() || accModel.getUserName().isEmpty() || accModel.getPass().isEmpty())  {
                    String errorMsg = "\t\t\t\tFields cannot be empty. Please try again!";
                    System.out.println(RED + errorMsg + RESET);
                    addAccount();
                } else {
                   accountService.addAccount(accModel);
                   System.out.println("\t\t\t\tWelcome " + accModel.getFirstName() + "! You successfully created your account.");
                   waitForEnter(sc);
                   main.welcomeMessage();
                }
    }
    
    public void searchAccount() throws SQLException{
        
        System.out.println("\n\n");
        System.out.print("\t\t\t\tEnter first name you want to search: ");
        String searchName = sc.nextLine().toLowerCase();
        System.out.println("════════════════════════════════════════════════════════════════════════════════");
        System.out.printf("| %-5s | %-15s | %-15s | %-12s | %-12s | %-20s | %-15s | %-6s |\n",
        "ID", "Username", "Password", "First Name", "Last Name", "Address", "Contact No", "Admin");
        System.out.println("════════════════════════════════════════════════════════════════════════════════");
        
            List<AccountModel> search = accountService.getByFirstName(searchName);
        
            if (search != null && !search.isEmpty()) {
                for (AccountModel acc : search) {
                System.out.printf("| %-5d | %-15s | %-15s | %-12s | %-12s | %-20s | %-15s | %-6s |\n",
                        acc.getId(),
                        acc.getUserName(),
                        acc.getPass(),
                        acc.getFirstName(),
                        acc.getLastName(),
                        acc.getAddress(),
                        acc.getContactNo(),
                        acc.isAdmin() ? "Yes" : "No");
                System.out.println("════════════════════════════════════════════════════════════════════════════════");

            }
                waitForEnter(sc);
                accountMenu();
                
            } else {
                System.out.println(RED + "\t\t\t\t⚠No User Found! Please try again." + RESET);
                waitForEnter(sc);
                searchAccount();
            }
    }
    
    public void updateAccount() throws SQLException {
        //list account
        System.out.println("\n\n");
        System.out.print("\t\t\t\tEnter ID you want to update: ");
        int id = sc.nextInt();
        sc.nextLine();
        
        
        System.out.println("\t\t\t\t╔═══════════════════════════╗");
        System.out.println("\t\t\t\t║       👥 CHOOSE FIELD YOU WANT TO UPDATE   ║");
        System.out.println("\t\t\t\t╚═══════════════════════════╝");
        System.out.println("\t\t\t\t  [1] First Name");
        System.out.println("\t\t\t\t  [2] Last Name");
        System.out.println("\t\t\t\t  [3] Address");
        System.out.println("\t\t\t\t  [4] Contact Number");
        System.out.println("\t\t\t\t  [5] User Type");
        System.out.println("\t\t\t\t  [6] User Name");
        System.out.println("\t\t\t\t  [7] Password");
        System.out.println("\t\t\t\t  [8] Back");
        System.out.println("\t\t\t\t  [9] Exit");
        System.out.println("\t\t\t\t═══════════════════════════");
        System.out.print(BLUE + "\t\t\t\tPlease enter your choice: " + RESET);
        int choice = sc.nextInt();
        sc.nextLine();
        
        String fieldName ="";
        String newInfo ="";
        switch (choice){
            case 1:
                fieldName = "user_firstname";
                System.out.print("\t\t\t\tEnter new First Name: " );
                newInfo = sc.nextLine();
                break;
            case 2:
                fieldName = "user_lastname";
                System.out.print("\t\t\t\tEnter new Last Name: " );
                newInfo = sc.nextLine();
                break;
            case 3:
                fieldName = "user_address";
                System.out.print("\t\t\t\tEnter new Address: " );
                newInfo = sc.nextLine();
                break;
            case 4:
                fieldName = "user_contact_no";
                System.out.print("\t\t\t\tEnter new Contact Number: " );
                newInfo = sc.nextLine();
                break;
            case 5:
                fieldName = "user_is_admin";
                int userType;
                while (true){
                    System.out.println("\n\t\t\t\t\t[0]Student\n\t\t\t\t\t[1]Libraian");
                    System.out.print("\t\t\t\tEnter user type: ");
                    
                    if (sc.hasNextInt()) {
                    userType = sc.nextInt();
                    sc.nextLine();
                    if (userType == 0 || userType == 1) {
                        boolean isAdmin = (userType == 1);
                        newInfo = isAdmin ? "1" : "0";
                        accModel.setIsAdmin(isAdmin);
                        break;
                    } else{
                        System.out.println(RED + "\t\t\t\tInvalid Input. Please Try Again." + RESET);
                    }
                    } else {
                        System.out.println(RED + "\t\t\t\tInvalid Input. Please Try Again." + RESET);
                        sc.nextLine(); 
                    }
                }
                break;
            case 6:
                fieldName = "user_name";
                while (true){
                    System.out.print("\t\t\t\tEnter  new Username: ");
                    newInfo = sc.nextLine();
                    if (accountService.isUsernameTaken(newInfo)) {
                        System.out.println("\n\n\t\t\t\tSorry, username is already taken.");
                        System.out.println("\t\t\t\tPlease enter a new username: "); 
                    } else{
                        break;
                    }
                }
                break;
            case 7:
                fieldName = "user_pass";
                System.out.print("\t\t\t\tEnter  new Password: ");
                newInfo = sc.nextLine();
                break;
            case 8:
                accountMenu();
                break;
            case 9:
                main.exitProgram();
                break;
            default: 
                System.out.println(RED +"\t\t\t\t⚠ Invalid choice. Please enter a number from the menu." + RESET);
                updateAccount();
                return;
        }
        accountService.updateAccount(id, fieldName, newInfo);
        System.out.println(GREEN + "\t\t\t\tUpdated Successfully!" + RESET);
        waitForEnter(sc);
        accountMenu();
    }
    
    public void deleteAccount() throws SQLException {
        System.out.println("\n\n");
        System.out.print("\t\t\t\tEnter User ID you want to delete: ");
        int id = sc.nextInt();
        sc.nextLine();
            
            System.out.println("\t\t\t\tAre you sure you want to delete User ID " + id + "?");
            System.out.println("\t\t\t\t[Y] Yes\n\t\t\t\t[N] No");
            System.out.print("\t\t\t\tEnter choice: ");
            String choice = sc.nextLine();
            if ("Y".equalsIgnoreCase(choice)) {
                accountService.deleteAccount(id);
                System.out.println("\t\t\t\tUser ID " + id + " is successfully deleted!");
                
            } else {
                System.out.println(RED+"\t\t\t\tDelete canceled."+RESET);
            }
        waitForEnter(sc);
        accountMenu();
    }
    
    public boolean loginAccount() throws SQLException {
        
        while (true){
            System.out.print("\n\t\t\t\t👤 Username: ");
            String userName = sc.nextLine();

            System.out.print("\t\t\t\t🔒 Password: ");
            String password = sc.nextLine();
        
            AccountModel user = accController.loginAccount(userName, password);
            if (user!= null) {
                boolean isAdmin = user.isAdmin();
                System.out.println(GREEN + "\n\t\t\t\tLogin successful!" + RESET);
                if (isAdmin) {
                    main.adminLogInMenu();
                } else {
                    main.userLoginMenu();
                }
                return true;
            } else {
            System.out.println(RED + "\n\t\t\t\t❌Invalid credentials. Please try again." + RESET);
            loginAccount();
            }
        } 
    }
    
    public void logInHeader(){
        System.out.println("\n\n");
        String login = "\t\t\t\t╔═════════════════════════════╗\n" +
                       "\t\t\t\t║         School Library Management System          ║\n" +
                       "\t\t\t\t╠═════════════════════════════╣\n" +
                       "\t\t\t\t║               LOGIN TO CONTINUE                   ║\n" +
                       "\t\t\t\t╚═════════════════════════════╝";
        System.out.println(login);
    }
    
    public static void waitForEnter(Scanner sc) {
        System.out.print("\n\t\t\t\tPress Enter to return...");
        sc.nextLine();
    }
}
   
