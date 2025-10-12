package com.luv2code.books.controller;

import com.luv2code.books.entity.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
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
                new Book("Journey to the Stars", "David Kim", "Technology"),
                new Book("The Art of Simplicity", "Marie Chen", "Cooking"),
                new Book("Hidden Gardens", "Liam Wong", "Lifestyle"),
                new Book("Culinary Adventures", "Sophia Garcia", "Cooking"),
                new Book("The Code of Tomorrow", "Ethan Brown", "Technology")
        ));
    }


    @GetMapping("/api/books")
    public List<Book> getBooks(@RequestParam(required = false) String category) {
        if (category == null) return books;

        return books.stream()
                .filter(book -> book.getCategory().equalsIgnoreCase(category))
                .toList();
    }


    @GetMapping("/api/books/{title}")
    public Book getBookByTitle(@PathVariable String title) {
        Book result = books.stream()
                .filter(book -> book.getName().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
        return result;
    }
}
