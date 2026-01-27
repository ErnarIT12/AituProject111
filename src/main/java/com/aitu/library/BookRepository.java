package com.aitu.library;

import java.util.List;

public interface BookRepository {
    void addBook(EBook book);
    List<EBook> getAllBooks();
    void deleteBook(String isbn);
    void updateBook(String isbn, String newTitle, String newAuthor);
}