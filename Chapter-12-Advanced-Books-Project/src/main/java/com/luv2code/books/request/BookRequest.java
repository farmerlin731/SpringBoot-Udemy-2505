package com.luv2code.books.request;

public class BookRequest {

    private String name;
    private String author;
    private String category;
    private int rating;

    public BookRequest(String name, String author, String category, int rating) {
        this.name = name;
        this.author = author;
        this.category = category;
        this.rating = rating;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
