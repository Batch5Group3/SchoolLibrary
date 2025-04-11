
package com.app.controller;

import com.app.util.DbConnection;
import java.util.Scanner;


public class UserAccount extends DbConnection {
    
    //create User Account
    public void createUserAccount() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Create User Account");
        
        System.out.print("Enter first name: ");
        String firstName = sc.nextLine();
        System.out.print("Enter last name: ");
        String lastName = sc.nextLine();
        System.out.print("Enter address: ");
        String address = sc.nextLine();
        System.out.print("Enter contact number: ");
        int phone = sc.nextInt();
        System.out.println("User Type:\n\t[0]Student\n\t[1]Libraian\nEnter user type: ");
        int userType = sc.nextInt();
        System.out.print("Enter username: ");
        String userName = sc.nextLine();
        System.out.print("Enter password: ");
        String userPass = sc.nextLine();
        
        String query = "INSERT INTO tbl_account (user_name, user_pass, user_firstname, user_lastname,"
                + "user_address, user_phone, user_is_admin)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try {
            connect();
            prepare = connect.prepareStatement(query);
            prepare.setString(1, userName);
            prepare.setString(2, userPass);
            prepare.setString(3, firstName);
            prepare.setString(4, lastName);
            prepare.setString(5, address);
            prepare.setInt(6, phone);
            prepare.setInt(7, userType);
            
            if (firstName.isEmpty() || lastName.isEmpty() || userName.isEmpty() || userPass.isEmpty())  {
                 String errorMsg = "Fields cannot be empty. Please try again!";
                 System.out.println(errorMsg);
                 
            } else {
                prepare.executeUpdate();
                System.out.println("Welcome " + firstName + "! You successfully created your account.");
                
                connect.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    // Login User Account
    public void loginUserAccount() {
        Scanner sc = new Scanner(System.in);
        System.out.println("** LOG IN **");
        
        System.out.print("Enter user name: ");
        String userLoginName = sc.nextLine();
        System.out.print("Enter user password: ");
        String userLoginPass = sc.nextLine();
        
        String query = "SELECT user_name, user_pass, user_is_admin FROM tbl_account "
                + "WHERE user_name = ? AND user_pass = ? AND archived = 0";
        
        try {
            connect();
            prepare = connect.prepareStatement(query);
            prepare.setString(1, userLoginName);
            prepare.setString(2, userLoginPass);
            result = prepare.executeQuery();
            
            if (result.next()) {
                int isAdmin = result.getInt("user_is_admin");
                
                System.out.println("Logged in Successfully!");
                if (isAdmin == 1) {
                   //bookDashBoard();
                } else {
                   // userMenu();
                }
            } else {
                System.out.println("Invalid Credentials!");
                loginUserAccount();
            }
        } catch (Exception e) {
            System.out.println("Login Failed " + e);
        }
    }
}
