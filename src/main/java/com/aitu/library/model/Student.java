package com.aitu.library.model;

public class Student extends LibraryUser {
    private int yearOfStudy;
    // Пустой конструктор нужен для JSON десериализации (Jackson)
    public Student() {
        super(0, "");
    }
    public Student(int userId, String username, int yearOfStudy) {
        super(userId, username);
        this.yearOfStudy = yearOfStudy;
    }

    public int getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(int yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    @Override
    public int getBorrowLimit() {
        return 5;
    }

    @Override
    public String toString() {
        return super.toString() + " - " + yearOfStudy + " course";
    }
}