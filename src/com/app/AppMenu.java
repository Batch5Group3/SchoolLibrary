
package com.app;

import com.view.AccountView;
import com.view.BookView;
import com.view.TransactionView;
import java.util.Scanner;

public class AppMenu {
    private final BookView bookView;
    private final AccountView accountView;
    private final TransactionView transactionView;
    private final Scanner scanner;

    public AppMenu(BookView bookView, AccountView accountView, TransactionView transactionView) {
        this.bookView = bookView;
        this.accountView = accountView;
        this.transactionView = transactionView;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        int choice;

        do {
            System.out.println("\n=== Main Menu ===");
            System.out.println("1. Book Management");
            System.out.println("2. Account Management");
            System.out.println("3. Transaction Management");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    bookView.showMainMenu();
                    break;
                case 2:
                    accountView.showMenu();
                    break;
                case 3:
                    transactionView.showMenu();
                    break;
                case 4:
                    System.out.println("Exiting the app...");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (choice != 4);
    }
}
