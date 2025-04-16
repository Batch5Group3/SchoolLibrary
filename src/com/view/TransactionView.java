
package com.view;

import com.controller.TransactionController;
import com.model.Transaction;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class TransactionView {
     private final TransactionController controller;
    private final Scanner scanner;

    public TransactionView(TransactionController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        int choice;        
        do {
            System.out.println("\n=== Transaction Menu ===");
            System.out.println("1. Add Transaction");
            System.out.println("2. View All Transactions");
            System.out.println("3. Find Transaction by ID");
            System.out.println("4. Update Transaction");
            System.out.println("5. Delete Transaction");
            System.out.println("6. Back to Main Menu");
            System.out.print("Choose option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addTransaction();
                    break;
                case 2:
                    viewAllTransactions();
                    break;
                case 3:
                    findTransactionById();
                    break;
                case 4:
                    updateTransaction();
                    break;
                case 5:
                    deleteTransaction();
                    break;
                case 6:
                    System.out.println("Exiting the Transaction Menu...");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (choice != 6);
    }

    private void addTransaction() {
        Transaction transaction = new Transaction();

        System.out.print("Enter User ID: ");
        transaction.setUserId(scanner.nextInt());

        System.out.print("Enter Book ID: ");
        transaction.setBookId(scanner.nextInt());
        scanner.nextLine();

        System.out.print("Enter Borrow Date (yyyy-mm-dd): ");
        transaction.setBorrowDate(Date.valueOf(scanner.nextLine()));

        System.out.print("Enter Return Date (yyyy-mm-dd): ");
        transaction.setReturnDate(Date.valueOf(scanner.nextLine()));

        System.out.print("Enter Fine Amount: ");
        transaction.setFineAmount(scanner.nextDouble());

        controller.addTransaction(transaction);
        System.out.println("Transaction added successfully.");
    }

    private void viewAllTransactions() {
        List<Transaction> transactions = controller.showAllTransactions();
        System.out.println("\n=== All Transactions ===");
        for (Transaction t : transactions) {
            displayTransaction(t);
        }
    }

    private void findTransactionById() {
        System.out.print("Enter Transaction ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Transaction t = controller.findTransactionById(id);
        if (t != null) {
            displayTransaction(t);
        } else {
            System.out.println("Transaction not found.");
        }
    }

    private void updateTransaction() {
        System.out.print("Enter Transaction ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Transaction existing = controller.findTransactionById(id);
        if (existing == null) {
            System.out.println("Transaction not found.");
            return;
        }

        System.out.print("Enter New User ID (" + existing.getUserId() + "): ");
        existing.setUserId(scanner.nextInt());

        System.out.print("Enter New Book ID (" + existing.getBookId() + "): ");
        existing.setBookId(scanner.nextInt());
        scanner.nextLine();

        System.out.print("Enter New Borrow Date (" + existing.getBorrowDate() + ") [yyyy-mm-dd]: ");
        existing.setBorrowDate(Date.valueOf(scanner.nextLine()));

        System.out.print("Enter New Return Date (" + existing.getReturnDate() + ") [yyyy-mm-dd]: ");
        existing.setReturnDate(Date.valueOf(scanner.nextLine()));

        System.out.print("Enter New Fine Amount (" + existing.getFineAmount() + "): ");
        existing.setFineAmount(scanner.nextDouble());

        controller.updateTransaction(existing);
        System.out.println("Transaction updated successfully.");
    }

    private void deleteTransaction() {
        System.out.print("Enter Transaction ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        controller.deleteTransaction(id);
        System.out.println("Transaction deleted successfully.");
    }

    private void displayTransaction(Transaction t) {
        System.out.println("ID: " + t.getTransactionId()
                + ", User ID: " + t.getUserId()
                + ", Book ID: " + t.getBookId()
                + ", Borrow Date: " + t.getBorrowDate()
                + ", Return Date: " + t.getReturnDate()
                + ", Fine: " + t.getFineAmount());
    }
}
