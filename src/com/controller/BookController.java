
package com.controller;

import com.model.BookModel;
import com.services.BookService;
import java.util.List;

// The controller class handles the logic and coordination between View and Model classes
// has no direct input or output
public class BookController {
    // Utilizes the CrudService interface and injected with the Book model class
    private final BookService bookService = new BookService();
    
    // Read
    public List<BookModel> showAllBooks() {
        return bookService.getAll();
    }
    
     public List<BookModel> showAvailableBooks() {
        return bookService.getAvailableBooks();
    }
     
      public List<BookModel> showBorrowedBooks() {
        return bookService.getBorrowedBooks();
    }
    
    // Create
    public void addBook(BookModel book) {
        bookService.add(book);
    }
    
    public void updateBook(BookModel item) {
        bookService.updateItem(item);
    }
    
    public BookModel findBookById(int id) {
        return bookService.getById(id);
    }
    
    public boolean deleteItem(int id){
        return bookService.deleteItem(id);
    }
}
