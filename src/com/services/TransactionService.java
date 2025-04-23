
package com.services;

import com.app.util.DbConnection;
import com.dao.TransactionDAO;
import com.model.TransactionModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        } catch (Exception e) {
            e.printStackTrace();
        }return false;
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
        } catch (Exception e) {
            e.printStackTrace();
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
        } catch (Exception e) {
            e.printStackTrace();
        }

        return transaction;
    }

    @Override
    public boolean updateTransaction(int id) {
        String query = "UPDATE tbl_booktransaction SET return_date = CURRENT_DATE WHERE transaction_id = ?";
        try {
            connect();
            TransactionModel transaction = getTransactionById(id);
            prepare = connect.prepareStatement(query);
            prepare.setInt(1, id);
            int rowsAffected = prepare.executeUpdate();
            bookService.updateBookStatus(transaction.getBookId(), "Available");

            
            return rowsAffected > 0;

        } catch (Exception e) {
            e.printStackTrace();
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

                // Now mark the book as available
                bookService.updateBookStatus(item.getBookId(), "Available");
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
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
        e.printStackTrace();
        return false;
    }
    }
    
}
