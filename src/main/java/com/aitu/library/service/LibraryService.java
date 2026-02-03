package com.aitu.library.service;

import com.aitu.library.exception.ResourceNotFoundException;
import com.aitu.library.model.EBook;
import com.aitu.library.model.LibraryUser;
import com.aitu.library.repository.BookRepository;
import com.aitu.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;

@Service
public class LibraryService {
    private final UserRepository userRepo;
    private final BookRepository bookRepo;

    @Autowired
    public LibraryService(UserRepository userRepo, BookRepository bookRepo) {
        this.userRepo = userRepo;
        this.bookRepo = bookRepo;
    }

    // --- USERS LOGIC ---

    public List<LibraryUser> getAllUsers() {
        return userRepo.getAllUsers();
    }

    public LibraryUser getUserById(int id) {
        return userRepo.getUserById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    public void registerStudent(String name, int year) {
        // Using Factory Pattern
        LibraryUser student = UserFactory.createUser("STUDENT", name, year, null);
        userRepo.addUser(student);
    }

    public void registerTeacher(String name, String department) {
        // Using Factory Pattern
        LibraryUser teacher = UserFactory.createUser("TEACHER", name, 0, department);
        userRepo.addUser(teacher);
    }

    public void removeUser(int id) {
        if (!userRepo.deleteUser(id)) {
            throw new ResourceNotFoundException("Cannot delete. User not found with id: " + id);
        }
    }

    public void updateUserName(int id, String newName) {
        if (!userRepo.updateUser(id, newName)) {
            throw new ResourceNotFoundException("Cannot update. User not found with id: " + id);
        }
    }

    // --- BOOKS LOGIC ---

    public List<EBook> getAllBooks() {
        return bookRepo.getAllBooks();
    }

    // In-memory processing (Sorting & Filtering) using Streams
    public List<EBook> getSortedBooks(String sortBy) {
        List<EBook> allBooks = bookRepo.getAllBooks();
        
        if ("title".equalsIgnoreCase(sortBy)) {
            return allBooks.stream()
                    .sorted(Comparator.comparing(EBook::getTitle))
                    .collect(Collectors.toList());
        } else if ("author".equalsIgnoreCase(sortBy)) {
            return allBooks.stream()
                    .sorted(Comparator.comparing(EBook::getAuthor))
                    .collect(Collectors.toList());
        }
        return allBooks;
    }

    public EBook getBookByIsbn(String isbn) {
        return bookRepo.getBookByIsbn(isbn)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with ISBN: " + isbn));
    }

    public List<EBook> searchBooks(String keyword) {
        return bookRepo.searchBooks(keyword);
    }

    public void addNewBook(String title, String isbn, String author) {
        // Using Builder Pattern
        EBook book = new EBook.Builder()
                .setTitle(title)
                .setIsbn(isbn)
                .setAuthor(author)
                .build();
        bookRepo.addBook(book);
    }

    public void removeBook(String isbn) {
        if (!bookRepo.deleteBook(isbn)) {
            throw new ResourceNotFoundException("Cannot delete. Book not found with ISBN: " + isbn);
        }
    }

    public void updateBookDetails(String isbn, String newTitle, String newAuthor) {
        if (!bookRepo.updateBook(isbn, newTitle, newAuthor)) {
            throw new ResourceNotFoundException("Cannot update. Book not found with ISBN: " + isbn);
        }
    }
}