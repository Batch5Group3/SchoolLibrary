
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

    public boolean returnTransaction(int id) {
        return transactionService.returnBookTransaction(id);
    }
    
    public boolean userBorrowTransaction(TransactionModel transaction){
        return transactionService.borrowBookTransaction(transaction);
    }

    public void deleteTransaction(int id) {
        transactionService.deleteItem(id);
    }
}
