
package com.dao;

import java.sql.Date;
import java.util.List;


public interface TransactionDAO<T> {
    public boolean add(T item); // Create
    public List<T> getAll(); // Read
    public T getById(int id); // Read
    public T getTransactionById(int id); //Read
    public boolean returnBookTransaction(int id, double fineAmount); // Update
    public  boolean borrowBookTransaction(T item);
    public double computeFineAmount(Date borrowDate, Date returnDate);
    public boolean deleteItem(int id); // Delete
    
}
