
package com.app.controller;

import java.util.List;


public interface CrudService<S> {
    
    public void add(S item);
    public List<S> getAll();
    public S getById();
    public void update(int id, S updatedItem);
    public void delete(int id);
    
}
