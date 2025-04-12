
package com.services;

import com.app.util.DbConnection;
import com.model.Book;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
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
         String sql = "INSERT INTO tbl_libbook "
                 + "(book_title, book_author, book_year_published, book_type) " 
                 + "VALUES (?, ?, ?, ?)";

        try (Connection conn = dbConnection.connect();
            PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, item.getBookTitle());
            statement.setString(2, item.getBookAuthor());
            statement.setInt(3, item.getBookPublicationDate());
            statement.setString(4, item.getBookType());

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Book added successfully!");
            }

        } catch (Exception e) {
            System.out.println("Error adding book: " + e.getMessage());
        }
    }

    @Override
    public List<Book> getAll() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM tbl_libbook";

        try (Connection conn = dbConnection.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                //Date sqlDate = rs.getDate("book_publication_date");
                //LocalDate pubDate = (sqlDate != null) ? sqlDate.toLocalDate() : null;
                
                Book book = new Book(
                    rs.getInt("book_id"),
                    rs.getString("book_title"),
                    rs.getString("book_author"),
                    rs.getInt("book_year_published"),
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
       String selectSql = "SELECT * FROM tbl_libbook WHERE book_id = ?";
       Book existingBook = null;

       try (Connection conn = dbConnection.connect();
            PreparedStatement stmt = conn.prepareStatement(selectSql)) {

           stmt.setInt(1, id);
           ResultSet rs = stmt.executeQuery();

           if (rs.next()) {
               existingBook = new Book(
                   rs.getInt("book_id"),
                   rs.getString("book_title"),
                   rs.getString("book_author"),
                   rs.getInt("book_year_published"),
                   rs.getString("book_type")
               );
           } else {
               System.out.println("No book found with the specified ID.");
               return null;
           }

       } catch (Exception e) {
           System.out.println("Error fetching book details: " + e.getMessage());
           return null;
       }
       return existingBook;
    }

    @Override
    public void deleteItem(int id) {
        // removeIf(predicate) is a method of ArrayList class
        books.removeIf(book -> book.getBookId() == id);
    }

    @Override
    public void updateItem(Book book) {
        String updateSql = "UPDATE tbl_libbook SET book_title = ?, book_author = ?, book_year_published = ?, book_type = ? " +
                       "WHERE book_id = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(updateSql)) {

            stmt.setString(1, book.getBookTitle());
            stmt.setString(2, book.getBookAuthor());
            stmt.setInt(3, book.getBookPublicationDate());
            stmt.setString(4, book.getBookType());
            stmt.setInt(5, book.getBookId());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Book updated successfully!");
            } else {
                System.out.println("No book found with the specified ID.");
            }

        } catch (Exception e) {
            System.out.println("Error updating book: " + e.getMessage());
        }
    }
    
}
