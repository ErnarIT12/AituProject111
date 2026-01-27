package com.aitu.library;

import java.util.List;

public interface UserRepository {
    void addUser(LibraryUser user);
    List<LibraryUser> getAllUsers();
    void deleteUser(int id);
    void updateUser(int id, String newName);
}