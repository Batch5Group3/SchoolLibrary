
package com.controller;

import com.model.Book;
import com.services.BookService;
import com.services.CrudService;
import java.util.List;

// The controller class handles the logic and coordination between View and Model classes
// has no direct input or output
public class BookController {
    private final CrudService<Book> bookService;
    
    public BookController() {
        this.bookService = new BookService();
    }
    
    public List<Book> showAllBooks() {
        return bookService.getAll();
    }
    
    public void addBook(Book book) {
        bookService.add(book);
    }
}
