package com.example.clinicbookingsystem.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.time.LocalDate;

import com.example.clinicbookingsystem.Address;
import com.example.clinicbookingsystem.ContactPreference;
import com.example.clinicbookingsystem.model.Person;


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

    public String getEmail() {
        return email;
    }

    public String getFamilyDoctor() {
        return familyDoctor; 
    }

    public int getHealthInsurance() {
        return healthInsurance;
    }

    public Address getAddress(){
        return address;
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

    //Update address allows the Patient alone to update its own address
    private void updateAddress(Address address){
        this.address = address;
    }

    //Request update address allows other users to request the patient to update its own address
    public void requestUpdateAddress(Address address){
        //Allow the patient to choose whether it updates its own state
        if(!(address.getPostalCode().equals("")) && !(address.getCity().equals("")) && !(address.getStreetAddress().equals(""))){
            updateAddress(address);
        }
    }

    //Request update contact preference allows other users to request the patient to update its own contact info
    public void requestUpdateContactPreference(ContactPreference contactInfo){
        //Allow the patient to choose whether it updates its own state
        if(!(contactInfo.getPhoneNumber().equals(""))){
            updateContactPreference(contactInfo);
        }
    }
}