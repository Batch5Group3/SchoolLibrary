package com.view;

import com.controller.LoginController;

import java.util.Scanner;

public class LoginView {
    private final LoginController loginController = new LoginController();

    public boolean showLogin() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Login ===");
        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (loginController.login(username, password)) {
            System.out.println("Login successful!");
            
            return true;
        } else {
            System.out.println("Invalid username or password.");
            return false;
        }
    }
}
