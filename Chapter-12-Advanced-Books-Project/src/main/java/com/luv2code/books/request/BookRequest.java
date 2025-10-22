package com.luv2code.books.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class BookRequest {

    @Size(min = 1, max = 30, message = "Title must be between 1 & 30.")
    private String name;
    private String author;
    private String category;

    @Min(value = 1, message = "Should be more than 1.")
    @Max(value = 10, message = "Should be less than 10.")
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
