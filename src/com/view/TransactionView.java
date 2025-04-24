
package com.view;

import com.app.util.DbConnection;
import com.controller.TransactionController;
import com.model.BookModel;
import com.model.TransactionModel;
import com.services.BookService;
import com.services.TransactionService;
import static com.view.MainMenu.BLUE;
import static com.view.MainMenu.RESET;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class TransactionView extends DbConnection{
    public static final String RED = "\033[1;31m";
    public static final String BLUE = "\u001B[34m";
    public static final String GREEN = "\u001B[32m";
    public static final String RESET = "\033[0m";
    Scanner scanner = new Scanner(System.in);
    private final TransactionModel transaction = new TransactionModel();
    private final TransactionService transactionService = new TransactionService();
    private final TransactionController transactionController = new TransactionController();
    private final MainMenu main = new MainMenu();

    public void transactionMenu() throws SQLException {
        int choice;
        System.out.println("\t\t\t\t╔═════════════════════════╗");
        System.out.println("\t\t\t\t║           📝 MANAGE TRANSACTIONS           ║");
        System.out.println("\t\t\t\t╚═════════════════════════╝");
        System.out.println("\t\t\t\t   [1] 📄 View All Transactions");
        System.out.println("\t\t\t\t   [2] 🔍 Search Transaction");
        System.out.println("\t\t\t\t   [3] 🗑️ Delete Transaction");
        System.out.println("\t\t\t\t   [4] 🔙 Back to Main Menu");
        System.out.println("\t\t\t\t ══════════════════════════");
        System.out.print(BLUE + "\t\t\t\tPlease enter your choice: " + RESET);
        choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1:
                displayTransaction();
                break;
            case 2:
                findTransactionById();
                break;
            case 3:
                deleteTransaction();
                break;
            case 4:
                main.adminLogInMenu();
                break;
            default:
                System.out.println("Invalid option.");
        }
    }
    
    public void displayTransaction() throws SQLException {
        List<TransactionModel> transaction = transactionService.getAll();
        System.out.println();
        System.out.println(BLUE +"\n════════════════════ TRANSACTION DETAILS ═════════════════════"+ RESET);
        System.out.println("═════════════════════════════════════════════════════");
        System.out.printf("| %-13s | %-10s | %-10s | %-15s | %-15s | %-10s |\n",
                "Transaction ID", "User ID", "Book ID", "Borrow Date", "Return Date", "Fine");
        System.out.println("═════════════════════════════════════════════════════");
        for (TransactionModel t : transaction) {
            System.out.printf("| %-14d | %-10d | %-10d | %-15s | %-15s | ₱%-9.2f |\n",
                    t.getId(),
                    t.getUserId(),
                    t.getBookId(),
                    t.getBorrowDate(),
                    t.getReturnDate(),
                    t.getFineAmount());
        }
        System.out.println("═════════════════════════════════════════════════════");
        waitForEnter(scanner);
        transactionMenu();
    }

    public void findTransactionById() throws SQLException {
        List<TransactionModel> transaction = transactionService.getAll();
        System.out.print("\n\t\t\t\tEnter Transaction ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        TransactionModel t = transactionController.findTransactionById(id);
        if (t != null) {
            System.out.println(BLUE +"\n════════════════════ TRANSACTION DETAILS ═════════════════════"+ RESET);
        System.out.println("═════════════════════════════════════════════════════");
        System.out.printf("| %-13s | %-10s | %-10s | %-15s | %-15s | %-10s |\n",
                "Transaction ID", "User ID", "Book ID", "Borrow Date", "Return Date", "Fine");
        System.out.println("═════════════════════════════════════════════════════");
        System.out.printf("| %-14d | %-10d | %-10d | %-15s | %-15s | ₱%-9.2f |\n",
                t.getId(),
                t.getUserId(),
                t.getBookId(),
                t.getBorrowDate(),
                t.getReturnDate() != null ? t.getReturnDate() : "Not returned",
                t.getFineAmount());
        
        System.out.println("═════════════════════════════════════════════════════");
        } else {
            System.out.println(RED+"\t\t\t\t⚠ Transaction not found."+RESET);
        }
        waitForEnter(scanner);
        transactionMenu();
    }
    
    public void returnBookByTransactionId() throws SQLException {
        List<TransactionModel> transaction = transactionService.getAll();
        System.out.println("\n══════════════════════════════════════════════════════");
        System.out.println("                                📚  RETURN BOOK  📚 ");
        System.out.println("══════════════════════════════════════════════════════");
        System.out.printf("| %-13s | %-10s | %-10s | %-15s | %-15s | %-10s |\n",
                "Transaction ID", "User ID", "Book ID", "Borrow Date", "Return Date", "Fine");
        System.out.println("══════════════════════════════════════════════════════");
        for (TransactionModel t : transaction) {
            System.out.printf("| %-14d | %-10d | %-10d | %-15s | %-15s | ₱%-9.2f |\n",
                    t.getId(),
                    t.getUserId(),
                    t.getBookId(),
                    t.getBorrowDate(),
                    t.getReturnDate(),
                    t.getFineAmount());
        }
        System.out.println("══════════════════════════════════════════════════════");
        
        System.out.print("\n\t\t\t\tEnter Transaction ID: ");
        int transactionId = scanner.nextInt();
        scanner.nextLine();
        
        TransactionModel selectedTransaction = transactionService.getTransactionById(transactionId);
        if (selectedTransaction == null) {
            System.out.println(RED + "\t\t\t\t❌ Transaction not found." + RESET);
            waitForEnter(scanner);
            main.adminLogInMenu();
            return;
        }

        if (selectedTransaction.getReturnDate() != null) {
            System.out.println(RED + "\t\t\t\t❌ This book was already returned on " + selectedTransaction.getReturnDate() + "." + RESET);
            waitForEnter(scanner);
            main.adminLogInMenu();
            return;
        }

        double fine = transactionController.previewFineAmount(transactionId);
        System.out.println("\t\t\t\tFine (if returned today): ₱" + fine);

        System.out.print("\t\t\t\tConfirm return?");
        System.out.println("\n\t\t\t\t[Y] Yes\n\t\t\t\t[N] No");
        System.out.print(BLUE + "\t\t\t\tPlease enter your choice: " + RESET);
        String confirm = scanner.nextLine();
        if ("Y".equalsIgnoreCase(confirm)) {
            boolean success = transactionService.returnBookTransaction(transactionId, fine);
            if (success) {
                System.out.println(GREEN+"\n\t\t\t\t✔ Book marked as returned."+RESET);
            } else {
                System.out.println(RED+"\t\t\t\t❌ Failed to mark book as returned."+RESET);
            }
        } else {
            System.out.println("\t\t\t\t❌ Return cancelled.");
        }

        waitForEnter(scanner);
        main.adminLogInMenu();
}
    
    public void borrowBookTransaction() throws SQLException {
        BookService bookService = new BookService();
        System.out.println("\t\t\t\t╔═════════════════════════════╗");
        System.out.println("\t\t\t\t║                 📚 BORROW A BOOK                 ║");
        System.out.println("\t\t\t\t╚═════════════════════════════╝");

        System.out.print("\t\t\t\tEnter User ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); 
        
        System.out.print("\t\t\t\tEnter the Book ID you want to borrow: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("\t\t\t\tEnter Borrow Date (yyyy-mm-dd): ");
        String borrowDateStr = scanner.nextLine();
        System.out.println("\t\t\t\t═══════════════════════════════");

        BookModel book = bookService.getById(bookId);
        
        if (book == null || !book.getStatus().equalsIgnoreCase("Available")) {
            System.out.println("\t\t\t\t⚠ Book is not available for borrowing.");
            return;
        }
        
        transaction.setUserId(userId);
        transaction.setBookId(bookId);
        transaction.setBorrowDate(Date.valueOf(borrowDateStr));
        transaction.setReturnDate(null);
        transaction.setFineAmount(0);
        
        boolean success = transactionService.borrowBookTransaction(transaction);
        
        if (success) {
            System.out.println(GREEN+"\n\t\t\t\t📖 Book Borrowed Successfully!"+RESET);
            System.out.println("\t\t\t\t════════════════════════════════");
            System.out.println("\t\t\t\tUser ID       : " + transaction.getUserId());
            System.out.println("\t\t\t\tBook ID       : " + transaction.getBookId());
            System.out.println("\t\t\t\tBorrow Date   : " + transaction.getBorrowDate());
            System.out.println("\t\t\t\tReturn Date   : " + (transaction.getReturnDate() != null ? transaction.getReturnDate() : "Not returned yet"));
            System.out.println("\t\t\t\tFine Amount   : ₱" + transaction.getFineAmount());
            System.out.println("\t\t\t\t════════════════════════════════");
            System.out.println(BLUE + "\t\t\t\t📌 Please return the book within 5 days to avoid a late fee." + RESET);
        }else {
            System.out.println("\t\t\t\t❌ Failed to borrow book. Please check book availability or try again.");
        }
        waitForEnter(scanner);
    }

    public void deleteTransaction() throws SQLException {
        System.out.print("\n\n\t\t\t\tEnter Transaction ID you want to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        System.out.println("\t\t\t\tAre you sure you want to delete Transaction ID " + id + "?");
        System.out.println("\t\t\t\t[Y] Yes\n\t\t\t\t[N] No");
        System.out.print("\t\t\t\tEnter choice: ");
        String choice = scanner.nextLine();
        if ("Y".equalsIgnoreCase(choice)) {
            transactionService.deleteItem(id);
            System.out.println("\t\t\t\tTransaction ID " + id + " is successfully deleted!");

        } else {
            System.out.println(RED+"\t\t\t\tDelete canceled."+RESET);
        }
        waitForEnter(scanner);
        transactionMenu();
    }

    
    public static void waitForEnter(Scanner sc) {
        System.out.print("\n\t\t\t\tPress Enter to return...");
        sc.nextLine();
    }
}
