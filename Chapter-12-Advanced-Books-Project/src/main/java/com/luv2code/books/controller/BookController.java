package com.luv2code.books.controller;

import com.luv2code.books.entity.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/books")
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


    @GetMapping
    public List<Book> getBooks(@RequestParam(required = false) String category) {
        if (category == null) return books;

        return books.stream()
                .filter(book -> book.getCategory().equalsIgnoreCase(category))
                .toList();
    }


    @GetMapping("/{title}")
    public Book getBookByTitle(@PathVariable String title) {
        Book result = books.stream()
                .filter(book -> book.getName().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
        return result;
    }

    @PostMapping
    public void createBook(@RequestBody Book newBook) {
        boolean isNew = books.stream()
                .noneMatch(book -> book.getName().equalsIgnoreCase(newBook.getName()));

        if (isNew) books.add(newBook);
    }

    @PutMapping("/{title}")
    public void updateBook(@PathVariable String title, @RequestBody Book updatedBook) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getName().equalsIgnoreCase(title)) {
                books.set(i, updatedBook);
                return;
            }
        }
    }

    @DeleteMapping("/{title}")
    public String deleteBook(@PathVariable String title) {
        books.removeIf(book -> book.getName().equalsIgnoreCase(title));
        return "Delete Finished!";
    }
}
