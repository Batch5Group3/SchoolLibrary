
package com.services;

import com.app.util.DbConnection;
import com.dao.TransactionDAO;
import com.model.TransactionModel;
import java.sql.Date;
import java.sql.SQLException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransactionService extends DbConnection implements TransactionDAO<TransactionModel> {
    private final BookService bookService = new BookService();  
    
    @Override
    public boolean add(TransactionModel item) {
        String query = "INSERT INTO tbl_booktransaction (user_id, book_id, borrow_date, fine_amount) VALUES (?, ?, ?, ?)";

        try {
            connect();
            prepare = connect.prepareStatement(query);
            prepare.setInt(1, item.getUserId());
            prepare.setInt(2, item.getBookId());
            prepare.setDate(3, item.getBorrowDate());
            prepare.setDouble(4, item.getFineAmount());
            int rowsInserted = prepare.executeUpdate();
            if (rowsInserted > 0) {
                bookService.updateBookStatus(item.getBookId(), "Borrowed");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("TransactionService: add() " + e.getMessage());
        } finally {
            try {
                connect.close();
            } catch (SQLException ex) {
                Logger.getLogger(TransactionService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    @Override
    public List<TransactionModel> getAll() {
        List<TransactionModel> transactions = new ArrayList<>();
        String query = "SELECT * FROM tbl_booktransaction";
        try {
            connect();
            state = connect.createStatement();
            result = state.executeQuery(query);
            while (result.next()) {
                TransactionModel transaction = new TransactionModel(
                        result.getInt("transaction_id"),
                        result.getInt("user_id"),
                        result.getInt("book_id"),
                        result.getDate("borrow_date"),
                        result.getDate("return_date"),
                        result.getInt("fine_amount"));
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            System.out.println("TransactionService: getAll() " + e.getMessage());
        } finally {
            try {
                connect.close();
            } catch (SQLException ex) {
                Logger.getLogger(TransactionService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return transactions;
    }

    @Override
    public TransactionModel getById(int id) {
        TransactionModel transaction = null;
        String query = "SELECT * FROM tbl_booktransaction WHERE transaction_id = ?";

        try {
            connect();
            prepare = connect.prepareStatement(query);
            prepare.setInt(1, id);
            result = prepare.executeQuery();
            if (result.next()) {
                transaction = new TransactionModel(
                        result.getInt("transaction_id"),
                        result.getInt("user_id"),
                        result.getInt("book_id"),
                        result.getDate("borrow_date"),
                        result.getDate("return_date"),
                        result.getInt("fine_amount"));
            }
        } catch (SQLException e) {
            System.out.println("TransactionService: getById() " + e.getMessage());
        } finally {
            try {
                connect.close();
            } catch (SQLException ex) {
                Logger.getLogger(TransactionService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return transaction;
    }

    @Override
    public boolean returnBookTransaction(int id, double fineAmount) {
        String query = "UPDATE tbl_booktransaction SET return_date = CURRENT_DATE, fine_amount = ? WHERE transaction_id = ?";

        try {
            connect();
            TransactionModel transaction = getTransactionById(id);
            if (transaction == null) {
                return false; }
            if (transaction.getReturnDate() != null) {
                System.out.println("\t\t\t\tThis book was already returned on " + transaction.getReturnDate());
                return false; }
            prepare = connect.prepareStatement(query);
            prepare.setDouble(1, fineAmount);
            prepare.setInt(2, id);
            int rowsAffected = prepare.executeUpdate();
            if (rowsAffected > 0) {
                bookService.updateBookStatus(transaction.getBookId(), "Available");
                return true; }
        } catch (SQLException e) {
            System.out.println("TransactionService: returnBookTransaction() " + e.getMessage());
        } finally {
            try {
                connect.close();
            } catch (SQLException ex) {
                Logger.getLogger(TransactionService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    @Override
    public boolean deleteItem(int id) {
        String query = "DELETE FROM tbl_booktransaction WHERE transaction_id = ?";

        try {
            connect();
            TransactionModel item = getTransactionById(id);
            if (item != null) {
                prepare = connect.prepareStatement(query);
                prepare.setInt(1, id);
                prepare.executeUpdate();
                bookService.updateBookStatus(item.getBookId(), "Available");
                return true; }
        } catch (SQLException e) {
            System.out.println("TransactionService: deleteItem() " + e.getMessage());
        } finally {
            try {
                connect.close();
            } catch (SQLException ex) {
                Logger.getLogger(TransactionService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    @Override
    public TransactionModel getTransactionById(int id) {
        for (TransactionModel transaction : getAll()) {
            if (transaction.getId()== id) {
                return transaction;
            }
        }
        return null;
    }

    @Override
    public boolean borrowBookTransaction(TransactionModel transaction) {
        String query = "INSERT INTO tbl_booktransaction (user_id, book_id, borrow_date, fine_amount) VALUES (?, ?, ?, ?)";
        
        try {
            connect();
            prepare = connect.prepareStatement(query);
            prepare.setInt(1, transaction.getUserId());
            prepare.setInt(2, transaction.getBookId());
            prepare.setDate(3, transaction.getBorrowDate());
            prepare.setDouble(4, transaction.getFineAmount());
            prepare.executeUpdate();
            bookService.updateBookStatus(transaction.getBookId(), "Borrowed");
            return true;
            
        } catch (SQLException e) {
            System.out.println("TransactionService: borrowBookTransaction() " + e.getMessage());
            return false;
        } finally {
            try {
                connect.close();
            } catch (SQLException ex) {
                Logger.getLogger(TransactionService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public double computeFineAmount(Date borrowDate, Date returnDate) {
        double fineAmount = 0.0;
        if (returnDate != null && returnDate.after(borrowDate)) {
            long totalDays = ChronoUnit.DAYS.between(
                    borrowDate.toLocalDate(),
                    returnDate.toLocalDate());
            long overdueDays = totalDays - 5;
            
            if (overdueDays > 0) {
                fineAmount = overdueDays * 5.0;
            }
        }
        return fineAmount;
    }
}
