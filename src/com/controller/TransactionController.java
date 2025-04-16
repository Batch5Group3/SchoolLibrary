
package com.controller;

import com.model.Transaction;
import com.services.CrudService;
import com.services.TransactionService;
import java.util.List;

public class TransactionController {
    private final CrudService<Transaction> transactionService;

    public TransactionController() {
        this.transactionService = new TransactionService();
    }

    public void addTransaction(Transaction transaction) {
        transactionService.add(transaction);
    }

    public List<Transaction> showAllTransactions() {
        return transactionService.getAll();
    }

    public Transaction findTransactionById(int id) {
        return transactionService.getById(id);
    }

    public void updateTransaction(Transaction transaction) {
        transactionService.updateItem(transaction);
    }

    public void deleteTransaction(int id) {
        transactionService.deleteItem(id);
    }
}
