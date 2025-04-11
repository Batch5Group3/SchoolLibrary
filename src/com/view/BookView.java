
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
            System.out.println("3. Exit");
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
        LocalDate pubDate = LocalDate.parse(scanner.nextLine());
        System.out.print("Enter type: ");
        String type = scanner.nextLine();

        return new Book(0, title, author, pubDate, type);
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
