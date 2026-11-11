package com.example.clinicbookingsystem.model;
import java.time.LocalDate;
import com.example.clinicbookingsystem.*;

// this is the Shared account and profile data - Patient and Receptionist are types of Person.
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

    //Update contact preference allows a Person to update its own contact preference
    protected void updateContactPreference(ContactPreference contactInfo){
        this.contactInfo = contactInfo;
    }
}