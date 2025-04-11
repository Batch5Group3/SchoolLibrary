
package com.services;

import com.app.util.DbConnection;
import com.model.Book;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookService implements CrudService<Book> {
    private final List<Book> books = new ArrayList<>();
    private final DbConnection dbConnection = new DbConnection();

    @Override
    public void add(Book item) { 
        books.add(item);
    }

    @Override
    public List<Book> getAll() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM tblbooks";

        try (Connection conn = dbConnection.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Date sqlDate = rs.getDate("book_publication_date");
                LocalDate pubDate = (sqlDate != null) ? sqlDate.toLocalDate() : null;
                Book book = new Book(
                    rs.getInt("book_id"),
                    rs.getString("book_title"),
                    rs.getString("book_author"),
                    pubDate,
                    rs.getString("book_type")
                );
                books.add(book);
            }

        } catch (Exception e) {
            System.out.println("Error retrieving books: " + e.getMessage());
        }

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
    public void deleteItem(int id) {
        // removeIf(predicate) is a method of ArrayList class
        books.removeIf(book -> book.getBookId() == id);
    }
    
}
