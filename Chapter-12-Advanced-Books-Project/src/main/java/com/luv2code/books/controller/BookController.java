package com.luv2code.books.controller;

import com.luv2code.books.entity.Book;
import com.luv2code.books.exception.BookNotFoundException;
import com.luv2code.books.request.BookRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//for swagger
@Tag(name = "Books Rest Api Endpoints", description = "operations related to books")
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

    //for swagger
    @Operation(summary = "Get All Books", description = "get a list from server")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Book> getBooks(@RequestParam(required = false) String category) {
        if (category == null) return books;

        return books.stream()
                .filter(book -> book.getCategory().equalsIgnoreCase(category))
                .toList();
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable @Min(value = 1) int id) {
        Book result = books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                //.orElse(null);
                .orElseThrow(() -> new BookNotFoundException("Book not found - " + id));
        return result;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createBook(@Valid @RequestBody BookRequest theBookRequest) {
        //determine the id
        int id = books.isEmpty() ? 1 : books.getLast().getId() + 1;

        books.add(converToBook(id, theBookRequest));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable @Min(value = 1) int id, @Valid @RequestBody BookRequest theBookRequest) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == id) {
                Book updatedBook = converToBook(id, theBookRequest);
                books.set(i, updatedBook);
                return updatedBook;
            }
        }

        throw new BookNotFoundException("Book not found - " + id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable @Min(value = 1) int id) {

        // BOOK NOT FOUND EXCEPTION
        books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException("Book not found - " + id));

        books.removeIf(book -> book.getId() == id);
        return "Delete Finished!";
    }

    private Book converToBook(int id, BookRequest theBookRequest) {
        return new Book(id, theBookRequest.getName(), theBookRequest.getAuthor(), theBookRequest.getCategory(), theBookRequest.getRating());
    }


}
