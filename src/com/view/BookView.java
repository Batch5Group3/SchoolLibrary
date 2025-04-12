
package com.view;

import com.controller.BookController;
import com.model.Book;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

// This class represents the html code if this was done in a web app
// View class handles all user interactions
public class BookView {
    private final BookController controller;
    private final Scanner scanner;
    
    public BookView(BookController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void showMainMenu() {
        boolean show = true;
        while (show) {
            System.out.println("=== School Library Management System ===");
            System.out.println("1. Add book");
            System.out.println("2. Show all books");
            System.out.println("3. Update a book");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice) {
                case 1:
                    Book newBook = readBookDetails();
                    controller.addBook(newBook);
                    showMessage("Successfully added a new book...");
                    break;
                case 2:
                    List<Book> books = controller.showAllBooks();
                    displayBookList(books);
                    break;
                case 3:
                   System.out.print("Enter the ID of the book to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine(); // clear newline

                    Book existing = controller.findBookById(updateId);
                    if (existing != null) {
                        Book updated = updateBook(existing); // call the view method
                        controller.updateBook(updated); // pass to controller
                        showMessage("Book updated successfully.");
                    } else {
                        showMessage("Book with ID " + updateId + " not found.");
                    }
                    break;
                case 4:
                    show = false;
                    System.out.println("Exiting the app...");
                    break;
                default:
                    showMessage("Invalid option...");
            }
        }
    }
    
    public Book readBookDetails() {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Enter publication date (YYYY-MM-DD): ");
        int pubDate = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter type: ");
        String type = scanner.nextLine();

        return new Book(0, title, author, pubDate, type);
    }
    
    public Book updateBook(Book existingBook) {
       System.out.println("Updating book with ID: " + existingBook.getBookId());

        System.out.print("Enter new title (leave blank to keep \"" + existingBook.getBookTitle() + "\"): ");
        String title = scanner.nextLine();
        if (title.isEmpty()) {
            title = existingBook.getBookTitle();
        }

        System.out.print("Enter new author (leave blank to keep \"" + existingBook.getBookAuthor() + "\"): ");
        String author = scanner.nextLine();
        if (author.isEmpty()) {
            author = existingBook.getBookAuthor();
        }

        System.out.print("Enter new publication year (or 0 to keep \"" + existingBook.getBookPublicationDate() + "\"): ");
        int year;
        try {
            year = scanner.nextInt();
        } catch (Exception e) {
            year = 0; // fallback if user enters a non-int
            scanner.next(); // clear invalid input
        }
        scanner.nextLine(); // consume leftover newline
        if (year == 0) {
            year = existingBook.getBookPublicationDate();
        }

        System.out.print("Enter new type (leave blank to keep \"" + existingBook.getBookType() + "\"): ");
        String type = scanner.nextLine();
        if (type.isEmpty()) {
            type = existingBook.getBookType();
        }

        return new Book(existingBook.getBookId(), title, author, year, type);
    }
    
    public void displayBookList(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }
        books.forEach((book) -> {
            displayBook(book);
        });
    }
    
    public void displayBook(Book book) {
        System.out.println("==== Book/s available ====");
        System.out.println("Title: " + book.getBookTitle());
        System.out.println("Author: " + book.getBookAuthor());
        System.out.println("Type: " + book.getBookType());
        System.out.println("publishing date: " + book.getBookPublicationDate());
        System.out.println();
    }
    
    public void showMessage(String message) {
        System.out.println(message);
    }
    
}
