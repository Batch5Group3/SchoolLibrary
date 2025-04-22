
package com.model;


public class BookModel {
    private int id;
    private String title;
    private String author;
    private int pubYear;
    private String type;

    public BookModel() {
    }
    
    public BookModel(int bkId, String title, String author, int pubYear, String type) {
        this.id = bkId;
        this.title = title;
        this.author = author;
        this.pubYear = pubYear;
        this.type = type;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPubYear() {
        return pubYear;
    }

    public void setPubYear(int pubYear) {
        this.pubYear = pubYear;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
}
