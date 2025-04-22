
package com.view;

import com.app.util.DbConnection;
import com.controller.TransactionController;
import com.model.TransactionModel;
import com.services.TransactionService;
import static com.view.MainMenu.BLUE;
import static com.view.MainMenu.RESET;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class TransactionView extends DbConnection{
    Scanner scanner = new Scanner(System.in);
    private final TransactionService transactionService = new TransactionService();
    private final TransactionController transactionController = new TransactionController();
    private final MainMenu main = new MainMenu();

    public void transactionMenu() {
        int choice;        
        do {
            System.out.println("\n\t\t\t\t╔═════════════════════════╗");
            System.out.println("\t\t\t\t║   📝 MANAGE TRANSACTIONS   ║");
            System.out.println("\t\t\t\t╚═════════════════════════╝");
            System.out.println("\t\t\t\t   [1] View All Transactions");
            System.out.println("\t\t\t\t   [2] Mark Book as Returned");
            System.out.println("\t\t\t\t   [3] Apply Fine for Overdue Books");
            System.out.println("\t\t\t\t   [4] View Transaction History");
            System.out.println("\t\t\t\t   [5] Back to Admin Menu");
            System.out.println("\t\t\t\t   ══════════════════════════\n");
            System.out.print(BLUE + "\t\t\t\tPlease enter your choice: " + RESET);
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

    public void addTransaction() {
        TransactionModel transaction = new TransactionModel();

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
        transaction.setFineAmount(scanner.nextInt());
        scanner.nextLine();

        transactionController.addTransaction(transaction);
        System.out.println("Transaction added successfully.");
    }

    public void viewAllTransactions() {
        List<TransactionModel> transactions = transactionController.showAllTransactions();
        System.out.println("\n=== All Transactions ===");
        for (TransactionModel t : transactions) {
            displayTransaction(t);
        }
    }

    public void findTransactionById() {
        System.out.print("Enter Transaction ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        TransactionModel t = transactionController.findTransactionById(id);
        if (t != null) {
            displayTransaction(t);
        } else {
            System.out.println("Transaction not found.");
        }
    }

    public void updateTransaction() {
        System.out.print("Enter Transaction ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        TransactionModel existing = transactionController.findTransactionById(id);
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
        existing.setFineAmount(scanner.nextInt());

        transactionController.updateTransaction(existing);
        System.out.println("Transaction updated successfully.");
    }

    public void deleteTransaction() {
        System.out.print("Enter Transaction ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        transactionController.deleteTransaction(id);
        System.out.println("Transaction deleted successfully.");
    }

    public void displayTransaction(TransactionModel t) {
        System.out.println("ID: " + t.getId()
                + ", User ID: " + t.getUserId()
                + ", Book ID: " + t.getBookId()
                + ", Borrow Date: " + t.getBorrowDate()
                + ", Return Date: " + t.getReturnDate()
                + ", Fine: " + t.getFineAmount());
    }
}
