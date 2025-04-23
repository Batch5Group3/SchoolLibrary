
package com.services;

import com.app.util.DbConnection;
import com.dao.BookDAO;
import com.model.BookModel;
import java.util.ArrayList;
import java.util.List;

public class BookService extends DbConnection implements BookDAO<BookModel> {

    @Override
    public boolean add(BookModel book) { 
         String query = "INSERT INTO tbl_libbook "
                 + "(book_title, book_author, book_year_published, book_type) " 
                 + "VALUES (?, ?, ?, ?)";

        try {
            connect();
            prepare = connect.prepareStatement(query);
            prepare.setString(1, book.getTitle());
            prepare.setString(2, book.getAuthor());
            prepare.setInt(3, book.getPubYear());
            prepare.setString(4, book.getType());
            return prepare.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error adding book: " + e.getMessage()); 
        }
        return false;
    }

    @Override
    public List<BookModel> getAll() {
        String query = "SELECT * FROM tbl_libbook";
        List<BookModel> books = new ArrayList<>();

        try {
            connect();
            state = connect.createStatement();
            result = state.executeQuery(query);

            while (result.next()) {
                books.add(new BookModel(
                        result.getInt("book_id"),
                        result.getString("book_title"),
                        result.getString("book_author"),
                        result.getInt("book_year_published"),
                        result.getString("book_type"),
                        result.getString("book_status")));
            }
        } catch (Exception e) {
            System.out.println("Error retrieving books: " + e.getMessage());
        }
        return books;
    }

    @Override
    public BookModel getById(int id) {
       String query = "SELECT * FROM tbl_libbook WHERE book_id = ?";
       BookModel existingBook = null;

       try {
            connect();
            prepare = connect.prepareStatement(query);
            prepare.setInt(1, id);
            result = prepare.executeQuery();
            if (result.next()) {
               existingBook = new BookModel(
                       result.getInt("book_id"),
                       result.getString("book_title"),
                       result.getString("book_author"),
                       result.getInt("book_year_published"),
                       result.getString("book_type"),
                       result.getString("book_status"));
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
    public boolean deleteItem(int id) {
        String query = "DELETE FROM tbl_libbook WHERE book_id = ?";
        
        try {
            connect();
            prepare = connect.prepareStatement(query);
            prepare.setInt(1, id);
            int rowsDeleted = prepare.executeUpdate();
            return rowsDeleted > 0;
    } catch (Exception e) {
        System.out.println("Error deleting book: " + e.getMessage());
    } 
        return false;
    }

    @Override
    public void updateItem(BookModel book) {
        String query = "UPDATE tbl_libbook SET book_title = ?, book_author = ?, book_year_published = ?, book_type = ? " +
                       "WHERE book_id = ?";

        try {
            connect();
            prepare = connect.prepareStatement(query);
            prepare.setString(1, book.getTitle());
            prepare.setString(2, book.getAuthor());
            prepare.setInt(3, book.getPubYear());
            prepare.setString(4, book.getType());
            prepare.setInt(5, book.getId());
            
            int rowsUpdated = prepare.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("\t\t\t\t✔Book updated successfully!");
            } else {
                System.out.println("\t\t\t\t⚠No book found with the specified ID.");
            }
        } catch (Exception e) {
            System.out.println("Error updating book: " + e.getMessage());
        }
    }
    
    @Override
    public void updateBookStatus(int bookId, String newStatus) {
        String query = "UPDATE tbl_libbook SET book_status = ? WHERE book_id = ?";
        try {
            connect();
            prepare = connect.prepareStatement(query);
            prepare.setString(1, newStatus);
            prepare.setInt(2, bookId);
            prepare.executeUpdate();
        
        } catch (Exception e) {
            System.out.println("Error updating book status: " + e.getMessage());
        }
    }
    
    
    @Override
    public List<BookModel> getAvailableBooks() {
        String query = "SELECT * FROM tbl_libbook WHERE book_status = 'Available'";
        List<BookModel> availableBooks = new ArrayList<>();

        try {
            connect();
            state = connect.createStatement();
            result = state.executeQuery(query);

            while (result.next()) {
                availableBooks.add(new BookModel(
                        result.getInt("book_id"),
                        result.getString("book_title"),
                        result.getString("book_author"),
                        result.getInt("book_year_published"),
                        result.getString("book_type"),
                        result.getString("book_status")));
            }
        } catch (Exception e) {
            System.out.println("Error retrieving books: " + e.getMessage());
        }
        return availableBooks;
    }

    @Override
    public List<BookModel> getBorrowedBooks() {
        String query = "SELECT * FROM tbl_libbook WHERE book_status = 'Borrowed'";
        List<BookModel> availableBooks = new ArrayList<>();

        try {
            connect();
            state = connect.createStatement();
            result = state.executeQuery(query);

            while (result.next()) {
                availableBooks.add(new BookModel(
                        result.getInt("book_id"),
                        result.getString("book_title"),
                        result.getString("book_author"),
                        result.getInt("book_year_published"),
                        result.getString("book_type"),
                        result.getString("book_status")));
            }
        } catch (Exception e) {
            System.out.println("Error retrieving books: " + e.getMessage());
        }
        return availableBooks;
    }

    
}
