package com.example.clinicbookingsystem.model;

import java.time.LocalDate;

// this is the Shared account and profile data and patient is just a type of user.
public abstract class User {
    private final String fullName;
    private final String address;
    private final String email;
    private final LocalDate dateOfBirth;
    private final String username;
    private final String password;

    protected User(
            String fullName,
            String address,
            String email,
            LocalDate dateOfBirth,
            String username,
            String password) {
        this.fullName = fullName;
        this.address = address;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.username = username;
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}