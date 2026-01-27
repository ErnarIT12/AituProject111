package com.aitu.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final LibraryService libraryService;

    @Autowired
    public BookController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping
    public List<EBook> getAllBooks() {
        return libraryService.getAllBooks();
    }

    @PostMapping
    public String addBook(@RequestBody EBook book) {
        libraryService.addNewBook(book.getTitle(), book.getIsbn(), book.getAuthor());
        return "Book added successfully";
    }

    @DeleteMapping("/{isbn}")
    public String deleteBook(@PathVariable String isbn) {
        libraryService.removeBook(isbn);
        return "Book deleted successfully";
    }

    @PutMapping("/{isbn}")
    public String updateBook(@PathVariable String isbn, @RequestParam String title, @RequestParam String author) {
        libraryService.updateBookDetails(isbn, title, author);
        return "Book updated successfully";
    }
}