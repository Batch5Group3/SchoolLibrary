
package com.management.view;

import com.management.model.Book;
import java.util.List;

public class BookView {
   public void displayBook(Book book) {
        System.out.println("Book Details:");
        System.out.println("-------------");
        System.out.println("ID: " + book.getBookId());
        System.out.println("Title: " + book.getBookTitle());
        System.out.println("Author: " + book.getBookAuthor());
        System.out.println("Publication Date: " + book.getBookPublicationDate());
        System.out.println("Type: " + book.getBookType());
        System.out.println();
    }

    public void displayBookList(List<Book> books) {
        System.out.println("Book List:");
        System.out.println("==========");
        for (Book book : books) {
            displayBook(book);
        }
    }
}
