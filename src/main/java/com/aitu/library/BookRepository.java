package com.aitu.library;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    void addBook(EBook book);
    List<EBook> getAllBooks();
    Optional<EBook> getBookByIsbn(String isbn); // Новый метод
    boolean deleteBook(String isbn); // Возвращаем boolean
    boolean updateBook(String isbn, String newTitle, String newAuthor); // Возвращаем boolean
}