
package com.app;

import com.controller.BookController;
import com.view.BookView;

public class Main {
    public static void main(String[] args) {
        //System.out.println("Initial commit");
        //System.out.println("Initial commit from branch off...");
        // I think in this part... it's the dependency injection mechanism???
        BookController bookController = new BookController();
        BookView bookView = new BookView(bookController);
        bookView.showMainMenu();
    }
}
