package com.aitu.library;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void addUser(LibraryUser user);
    List<LibraryUser> getAllUsers();
    Optional<LibraryUser> getUserById(int id); // Новый метод
    boolean deleteUser(int id); // Возвращаем boolean (успех/неудача)
    boolean updateUser(int id, String newName); // Возвращаем boolean
}