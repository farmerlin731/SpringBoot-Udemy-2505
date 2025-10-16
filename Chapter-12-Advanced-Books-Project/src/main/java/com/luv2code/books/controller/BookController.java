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
                new Book(1, "The Silent Observer", "Alice Johnson", "Mystery", 7),
                new Book(2, "Journey to the Stars", "David Kim", "Technology", 10),
                new Book(3, "The Art of Simplicity", "Marie Chen", "Cooking", 1),
                new Book(4, "Hidden Gardens", "Liam Wong", "Lifestyle", 7),
                new Book(5, "Culinary Adventures", "Sophia Garcia", "Cooking", 2),
                new Book(6, "The Code of Tomorrow", "Ethan Brown", "Technology", 5)
        ));
    }


    @GetMapping
    public List<Book> getBooks(@RequestParam(required = false) String category) {
        if (category == null) return books;

        return books.stream()
                .filter(book -> book.getCategory().equalsIgnoreCase(category))
                .toList();
    }


    @GetMapping("/{id}")
    public Book getBookById(@PathVariable int id) {
        Book result = books.stream()
                .filter(book -> book.getId() == id)
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

    @PutMapping("/{id}")
    public void updateBook(@PathVariable int id, @RequestBody Book updatedBook) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == id) {
                books.set(i, updatedBook);
                return;
            }
        }
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable int id) {
        books.removeIf(book -> book.getId() == id);
        return "Delete Finished!";
    }
}
