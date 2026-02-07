package com.aitu.library.controller;

import com.aitu.library.model.EBook;
import com.aitu.library.service.LibraryService;
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
    public List<EBook> getAllBooks(@RequestParam(required = false) String sortBy) {
        if (sortBy != null) {
            return libraryService.getSortedBooks(sortBy);
        }
        return libraryService.getAllBooks();
    }

    @GetMapping("/search")
    public List<EBook> searchBooks(@RequestParam String keyword) {
        return libraryService.searchBooks(keyword);
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

    // --- НОВЫЕ ЭНДПОИНТЫ ДЛЯ ВЫДАЧИ ---

    @PostMapping("/{isbn}/borrow")
    public String borrowBook(@PathVariable String isbn, @RequestParam int userId) {
        try {
            libraryService.borrowBook(userId, isbn);
            return "Book borrowed successfully";
        } catch (IllegalStateException e) {
            return "Error: " + e.getMessage();
        }
    }

    @PostMapping("/{isbn}/return")
    public String returnBook(@PathVariable String isbn, @RequestParam int userId) {
        libraryService.returnBook(userId, isbn);
        return "Book returned successfully";
    }
}