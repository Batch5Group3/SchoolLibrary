
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
    public static final String GREEN = "\u001B[32m";
    public static final String RESET = "\033[0m";
    Scanner scanner = new Scanner(System.in);
    private final BookService bookService = new BookService();
    private final BookController bookController = new BookController();
    BookModel bookModel = new BookModel();

    public void bookMenu() throws SQLException {
        MainMenu main = new MainMenu();
        
        boolean show = true;
        while (show) {
            System.out.println("\t\t\t\t╔═══════════════════════╗");
            System.out.println("\t\t\t\t║\t\t📚 BOOKS MENU 📚\t\t║");
            System.out.println("\t\t\t\t╚═══════════════════════╝");
            System.out.println("\t\t\t\t  [1] ➕ Add Book");
            System.out.println("\t\t\t\t  [2] 📖 Show All Books");
            System.out.println("\t\t\t\t  [3] 🔍 Search Book");
            System.out.println("\t\t\t\t  [4] ✏️ Update a Book");
            System.out.println("\t\t\t\t  [5] 🗑️ Delete Book");
            System.out.println("\t\t\t\t  [6] 🔙 Back to Previous Menu");
            System.out.println("\t\t\t\t  [7] ❌ Exit Program");
            System.out.println("\t\t\t\t ════════════════════════");
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
                    findBookById();
                    break;
                case 4:
                   System.out.print("\t\t\t\tEnter the Book ID you want to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine(); // clear newline

                    BookModel existing = bookController.findBookById(updateId);
                    if (existing != null) {
                        BookModel updated = updateBook(existing); // call the view method
                        bookController.updateBook(updated); // pass to controller
                    } else {
                        showMessage(RED +"\t\t\t\t⚠ Book with ID " + updateId + " not found." + RESET);
                    }
                    waitForEnter(scanner);
                    break;
                case 5:
                    deleteBook();
                    break;
                case 6:
                    main.adminLogInMenu();
                    break;
                case 7:
                    main.exitProgram();
                    break;
                default:
                    System.out.println(RED + "\t\t\t\t⚠ Invalid choice. Please enter a number from the menu." + RESET);
                    bookMenu();
                    break;
            }
        }
    }
    
    public BookModel addBook() throws SQLException {
        System.out.println("\t\t\t\t╔═══════════════════════════════╗");
        System.out.println("\t\t\t\t║                  📘 ADD NEW BOOK                ║");
        System.out.println("\t\t\t\t╚═══════════════════════════════╝");
        System.out.print("\t\t\t\tEnter Title          : ");
        bookModel.setTitle(scanner.nextLine());
        System.out.print("\t\t\t\tEnter Author         : ");
        bookModel.setAuthor(scanner.nextLine());
        System.out.print("\t\t\t\tEnter Year Published : ");
        bookModel.setPubYear(scanner.nextInt());
        scanner.nextLine();
        System.out.print("\t\t\t\tEnter Type/Genre     : ");
        bookModel.setType(scanner.nextLine());
        
        if (bookModel.getTitle().isEmpty() || bookModel.getAuthor().isEmpty() || bookModel.getType().isEmpty()) {
                System.out.println(RED + "\t\t\t\t⚠ Fields cannot be empty. Please try again..." + RESET);
                addBook();
            } else {
            bookService.add(bookModel);
                System.out.println(GREEN+"\n\t\t\t\t✔ Book added successfully!"+RESET);
                displayBook();
            }
        return null;
    }
    
    public BookModel updateBook(BookModel existingBook) {
        System.out.println("\t\t\t\t╔═════════════════════════════════╗");
        System.out.println("\t\t\t\t║                 📖 UPDATE BOOK INFORMATION               ║");
        System.out.println("\t\t\t\t╚═════════════════════════════════╝");
        System.out.println(BLUE + "\t\t\t\tBook ID: " + RESET + existingBook.getId());
        System.out.println(BLUE +"\t\t\t\tCurrent Title: " + RESET + existingBook.getTitle());
        System.out.println(BLUE +"\t\t\t\tCurrent Author: " + RESET + existingBook.getAuthor());
        System.out.println(BLUE +"\t\t\t\tCurrent Published Year: " + RESET + existingBook.getPubYear());
        System.out.println(BLUE +"\t\t\t\tCurrent Type: " + RESET + existingBook.getType());
        System.out.println(BLUE +"\t\t\t\tCurrent Status: " + RESET + existingBook.getStatus());
        System.out.println("\t\t\t\t═══════════════════════════════════");
        System.out.print("\n\t\t\t\tEnter new title (press Enter to keep \"" + existingBook.getTitle() + "\"): ");
        String title = scanner.nextLine();
        if (title.isEmpty()) {
            title = existingBook.getTitle();
        }
        
        System.out.print("\t\t\t\tEnter new author (press Enter to keep \"" + existingBook.getAuthor() + "\"): ");
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

        System.out.print("\t\t\t\tEnter new type (press Enter to keep \"" + existingBook.getType() + "\"): ");
        String type = scanner.nextLine();
        if (type.isEmpty()) {
            type = existingBook.getType();
        }
        
        System.out.print("\t\t\t\tEnter new Status (press Enter to keep current): ");
        String status = scanner.nextLine();
        if (status.isEmpty()) status = existingBook.getStatus();
        
        System.out.println("\n\t\t\t\t═══════════════════════════════════");
        System.out.println("\t\t\t\tUPDATED INFORMATION: ");
        System.out.println("\t\t\t\t═══════════════════════════════════");
        System.out.println(GREEN +"\t\t\t\tID: " + RESET + existingBook.getId());
        System.out.println(GREEN +"\t\t\t\tTitle: " + RESET + title);
        System.out.println(GREEN +"\t\t\t\tAuthor: " + RESET + author);
        System.out.println(GREEN +"\t\t\t\tPublished Year: " + RESET + year);
        System.out.println(GREEN +"\t\t\t\tType: " + RESET + type);
        System.out.println(GREEN +"\t\t\t\tStatus: " + RESET + status);
        System.out.println("\t\t\t\t═══════════════════════════════════");
        
        return new BookModel(existingBook.getId(), title, author, year, type, status);
    }
    
    public void deleteBook() throws SQLException{
        
        System.out.print("\n\t\t\t\tEnter Book ID you want to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        BookModel book = bookController.findBookById(id);
        if (book == null) {
            System.out.println(RED + "\t\t\t\t⚠ No book found with the specified ID." + RESET);
            waitForEnter(scanner);
            bookMenu();
            return;
        }
            System.out.println("\n\t\t\t\tAre you sure you want to delete Book ID " + id);
            System.out.println("\t\t\t\t("+BLUE +book.getTitle()+RESET+")?");
            System.out.println("\t\t\t\t[Y] Yes\n\t\t\t\t[N] No");
            System.out.print(BLUE + "\t\t\t\tPlease enter your choice: " + RESET);
            String choice = scanner.nextLine();
            
            if ("Y".equalsIgnoreCase(choice)) {
                boolean isDeleted = bookController.deleteItem(id);
                if (isDeleted) {
                    System.out.println(GREEN+"\t\t\t\t✔ Book ID " + id + " was successfully deleted!"+RESET);
                } else {
                    System.out.println(RED + "\t\t\t\t⚠ No book found with the specified ID."+ RESET);
                }
            } else {
                System.out.println(RED + "\t\t\t\t❌ Delete canceled." + RESET);
            }
        waitForEnter(scanner);
        bookMenu();
    }
    
    public void displayBook() {
        List<BookModel> books = bookService.getAll();
        
            System.out.println();
            System.out.println(BLUE +"═══════════════════════════ BOOK LIST ═════════════════════════════════════" + RESET);
            System.out.println("══════════════════════════════════════════════════════════════════════");
            System.out.printf("| %-5s | %-30s | %-20s | %-15s | %-30s |\n", "ID", "Title", "Author", "Published Year", "Type");
            System.out.println("══════════════════════════════════════════════════════════════════════");
            for (BookModel book : books) {
                System.out.printf("| %-5s | %-30s | %-20s | %-15s | %-30s |\n",
                        book.getId(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getPubYear(),
                        book.getType());
            }
            System.out.println("══════════════════════════════════════════════════════════════════════");
            waitForEnter(scanner);
    }
    
    public void displayAvailableBook(){
        List<BookModel> books = bookService.getAvailableBooks();
        
            System.out.println();
            System.out.println(BLUE +"═════════════════════════ AVAILABLE BOOKS ═══════════════════════════════" + RESET);
            System.out.println("══════════════════════════════════════════════════════════════════");
            System.out.printf("| %-5s | %-30s | %-20s | %-15s | %-30s |\n", "ID", "Title", "Author", "Published Year", "Type");
            System.out.println("══════════════════════════════════════════════════════════════════");
            for (BookModel book : books) {
                System.out.printf("| %-5s | %-30s | %-20s | %-15s | %-30s |\n",
                        book.getId(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getPubYear(),
                        book.getType());
            }
            System.out.println("══════════════════════════════════════════════════════════════════");
    }
    
    public void findBookById() {
        
        System.out.print("\n\t\t\t\tEnter Book ID you want to find: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();
        BookModel book = bookService.getById(bookId);
        
        if (book != null) {
            System.out.println("══════════════════════════════════════════════════════════════════════════════");
            System.out.println("                                                  📘  BOOK DETAILS                             ");
            System.out.println("══════════════════════════════════════════════════════════════════════════════");
            System.out.printf("| %-5s | %-30s | %-20s | %-15s | %-30s | %-10s |\n", "ID", "Title", "Author", "Year", "Type", "Status");
            System.out.println("══════════════════════════════════════════════════════════════════════════════");
            System.out.printf("| %-5d | %-30s | %-20s | %-15d | %-30s | %-10s |\n", 
                  book.getId(), 
                  book.getTitle(), 
                  book.getAuthor(), 
                  book.getPubYear(), 
                  book.getType(), 
                  book.getStatus());
            System.out.println("══════════════════════════════════════════════════════════════════════════════");
            waitForEnter(scanner);
        } else {
            System.out.println(RED + "\t\t\t\tNo book found with the specified ID to update!" + RESET);
        }
    }
    
    public void displayBorrowedBook(){
        List<BookModel> books = bookService.getBorrowedBooks();
        
            System.out.println();
            System.out.println(BLUE +"══════════════════════════ BORROWED BOOKS ═══════════════════════════════" + RESET);
            System.out.println("══════════════════════════════════════════════════════════════════");
            System.out.printf("| %-5s | %-30s | %-20s | %-15s | %-30s |\n", "ID", "Title", "Author", "Published Year", "Type");
            System.out.println("══════════════════════════════════════════════════════════════════");
            for (BookModel book : books) {
                System.out.printf("| %-5s | %-30s | %-20s | %-15s | %-30s |\n",
                        book.getId(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getPubYear(),
                        book.getType());
                
            }
            System.out.println("══════════════════════════════════════════════════════════════════");
            waitForEnter(scanner);
    }
    

    
    public static void waitForEnter(Scanner sc) {
        System.out.print("\n\t\t\t\tPress Enter to return...");
        sc.nextLine();
    }
    
    
    public void showMessage(String message) {
        System.out.println(message);
    }
    
}
