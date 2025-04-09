
package com.services;

import com.model.Book;
import java.util.ArrayList;
import java.util.List;

public class BookService implements CrudService<Book> {
    private final List<Book> books = new ArrayList<>();

    @Override
    public void add(Book item) {
        books.add(item);
    }

    @Override
    public List<Book> getAll() {
        return books;
    }

    @Override
    public Book getById(int id) {
        for (Book book : books) {
            if (book.getBookId() == id) {
                return book;
            }
        }
        return null;
    }

    @Override
    public void deleteBook(int id) {
        // removeIf(predicate) is a method of ArrayList class
        books.removeIf(book -> book.getBookId() == id);
    }
    
}
