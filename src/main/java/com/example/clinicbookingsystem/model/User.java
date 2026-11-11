package com.example.clinicbookingsystem.model;

// this is the Shared account and profile data - patient and receptionists are types of user.
public abstract class User {
    private final String fullName;
    private final int id;
    private final String password;

    protected User(String fullName, int id, String password) {
        this.fullName = fullName;
        this.id = id;
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public int getID() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}