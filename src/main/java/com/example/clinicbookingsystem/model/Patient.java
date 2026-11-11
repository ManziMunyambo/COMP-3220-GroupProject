package com.example.clinicbookingsystem.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.time.LocalDate;

/** Patient-specific data plus the notes API already used by patient registration. */
public class Patient extends Person {
    private Address address;
    private String email;
    private final List<String> medicalRecords = new ArrayList<>();
    private String familyDoctor;
    private int healthInsurance;


    //Constructor
    public Patient(String fullName, LocalDate dateOfBirth, ContactPreference contactInfo, int id, String password, Address address,
            String email, String familyDoctor, int healthInsurance) {
        super(fullName, dateOfBirth, contactInfo, id, password);
        this.address = address;
        this.email = email;
        this.familyDoctor = familyDoctor;
        this.healthInsurance = healthInsurance;
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

    //Check that the expected fullName and dateOfBirth of the patient matches the actual patient
    public Boolean verifyIdentity(String fullName, LocalDate dateOfBirth){
        if(this.fullName.equals(fullName) && this.dateOfBirth.equals(dateOfBirth)){
            return true;
        }
        return false;
    }
}