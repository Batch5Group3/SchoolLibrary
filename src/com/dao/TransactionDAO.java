
package com.dao;

import java.util.List;

// <T> indicates this is a generic type
// it is intended to be implemented in different services then invoked in the controller
// common crud functions, update method still missing
public interface TransactionDAO<T> {
        boolean add(T item); // Create
        List<T> getAll(); // Read
        T getById(int id); // Read
        T getTransactionById(int id); //Read
        boolean returnBookTransaction(int id); // Update
        boolean borrowBookTransaction(T item);
        boolean deleteItem(int id); // Delete
    
}
