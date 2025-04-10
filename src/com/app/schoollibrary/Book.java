
package com.app.schoollibrary;

import com.app.util.DbConnection;
import java.sql.SQLException;
import java.util.Scanner;


public class Book extends DbConnection {
    
    // Read Operation/API
    public void displayBooks() throws SQLException{
        String query = "SELECT * FROM tbl_libbook";
        
        try {
          connect();  
          //execute query
          state = connect.createStatement();
          result = state.executeQuery(query);
          
            System.out.println("ID\tTITLE\t\t\t\t\tAUTHOR\t\t\t\tYEAR PUBLISHED\t\tBOOK TYPE");
          while(result.next()){
              int id = result.getInt("book_id");
              String title = result.getString("book_title");
              String author = result.getString("book_author");
              int pubYear = result.getInt("book_year_published");
              String type = result.getString("book_type");
              
              System.out.println(id + "\t" + title + "\t\t\t" + author + "\t\t\t\t"
              + pubYear + "\t\t\t" + type);
          }
          
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally{
            connect.close();
        }
        
    }
    
    //create Operation
    public void addBooks() throws SQLException{
        Scanner sc = new Scanner(System.in);
        System.out.println("---ADD BOOKS---\nFill up the following informations.");
        System.out.print("Book title: ");
        String title = sc.nextLine();
        System.out.println("Book author: ");
        String author = sc.nextLine();
        System.out.println("Book year published: ");
        int pubYear = sc.nextInt();
        System.out.println("Book type: ");
        String type = sc.nextLine();
        
        String query = "INSERT INTO tbl_libbook(book_title, book_author, book_year_published, book_type)"
                + "VALUES(?, ?, ?, ?)";
        
        try {
            connect();
            // execute query
            prepare = connect.prepareStatement(query);
            // set parameter = wildcards;
            prepare.setString(1, title);
            prepare.setString(2, author);
            prepare.setInt(3, pubYear);
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
    public void updateBooks(String title, String author, int pubYear, String type, int bookId) throws SQLException{
        String query = "UPDATE tbl_libbook SET book_title = ?, book_author = ?, book_year_published = ?, book_type = ? where book_id = ?"; //ANTI SQL Injection/parameterized query
        
        try {
            connect();
            // execute query
            prepare = connect.prepareStatement(query);
            // set parameter = wildcards;
            prepare.setString(1, title);
            prepare.setString(2, author);
            prepare.setInt(3, pubYear);
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
            System.out.println(e);
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
            System.out.println(e);
        } finally {
            connect.close();
        }
    }
    
    //Hard Delete
    public void deleteBooks() throws SQLException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter book ID you want to delete: ");
        int bookId = sc.nextInt();
        String query = "DELETE tbl_libbook where book_id = ?";
        
        try {
            connect();
            prepare = connect.prepareStatement(query);
            prepare.setInt(1, bookId);
            prepare.executeUpdate();
            
            System.out.println("Book ID " + bookId + " is successfully deleted!");
            displayBooks();
            
        } catch (Exception e) {
            System.out.println(e);
        } finally{
            connect.close();
        }
    }
    
    // Search Book
    public void searchBooks() throws SQLException{
        Scanner sc = new Scanner(System.in);
        System.out.print("Search book/s title: ");
        String bookTitle = sc.nextLine();
        String query = "SELECT * FROM tbl_libbook where book_title like ?";
        
        try {
            connect();
            prepare = connect.prepareStatement(query);
            prepare.setString(1, "%" +  bookTitle + "%");
            
            result = prepare.executeQuery();
            System.out.println("ID\tTITLE\t\t\t\t\tAUTHOR\t\t\t\tYEAR PUBLISHED\t\tBOOK TYPE");
            while (result.next()){
                
                int id = result.getInt("book_id");
                String title = result.getString("book_title");
                String author = result.getString("book_author");
                int pubYear = result.getInt("book_year_published");
                String type = result.getString("book_type");
              
              System.out.println(id + "\t" + title + "\t\t\t" + author + "\t\t\t\t"
              + pubYear + "\t\t\t" + type);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally{
            connect.close();
        }
    }
    
    // Dashboard
    public void bookDashBoard() {
        Scanner sc = new Scanner (System.in);
        boolean running = true;
        
        while (running){
            System.out.println("----Manage Books----");
            System.out.println("\t\t[1] View Books");
            System.out.println("\t\t[2] Search Books");    
            System.out.println("\t\t[3] Add Books"); 
            System.out.println("\t\t[4] Update Books"); 
            System.out.println("\t\t[5] Archive Books"); 
            System.out.println("\t\t[6] Restore Books"); 
            System.out.println("\t\t[7] Delete Books"); 
            System.out.println("Enter a choice: ");
            
            try {
                int dbChoice = sc.nextInt();
                switch(dbChoice){
                    case 1:
                        displayBooks();
                        break;
                    case 2:
                        searchBooks();
                        break;
                    case 3:
                        addBooks();
                        break;
                    case 4: 
//                      updateBooks();
                        break;
                    case 5: 
//                        archiveBooks();
                        break;
                    case 6:
//                        restoreBooks();
                        break;
                    case 7: 
                        deleteBooks();
                        break;
                }
            } catch (Exception e) {
                System.out.println(e);
                bookDashBoard();
            }
        }
    }
   
}