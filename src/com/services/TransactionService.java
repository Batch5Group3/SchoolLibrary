
package com.services;

import com.app.util.DbConnection;
import com.dao.TransactionDAO;
import com.model.TransactionModel;
import java.util.ArrayList;
import java.util.List;

public class TransactionService extends DbConnection implements TransactionDAO<TransactionModel> {
    private final BookService bookService = new BookService();  
    @Override
    public boolean add(TransactionModel item) {
        String query = "INSERT INTO tbl_booktransaction (user_id, book_id, borrow_date, return_date, fine_amount) VALUES (?, ?, ?, ?, ?)";

        try {
            connect();
        
            prepare = connect.prepareStatement(query);

            prepare.setInt(1, item.getUserId());
            prepare.setInt(2, item.getBookId());
            prepare.setDate(3, item.getBorrowDate());
            prepare.setDate(4, item.getReturnDate());
            prepare.setDouble(5, item.getFineAmount());
            prepare.executeUpdate();
            
             bookService.updateBookStatus(item.getBookId(), "Borrowed");
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
    public void updateItem(TransactionModel item) {
        String query = "UPDATE tbl_booktransaction SET user_id = ?, book_id = ?, borrow_date = ?, return_date = ?, fine_amount = ? WHERE transaction_id = ?";

        try {
            connect();
            prepare = connect.prepareStatement(query);

            prepare.setInt(1, item.getUserId());
            prepare.setInt(2, item.getBookId());
            prepare.setDate(3, item.getBorrowDate());
            prepare.setDate(4, item.getReturnDate());
            prepare.setDouble(5, item.getFineAmount());
            prepare.setInt(6, item.getId());

            prepare.executeUpdate();
            bookService.updateBookStatus(item.getBookId(), "Borrowed");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean deleteItem(int id) {
        String query = "DELETE FROM tbl_booktransaction WHERE transaction_id = ?";

        try {
            connect();
            prepare = connect.prepareStatement(query);

            prepare.setInt(1, id);
            prepare.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
