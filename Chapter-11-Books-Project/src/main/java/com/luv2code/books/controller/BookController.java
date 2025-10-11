package com.luv2code.books.controller;

import com.luv2code.books.entity.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {

    private List<Book> books = new ArrayList<>();

    public BookController() {
        initBooks();
    }

    private void initBooks() {
        books.addAll(List.of(
                new Book("The Silent Observer", "Alice Johnson", "Mystery"),
                new Book("Journey to the Stars", "David Kim", "Science Fiction"),
                new Book("The Art of Simplicity", "Marie Chen", "Philosophy"),
                new Book("Hidden Gardens", "Liam Wong", "Lifestyle"),
                new Book("Culinary Adventures", "Sophia Garcia", "Cooking"),
                new Book("The Code of Tomorrow", "Ethan Brown", "Technology")
        ));
    }


    @GetMapping("/api/books")
    public List<Book> getBooks() {
        return books;
    }
}
