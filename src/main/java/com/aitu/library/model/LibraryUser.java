package com.aitu.library.model;

import java.util.ArrayList;
import java.util.List;

public abstract class LibraryUser {
    //Attributes
    private int userId;
    private String username;
    //arrays
    private List<EBook> borrowedBooks;

    //Init or Construct
    public LibraryUser(int userId, String username){
        this.userId = userId;
        this.username = username;
        this.borrowedBooks = new ArrayList<EBook>();
    }

    // Abstract method - must be implemented by subclasses
    public abstract int getBorrowLimit();

    //Override Object method
    @Override
    public String toString() {
        return "User ID: " + this.getUserId() + ", Name: " + this.getName() + " (" + this.getClass().getSimpleName() + ")";
    }

    //equals by class and id
    @Override
    public boolean equals(Object object){
        if(this == object) return true;
        if(object == null || this.getClass() != object.getClass()) return false;
        LibraryUser other = (LibraryUser) object;
        return this.getUserId() == other.getUserId();
    }

    @Override
    public int hashCode(){
        return Integer.hashCode(getUserId());
    }

    //Getter
    public int getUserId(){
        return userId;
    }
    public String getName(){
        return username;
    }

    //Setter
    public void setUserId(int userId){
        this.userId = userId;
    }
    public void setUsername(String username){
        this.username = username;
    }
}