package com.aitu.library.repository;

import com.aitu.library.model.EBook;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    void addBook(EBook book);
    List<EBook> getAllBooks();
    Optional<EBook> getBookByIsbn(String isbn);
    boolean deleteBook(String isbn);
    boolean updateBook(String isbn, String newTitle, String newAuthor);
    List<EBook> searchBooks(String keyword);

    // --- НОВЫЕ МЕТОДЫ ДЛЯ ВЫДАЧИ ---
    boolean isBookAvailable(String isbn);
    void setBookAvailability(String isbn, boolean available);
    int countBooksBorrowedByUser(int userId);
    void recordBorrow(int userId, String isbn);
    void recordReturn(int userId, String isbn);
}