
package com.controller;

import com.dao.BookDAO;
import com.model.BookModel;
import com.services.BookService;
import java.util.List;


public class BookController {
    private final BookDAO<BookModel> bookService;
    
    public BookController() {
        this.bookService = new BookService();
    }
    
    public List<BookModel> showAllBooks() {
        return bookService.getAll();
    }
    
     public List<BookModel> showAvailableBooks() {
        return bookService.getAvailableBooks();
    }
     
      public List<BookModel> showBorrowedBooks() {
        return bookService.getBorrowedBooks();
    }
    
    public void addBook(BookModel book) {
        bookService.add(book);
    }
    
    public void updateBook(BookModel item) {
        bookService.updateItem(item);
    }
    
    public void updateBookStatus (int id, String newStatus){
        bookService.updateBookStatus(id, newStatus);
    }
    
    public BookModel findBookById(int id) {
        return bookService.getById(id);
    }
    
    public boolean deleteItem(int id){
        return bookService.deleteItem(id);
    }
}
