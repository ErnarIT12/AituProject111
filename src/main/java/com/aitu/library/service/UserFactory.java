package com.aitu.library.service;

import com.aitu.library.model.LibraryUser;
import com.aitu.library.model.Student;
import com.aitu.library.model.Teacher;

public class UserFactory {

    public static LibraryUser createUser(String type, String name, int yearOrNull, String deptOrNull) {
        if ("STUDENT".equalsIgnoreCase(type)) {
            return new Student(0, name, yearOrNull);
        } else if ("TEACHER".equalsIgnoreCase(type)) {
            return new Teacher(0, name, deptOrNull);
        } else {
            throw new IllegalArgumentException("Unknown user type: " + type);
        }
    }
}