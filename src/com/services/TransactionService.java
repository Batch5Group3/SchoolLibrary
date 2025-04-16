
package com.services;

import com.app.util.DbConnection;
import com.model.Transaction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TransactionService implements CrudService<Transaction> {
    private final DbConnection dbConnection = new DbConnection();
    
    @Override
    public void add(Transaction item) {
        String sql = "INSERT INTO tbl_booktransaction (user_id, book_id, borrow_date, return_date, fine_amount) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = dbConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, item.getUserId());
            stmt.setInt(2, item.getBookId());
            stmt.setDate(3, item.getBorrowDate());
            stmt.setDate(4, item.getReturnDate());
            stmt.setDouble(5, item.getFineAmount());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Transaction> getAll() {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM tbl_booktransaction";

        try (Connection conn = dbConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Transaction transaction = new Transaction();
                transaction.setTransactionId(rs.getInt("transaction_id"));
                transaction.setUserId(rs.getInt("user_id"));
                transaction.setBookId(rs.getInt("book_id"));
                transaction.setBorrowDate(rs.getDate("borrow_date"));
                transaction.setReturnDate(rs.getDate("return_date"));
                transaction.setFineAmount(rs.getDouble("fine_amount"));
                transactions.add(transaction);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return transactions;
    }

    @Override
    public Transaction getById(int id) {
        Transaction transaction = null;
        String sql = "SELECT * FROM tbl_booktransaction WHERE transaction_id = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                transaction = new Transaction();
                transaction.setTransactionId(rs.getInt("transaction_id"));
                transaction.setUserId(rs.getInt("user_id"));
                transaction.setBookId(rs.getInt("book_id"));
                transaction.setBorrowDate(rs.getDate("borrow_date"));
                transaction.setReturnDate(rs.getDate("return_date"));
                transaction.setFineAmount(rs.getDouble("fine_amount"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return transaction;
    }

    @Override
    public void updateItem(Transaction item) {
        String sql = "UPDATE tbl_booktransaction SET user_id = ?, book_id = ?, borrow_date = ?, return_date = ?, fine_amount = ? WHERE transaction_id = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, item.getUserId());
            stmt.setInt(2, item.getBookId());
            stmt.setDate(3, item.getBorrowDate());
            stmt.setDate(4, item.getReturnDate());
            stmt.setDouble(5, item.getFineAmount());
            stmt.setInt(6, item.getTransactionId());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteItem(int id) {
        String sql = "DELETE FROM tbl_booktransaction WHERE transaction_id = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
