
package com.services;

import java.util.List;

// <T> indicates this is a generic type
// it is intended to be implemented in different services then invoked in the controller
// common crud functions, update method still missing
public interface CrudService<T> {
    void add(T item); // Create
    List<T> getAll(); // Read
    T getById(int id); // Read
    // void updateItem(int id); // Update
    void deleteItem(int id); // Delete
}
