package com.aitu.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

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
        Student student = new Student(0, name, year);
        userRepo.addUser(student);
    }

    public void registerTeacher(String name, String department) {
        Teacher teacher = new Teacher(0, name, department);
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

    public EBook getBookByIsbn(String isbn) {
        return bookRepo.getBookByIsbn(isbn)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with ISBN: " + isbn));
    }

    public void addNewBook(String title, String isbn, String author) {
        bookRepo.addBook(new EBook(title, isbn, author));
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