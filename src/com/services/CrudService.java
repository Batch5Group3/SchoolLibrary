
package com.services;

import java.util.List;

// <T> indicates this is a generic type
// it is intended to be implemented in different controller actions
// for the common crud functions
public interface CrudService<T> {
    void add(T item);
    List<T> getAll();
    T getById(int id);
    void deleteItem(int id);
}
