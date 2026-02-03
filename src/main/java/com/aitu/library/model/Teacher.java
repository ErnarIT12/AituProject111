package com.aitu.library.model;

public class Teacher extends LibraryUser {
    private String department;

    // Пустой конструктор нужен для JSON десериализации (Jackson)
    public Teacher() {
        super(0, "");
    }

    public Teacher(int userId, String username, String department) {
        super(userId, username);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public int getBorrowLimit() {
        return 10;
    }

    @Override
    public String toString() {
        return super.toString() + " - Dep" + department;
    }
}