
package com.controller;

import com.dao.TransactionDAO;
import com.model.TransactionModel;
import com.services.TransactionService;
import java.util.List;

public class TransactionController {
    private final TransactionDAO<TransactionModel> transactionService;

    public TransactionController() {
        this.transactionService = new TransactionService();
    }

    public void addTransaction(TransactionModel transaction) {
        transactionService.add(transaction);
    }

    public List<TransactionModel> showAllTransactions() {
        return transactionService.getAll();
    }

    public TransactionModel findTransactionById(int id) {
        return transactionService.getById(id);
    }

    public boolean returnTransaction(int id, double fineAmount) {
        return transactionService.returnBookTransaction(id, fineAmount);
    }
    
    public boolean userBorrowTransaction(TransactionModel transaction){
        return transactionService.borrowBookTransaction(transaction);
    }
    
    public double previewFineAmount(int transactionId) {
        TransactionModel transaction = transactionService.getTransactionById(transactionId);
        if (transaction != null) {
            java.sql.Date today = new java.sql.Date(System.currentTimeMillis());
            return transactionService.computeFineAmount(transaction.getBorrowDate(), today);
        }
        return 0.0;
    }

    public void deleteTransaction(int id) {
        transactionService.deleteItem(id);
    }
}
