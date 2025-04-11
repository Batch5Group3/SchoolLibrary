
package com.model;
// This class holds the data
public class Book {
    // These fields represents the table columns
    private int bookId;
    private String bookTitle;
    private String bookAuthor;
    private java.time.LocalDate bookPublicationDate;
    private String bookType;

    public Book() {}

    public Book(int bookId, String bookTitle, String bookAuthor, java.time.LocalDate bookPublicationDate, String bookType) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookPublicationDate = bookPublicationDate;
        this.bookType = bookType;
    }

    // Getters and Setters
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public java.time.LocalDate getBookPublicationDate() {
        return bookPublicationDate;
    }

    public void setBookPublicationDate(java.time.LocalDate bookPublicationDate) {
        this.bookPublicationDate = bookPublicationDate;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    @Override
    public String toString() {
        return "Book{" +
               "bookId=" + bookId +
               ", bookTitle='" + bookTitle + '\'' +
               ", bookAuthor='" + bookAuthor + '\'' +
               ", bookPublicationDate=" + bookPublicationDate +
               ", bookType='" + bookType + '\'' +
               '}';
    }
}
