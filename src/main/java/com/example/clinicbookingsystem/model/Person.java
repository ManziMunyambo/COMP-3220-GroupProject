package com.example.clinicbookingsystem.model;

// this is the Shared account and profile data - patient and receptionists are types of user.
public abstract class Person {
    protected final String fullName;
    protected LocalDate dateOfBirth;
    protected ContactPreference contactInfo;
    protected final int id;
    protected final String password;

    protected Person(String fullName, LocalDate dateOfBirth, ContactPreference contactInfo, int id, String password) {
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.contactInfo = contactInfo;
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

    public LocalDate getDateOfBirth(){
        return dateOfBirth;
    }
}