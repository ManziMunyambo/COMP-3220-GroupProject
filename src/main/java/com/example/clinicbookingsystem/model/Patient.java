package com.example.clinicbookingsystem.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.time.LocalDate;

/** Patient-specific data plus the notes API already used by patient registration. */
public class Patient extends User {
    private LocalDate dateOfBirth;
    private String address;
    private int phoneNumber;
    private String email;
    private final List<String> medicalRecords = new ArrayList<>();
    private String familyDoctor;
    private int healthInsurance;

    public Patient(String fullName, int id, String password, LocalDate dateOfBirth, String address,
            int phoneNumber, String email, String familyDoctor, int healthInsurance) {
        super(fullName, id, password);
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.familyDoctor = familyDoctor;
        this.healthInsurance = healthInsurance;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getFamilyDoctor() {
        return familyDoctor; 
    }

    public int getHealthInsurance() {
        return healthInsurance;
    }

    // Calculate age from the birth date so it does not become stale over time.
    public int getAge() {
        return Period.between(getDateOfBirth(), LocalDate.now()).getYears();
    }

    public void addMedicalRecord(String record) {
        medicalRecords.add(record);
    }

    public List<String> getMedicalRecords() {
        return Collections.unmodifiableList(medicalRecords);
    }
}