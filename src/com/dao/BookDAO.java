
package com.dao;

import java.util.List;


public interface BookDAO<T> {
    
    public boolean add(T item); // Create
    public List<T> getAll(); // Read
    public List<T> getAvailableBooks();
    public List<T> getBorrowedBooks();
    public T getById(int id); // Read
    public void updateItem(T id); // Update
    public void updateBookStatus(int id, String newStatus);
    public boolean deleteItem(int id); // Delete 
}
