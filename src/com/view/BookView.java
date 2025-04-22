
package com.view;

import com.app.util.DbConnection;
import com.controller.BookController;
import com.model.BookModel;
import com.services.BookService;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;


public class BookView extends DbConnection{
    public static final String RED = "\033[1;31m";
    public static final String BLUE = "\u001B[34m";
    public static final String RESET = "\033[0m";
    Scanner scanner = new Scanner(System.in);
    private final BookService bookService = new BookService();
    private final BookController bookController = new BookController();
    BookModel bookModel = new BookModel();

    public void showMainMenu() throws SQLException {
        MainMenu main = new MainMenu();
        
        boolean show = true;
        while (show) {
            System.out.println("\t\t\t\t╔════════════════════════╗");
            System.out.println("\t\t\t\t║             📚 BOOKS MENU 📚          ║");
            System.out.println("\t\t\t\t╚════════════════════════╝");
            System.out.println("\t\t\t\t  [1] ➕ Add Book");
            System.out.println("\t\t\t\t  [2] 📖 Show All Books");
            System.out.println("\t\t\t\t  [3] ✏️ Update a Book");
            System.out.println("\t\t\t\t  [4] 🗑️ Delete Book");
            System.out.println("\t\t\t\t  [5] 🔙 Back to Previous Menu");
            System.out.println("\t\t\t\t  [6] ❌ Exit Program");
            System.out.println("\t\t\t\t═════════════════════════");
            System.out.print(BLUE + "\t\t\t\tPlease enter your choice: " + RESET);
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    displayBook();
                    break;
                case 3:
                   System.out.print("\t\t\t\tEnter the Book ID you want to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine(); // clear newline

                    BookModel existing = bookController.findBookById(updateId);
                    if (existing != null) {
                        BookModel updated = updateBook(existing); // call the view method
                        bookController.updateBook(updated); // pass to controller
                    } else {
                        showMessage(RED +"\t\t\t\tBook with ID " + updateId + " not found." + RESET);
                    }
                    break;
                case 4:
                    deleteBook();
                    break;
                case 5:
                    main.adminLogInMenu();
                    break;
                case 6:
                    main.exitProgram();
                    break;
                default:
                    System.out.println(RED + "\t\t\t\t⚠ Invalid choice. Please enter a number from the menu." + RESET);
                    showMainMenu();
                    break;
            }
        }
    }
    
    public BookModel addBook() throws SQLException {
        System.out.println("t\t\t\t╔═══════════════════════════════╗");
        System.out.println("t\t\t\t║                  📘 ADD NEW BOOK                ║");
        System.out.println("t\t\t\t╚═══════════════════════════════╝");
        System.out.print("t\t\t\tEnter Title           : ");
        bookModel.setTitle(scanner.nextLine());
        System.out.print("t\t\t\tEnter Author          : ");
        bookModel.setAuthor(scanner.nextLine());
        System.out.print("t\t\t\tEnter Year Published  : ");
        bookModel.setPubYear(scanner.nextInt());
        scanner.nextLine();
        System.out.print("t\t\t\tEnter Type/Genre      : ");
        bookModel.setType(scanner.nextLine());
        
        if (bookModel.getTitle().isEmpty() || bookModel.getAuthor().isEmpty() || bookModel.getType().isEmpty()) {
                System.out.println(RED + "\t\t\t\t⚠ Fields cannot be empty. Please try again..." + RESET);
                addBook();
            } else {
            bookService.add(bookModel);
                System.out.println("\t\t\t\t✔ Book added successfully!");
                displayBook();
            }
        return null;
    }
    
    public BookModel updateBook(BookModel existingBook) {
        System.out.println("\nt\t\t\t╔═══════════════════════════════╗");
        System.out.println("\t\t\t\t║             📖 UPDATE BOOK INFORMATION           ║");
        System.out.println("t\t\t\t╚═══════════════════════════════╝");
        System.out.println("\t\t\t\tBook ID: " + existingBook.getId());
        System.out.println("\t\t\t\tCurrent Title: " + existingBook.getTitle());
        System.out.println("\t\t\t\tCurrent Author: " + existingBook.getAuthor());
        System.out.println("\t\t\t\tCurrent Published Year: " + existingBook.getPubYear());
        System.out.println("\t\t\t\tCurrent Type: " + existingBook.getType());
        System.out.println("\t\t\tCurrent Status: " + existingBook.getStatus());
        System.out.println("\t\t\t\t══════════════════════════════════");
        System.out.print("\t\t\t\tEnter new title (leave blank to keep \"" + existingBook.getTitle() + "\"): ");
        String title = scanner.nextLine();
        if (title.isEmpty()) {
            title = existingBook.getTitle();
        }
        
        System.out.print("\t\t\t\tEnter new author (leave blank to keep \"" + existingBook.getAuthor() + "\"): ");
        String author = scanner.nextLine();
        if (author.isEmpty()) {
            author = existingBook.getAuthor();
        }

        System.out.print("\t\t\t\tEnter new publication year (or 0 to keep \"" + existingBook.getPubYear() + "\"): ");
        int year;
        try {
            year = scanner.nextInt();
        } catch (Exception e) {
            year = 0; // fallback if user enters a non-int
            scanner.next(); // clear invalid input
        }
        scanner.nextLine(); // consume leftover newline
        if (year == 0) {
            year = existingBook.getPubYear();
        }

        System.out.print("\t\t\t\tEnter new type (leave blank to keep \"" + existingBook.getType() + "\"): ");
        String type = scanner.nextLine();
        if (type.isEmpty()) {
            type = existingBook.getType();
        }
        
        System.out.print("\t\t\tEnter new Status (leave blank to keep current): ");
        String status = scanner.nextLine();
        if (status.isEmpty()) status = existingBook.getStatus();
        
        System.out.println("\n\t\t\t\t═════════════════════════════════════════");
        System.out.println("\t\t\t\tUpdated Information: ");
        System.out.println("\t\t\t\t════════════════════════════════════════════");
        System.out.println("\t\t\t\tID: " + existingBook.getId());
        System.out.println("\t\t\t\tTitle: " + title);
        System.out.println("\t\t\t\tAuthor: " + author);
        System.out.println("\t\t\t\tPublished Year: " + year);
        System.out.println("\t\t\t\tType: " + type);
        System.out.println("\t\t\tStatus:            " + status);
        System.out.println("\t\t\t\t════════════════════════════════════════════");
        
        waitForEnter(scanner);
        return new BookModel(existingBook.getId(), title, author, year, type, status);
        
    }
    
    public void deleteBook() throws SQLException{
        System.out.print("\n\t\t\t\tEnter Book ID you want to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        try {
            System.out.println("\n\t\t\t\tAre you sure you want to delete Book ID " + id + "?");
            System.out.println("\t\t\t\t[Y] Yes\n\t\t\t\t[N] No");
            System.out.print(BLUE + "\t\t\t\tPlease enter your choice: " + RESET);
            String choice = scanner.nextLine();
            
            if ("Y".equalsIgnoreCase(choice)) {
                boolean isDeleted = bookController.deleteItem(id);
                if (isDeleted) {
                    System.out.println("\t\t\t\t✔ Book ID " + id + " was successfully deleted!");
                } else {
                    System.out.println(RED + "\t\t\t\t⚠ No book found with the specified ID."+ RESET);
                }
            } else {
                System.out.println(RED + "\t\t\t\t❌ Delete canceled." + RESET);
            }
        } catch (Exception e) {
            System.out.println("\t\t\t\tFailed to delete " + e);
        }
        waitForEnter(scanner);
        showMainMenu();
        
    }
    
    public void displayBook() {
        List<BookModel> books = bookService.getAll();
        
        try {
            System.out.println();
            System.out.println("\033[1;34m══════════════════════════════ BOOK LIST ════════════════════════════════════\033[0m");
            System.out.println("═════════════════════════════════════════════════════════════════════════");
            System.out.printf("| %-5s | %-30s | %-20s | %-15s | %-30s |\n", "ID", "Title", "Author", "Published Year", "Type");
            System.out.println("═════════════════════════════════════════════════════════════════════════");
            for (BookModel book : books) {
                System.out.printf("| %-5s | %-30s | %-20s | %-15s | %-30s |\n",
                        book.getId(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getPubYear(),
                        book.getType());
            }
            System.out.println("══════════════════════════════════════════════════════════════════════════");
            waitForEnter(scanner);
                
        } catch (Exception e) {
            System.out.println("\t\t\t\tFailed " + e);
        } 
    }
    
    public void displayAvailableBook(){
        List<BookModel> books = bookService.getAvailableBooks();
        
        try {
            System.out.println();
            System.out.println("\033[1;34m══════════════════════════════ AVAILABLE BOOKS ════════════════════════════════════\033[0m");
            System.out.println("═════════════════════════════════════════════════════════════════════════");
            System.out.printf("| %-5s | %-30s | %-20s | %-15s | %-30s |\n", "ID", "Title", "Author", "Published Year", "Type");
            System.out.println("═════════════════════════════════════════════════════════════════════════");
            for (BookModel book : books) {
                System.out.printf("| %-5s | %-30s | %-20s | %-15s | %-30s |\n",
                        book.getId(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getPubYear(),
                        book.getType());
                
            }
            System.out.println("══════════════════════════════════════════════════════════════════════════");
            waitForEnter(scanner);
                
        } catch (Exception e) {
            System.out.println("\t\t\t\tFailed " + e);
        } 
    }
    
    public void displayBorrowedBook(){
        List<BookModel> books = bookService.getBorrowedBooks();
        
        try {
            System.out.println();
            System.out.println("\033[1;34m══════════════════════════════ BORROWED BOOKS ════════════════════════════════════\033[0m");
            System.out.println("═════════════════════════════════════════════════════════════════════════");
            System.out.printf("| %-5s | %-30s | %-20s | %-15s | %-30s |\n", "ID", "Title", "Author", "Published Year", "Type");
            System.out.println("═════════════════════════════════════════════════════════════════════════");
            for (BookModel book : books) {
                System.out.printf("| %-5s | %-30s | %-20s | %-15s | %-30s |\n",
                        book.getId(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getPubYear(),
                        book.getType());
                
            }
            System.out.println("══════════════════════════════════════════════════════════════════════════");
            waitForEnter(scanner);
                
        } catch (Exception e) {
            System.out.println("\t\t\t\tFailed " + e);
        } 
    }
    
    public static void waitForEnter(Scanner sc) {
        System.out.print("\n\t\t\t\tPress Enter to return...");
        sc.nextLine();
    }
    public void showMessage(String message) {
        System.out.println(message);
    }
    
}
