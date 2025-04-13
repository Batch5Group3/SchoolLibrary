
package com.app.controller;


import java.util.List;


public interface CrudService<S> {
    
    public void add(S item);
    public List<S> getAll();
    public S getByFirstName(String item);
    public void update(int id, String field, String newInfo);
    public void delete(int id);
    
}
