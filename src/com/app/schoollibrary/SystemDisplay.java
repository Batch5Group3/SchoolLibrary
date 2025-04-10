
package com.app.schoollibrary;

import java.sql.SQLException;


public class SystemDisplay {
    public static void main(String[] args) throws SQLException {
        Book f = new Book();
//        f.displayBooks();
//        f.addBooks("The Night Circus", "Erin Morgenstern", 2011, "Fantasy/Romance");
//        f.updateBooks("The Night Circus", "Erin Morgenstern", 2011, "Fantasy/Romance", 8);
          f.searchBooks();
        
    }
    
}
