
package com.app.view;


import com.app.controller.AccountController;
import com.app.model.AccountModel;
import com.app.util.DbConnection;
import java.sql.SQLException;
import java.util.Scanner;


public class AccountView extends DbConnection {
    AccountModel accModel = new AccountModel();
    AccountController accControl= new AccountController();
    
    public void addAccount() throws SQLException{
        Scanner sc = new Scanner(System.in);
        
        try {
        
        System.out.println("\t\t\t+=========================================+");
            System.out.println("\t\t\t|         🔐 CREATE YOUR NEW ACCOUNT      |");
            System.out.println("\t\t\t+=========================================+");
        System.out.print("\t\t\tEnter first name: ");
        accModel.setFirstName(sc.nextLine());
        System.out.print("\t\t\tEnter last name: ");
        accModel.setLastName(sc.nextLine());
        System.out.print("\t\t\tEnter address: ");
        accModel.setAddress(sc.nextLine());
        System.out.print("\t\t\tEnter contact number: ");
        accModel.setContactNo(sc.nextLine());
        System.out.print("\t\t\tUser Type:\n\t\t\t[0]Student\n\t\t\t[1]Libraian"
                + "\n\t\t\tEnter user type: ");
        accModel.setIsAdmin(true);
        sc.nextLine();
        System.out.print("\t\t\tEnter username: ");
        accModel.setUserName(sc.nextLine());
        System.out.print("\t\t\tEnter password: ");
        accModel.setPass(sc.nextLine());
        
        accControl.add(accModel);
        
        
        } catch (Exception e) {
            System.out.println("Failed " + e);
        } finally {
            connect.close();
        }
    }
    
    public void searchAccount(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter first name you want to search: ");
        String searchName = sc.nextLine();
        try {
            AccountModel search = accControl.getByFirstName(searchName);
            
            if (search != null) {
                System.out.println("User ID: " + search.getId()
                     + "\nFirst Name: " + search.getFirstName()
                     + "\nLast Name: " + search.getLastName()
                     + "\nAddress: " + search.getAddress()
                     + "\nContact No.: " + search.getContactNo());
            } else {
                System.out.println("No User Found! Please try again.");
                searchAccount();
            }
            
        } catch (Exception e) {
            System.out.println("Failed " + e);
        }
    }

    public void updateAccount() {
        //list account
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter ID you want to update: ");
        int id = sc.nextInt();
        sc.nextLine();
        
        System.out.println("=================================");
        System.out.println(" CHOOSE FIELD YOU WANT TO UPDATE");
        System.out.println("=================================");
        System.out.println("[1] First Name");
        System.out.println("[2] Last Name");
        System.out.println("[3] Address");
        System.out.println("[4] Contact Number");
        System.out.println("[5] User Type");
        System.out.println("[6] User Name");
        System.out.println("[7] Password");
        System.out.println("[8] Back");
        System.out.println("[9] Exit");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();
        
        String fieldName ="";
        switch (choice){
            case 1:
                fieldName = "user_firstname";
                break;
            case 2:
                fieldName = "user_lastname";
                break;
            case 3:
                fieldName = "user_address";
                break;
            case 4:
                fieldName = "user_contact_no";
                break;
            case 5:
                fieldName = "user_is_admin";
                break;
            case 6:
                fieldName = "user_name";
                break;
            case 7:
                fieldName = "user_pass";
                break;
            case 8:
                
                break;
            case 9:
                
                break;
            default: 
                System.out.println("Invalit Input");
                return;
        }
        System.out.print("Enter new information: " );
        String newInfo = sc.nextLine();
        
        accControl.update(id, fieldName, newInfo);
        }
    
    public void deleteAccount() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter User ID you want to delete: ");
        int id = sc.nextInt();
        sc.nextLine();
            
        try {
            accControl.delete(id);
        } catch (Exception e) {
            System.out.println("Failed to delete " + e);
        }
        sc.close();
    }
}
   
