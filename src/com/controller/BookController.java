
package com.controller;

import com.model.Book;
import com.services.BookService;
import com.services.CrudService;
import java.util.List;

// The controller class handles the logic and coordination between View and Model classes
// has no direct input or output
public class BookController {
    // Utilizes the CrudService interface and injected with the Book model class
    private final CrudService<Book> bookService;
    
    public BookController() {
        // initializes this.bookService with BookService objects
        this.bookService = new BookService();
    }
    
    // Read
    public List<Book> showAllBooks() {
        return bookService.getAll();
    }
    
    // Create
    public void addBook(Book book) {
        bookService.add(book);
    }
}
