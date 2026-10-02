package com.example.clinicbookingsystem.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Patient-specific data plus the notes API already used by patient registration. */
public class Patient extends User {
    // Keep this identifier as text so health-card numbers retain leading zeroes.
    private final String healthCardNumber;
    // lowkey these are optional fields for the patient we can discuss whther to add these or not
    private final String sex;
    private final String bloodType;
    private final String disability;
    private final List<String> medicalRecords = new ArrayList<>();

    public Patient(
            String healthCardNumber,
            String fullName,
            String address,
            String email,
            LocalDate dateOfBirth,
            String username,
            String password) {
        this(healthCardNumber, fullName, address, email, dateOfBirth, username, password, null, null, null);
    }

    public Patient(
            String healthCardNumber,
            String fullName,
            String address,
            String email,
            LocalDate dateOfBirth,
            String username,
            String password,
            String sex,
            String bloodType,
            String disability) {
        super(fullName, address, email, dateOfBirth, username, password);
        this.healthCardNumber = healthCardNumber;
        this.sex = sex;
        this.bloodType = bloodType;
        this.disability = disability;
    }

    public String getHealthCardNumber() {
        return healthCardNumber;
    }

    public String getSex() {
        return sex;
    }

    public String getBloodType() {
        return bloodType;
    }

    public String getDisability() {
        return disability;
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