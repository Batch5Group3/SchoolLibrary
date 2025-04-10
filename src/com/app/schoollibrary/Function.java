
package com.app.schoollibrary;

import com.app.util.DbConnection;
import java.sql.SQLException;
import java.util.Scanner;


public class Function extends DbConnection {
    
    // Read Operation/API
    public void displayBooks() throws SQLException{
        String query = "SELECT * FROM tbl_libbook";
        
        try {
          connect();  
          //execute query
          state = connect.createStatement();
          result = state.executeQuery(query);
          
            System.out.println("ID\tTITLE\t\t\t\t\tAUTHOR\t\t\t\tPUBLICATION DATE\t\tBOOK TYPE");
          while(result.next()){
              int id = result.getInt("book_id");
              String title = result.getString("book_title");
              String author = result.getString("book_author");
              int pubDate = result.getInt("book_publication_date");
              String type = result.getString("book_type");
              
              System.out.println(id + "\t" + title + "\t\t\t" + author + "\t\t\t\t"
              + pubDate + "\t\t\t" + type);
          }
          
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally{
            connect.close();
        }
        
    }
    
    //create Operation
    public void addBooks(String title, String author, int pubDate, String type) throws SQLException{
        String query = "INSERT INTO tbl_libbook(book_title, book_author, book_publication_date, book_type)"
                + "VALUES(?, ?, ?, ?)";
        
        try {
            connect();
            // execute query
            prepare = connect.prepareStatement(query);
            // set parameter = wildcards;
            prepare.setString(1, title);
            prepare.setString(2, author);
            prepare.setInt(3, pubDate);
            prepare.setString(4, type);
            prepare.executeUpdate();
            
            System.out.println( title + "book. Added successfully!");

            displayBooks();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally{
            connect.close();
        }
    }
    
    //update Operation
    public void updateBooks(String title, String author, int pubDate, String type, int bookId) throws SQLException{
        String query = "UPDATE tbl_libbook SET book_title = ?, book_author = ?, book_publication_date = ?, book_type = ? where book_id = ?"; //ANTI SQL Injection/parameterized query
        
        try {
            connect();
            // execute query
            prepare = connect.prepareStatement(query);
            // set parameter = wildcards;
            prepare.setString(1, title);
            prepare.setString(2, author);
            prepare.setInt(3, pubDate);
            prepare.setString(4, type);
            prepare.setInt(5, bookId);
            prepare.executeUpdate();
            
            System.out.println( title + "book. Update successfully!");

            displayBooks();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally{
            connect.close();
        }
    }
    
    //Delete API
    //Soft Delete / Archive
    public void archiveBooks(int bookId) throws SQLException{
        String query = "UPDATE tbl_libbook SET archived = 1 where book_id = ?";
        
        try {
            prepare = connect.prepareStatement(query);
            prepare.setInt(1, bookId);
            prepare.executeUpdate();
            System.out.println("Book ID " + bookId + "Archived Successfully!");
            displayBooks();
            
        } catch (Exception e) {
        } finally{
            connect.close();
        }
    }
    
    //Restore Archived Books
    public void restoreBooks(int bookId) throws SQLException{
        String query = "UPDATE tbl_libbook SET archived = 0 where book_id = ?";
        
        try {
            prepare = connect.prepareStatement(query);
            prepare.setInt(1, bookId);
            prepare.executeUpdate();
            System.out.println("Book ID " + bookId + "Retrieved Successfully!");
            displayBooks();
            
        } catch (Exception e) {
        } finally {
            connect.close();
        }
    }
    
    //Hard Delete
    public void deleteBooks(int bookId) throws SQLException{
        String query = "DELETE tbl_libbook where book_id = ?";
        
        try {
            connect();
            prepare = connect.prepareStatement(query);
            prepare.setInt(1, bookId);
            prepare.executeUpdate();
            System.out.println("Book ID " + bookId + " is successfully deleted!");
            displayBooks();
            
        } catch (Exception e) {
        } finally{
            connect.close();
        }
    }
    
    // Search Book
    public void searchBooks(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Search book/s: ");
        String bookTitle = sc.nextLine();
        String query = "SELECT * FROM tbl_libbook where book_title like ?";
        
        try {
            connect();
            prepare = connect.prepareStatement(query);
            prepare.setString(1, "%" +  bookTitle + "%");
            
            result = prepare.executeQuery();
        } catch (Exception e) {
        }
    }
   
}