package com.aitu.library.controller;

import com.aitu.library.model.LibraryUser;
import com.aitu.library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final LibraryService libraryService;

    @Autowired
    public UserController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping
    public List<LibraryUser> getAllUsers() {
        return libraryService.getAllUsers();
    }

    @PostMapping("/student")
    public String addStudent(@RequestParam String name, @RequestParam int year) {
        libraryService.registerStudent(name, year);
        return "Student added successfully";
    }

    @PostMapping("/teacher")
    public String addTeacher(@RequestParam String name, @RequestParam String department) {
        libraryService.registerTeacher(name, department);
        return "Teacher added successfully";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {
        libraryService.removeUser(id);
        return "User deleted successfully";
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable int id, @RequestParam String newName) {
        libraryService.updateUserName(id, newName);
        return "User updated successfully";
    }
}