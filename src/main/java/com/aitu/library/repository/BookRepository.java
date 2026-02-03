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
    List<EBook> searchBooks(String keyword); // метод поиска
}