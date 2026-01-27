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

    public void registerStudent(String name, int year) {
        Student student = new Student(0, name, year);
        userRepo.addUser(student);
    }

    public void registerTeacher(String name, String department) {
        Teacher teacher = new Teacher(0, name, department);
        userRepo.addUser(teacher);
    }

    public void removeUser(int id) {
        userRepo.deleteUser(id);
    }

    public void updateUserName(int id, String newName) {
        userRepo.updateUser(id, newName);
    }

    // --- BOOKS LOGIC ---

    public List<EBook> getAllBooks() {
        return bookRepo.getAllBooks();
    }

    public void addNewBook(String title, String isbn, String author) {
        EBook book = new EBook(title, isbn, author);
        bookRepo.addBook(book);
    }

    public void removeBook(String isbn) {
        bookRepo.deleteBook(isbn);
    }

    public void updateBookDetails(String isbn, String newTitle, String newAuthor) {
        bookRepo.updateBook(isbn, newTitle, newAuthor);
    }
}