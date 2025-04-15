
package com.view;

import com.controller.AccountController;
import com.model.Account;
import java.util.List;
import java.util.Scanner;

public class AccountView {
    private final Scanner scanner = new Scanner(System.in);
    private final AccountController accountController;

    public AccountView(AccountController accountController) {
        this.accountController = accountController;
    }

    public void showMenu() {
        int choice;
        do {
            System.out.println("\n=== Account Management Menu ===");
            System.out.println("1. Add Account");
            System.out.println("2. View All Accounts");
            System.out.println("3. View Account By ID");
            System.out.println("4. Update Account");
            System.out.println("5. Delete Account");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addAccount();
                    break;
                case 2:
                    viewAllAccounts();
                    break;
                case 3:
                    viewAccountById();
                    break;
                case 4:
                    updateAccount();
                    break;
                case 5:
                    deleteAccount();
                    break;
                case 6:
                    System.out.println("Exiting th Account Menu...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 6);
    }

    private void addAccount() {
        Account acc = inputAccountData(false);
        accountController.addAccount(acc);
        System.out.println("Account added.");
    }

    private void viewAllAccounts() {
        List<Account> accounts = accountController.showAllAccount();
        System.out.println("==== Accounts ====");
        for (Account acc : accounts) {
            System.out.println("User Name: " + acc.getUserName());
            System.out.println("First Name: " + acc.getUserFirstname());
            System.out.println("Contact#: " + acc.getUserContactNo());
            System.out.println();
        }
    }

    private void viewAccountById() {
        System.out.print("Enter User ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Account acc = accountController.findAccountById(id);
        if (acc != null) {
            System.out.println(acc);
        } else {
            System.out.println("Account not found.");
        }
    }

    private void updateAccount() {
        System.out.print("Enter User ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Account existing = accountController.findAccountById(id);
        if (existing != null) {
            System.out.println("Enter new account details:");
            Account updated = inputAccountData(true);
            updated.setUserId(id);
            accountController.updateAccount(updated);
            System.out.println("Account updated.");
        } else {
            System.out.println("Account not found.");
        }
    }

    private void deleteAccount() {
        System.out.print("Enter User ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Account acc = accountController.findAccountById(id);
        if (acc != null) {
            accountController.deleteAccount(id);
            System.out.println("Account deleted.");
        } else {
            System.out.println("Account not found.");
        }
    }

    private Account inputAccountData(boolean isUpdate) {
        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        System.out.print("First Name: ");
        String firstname = scanner.nextLine();

        System.out.print("Last Name: ");
        String lastname = scanner.nextLine();

        System.out.print("Address: ");
        String address = scanner.nextLine();

        System.out.print("Contact No: ");
        String contact = scanner.nextLine();

        System.out.print("Is Admin (true/false): ");
        boolean isAdmin = scanner.nextBoolean();
        scanner.nextLine(); // consume newline

        return new Account(0, username, password, firstname, lastname, address, contact, isAdmin);
    }
}
