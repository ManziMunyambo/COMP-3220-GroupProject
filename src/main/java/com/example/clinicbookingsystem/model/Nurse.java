package com.example.clinicbookingsystem.model;

import java.time.LocalDate;

import com.example.clinicbookingsystem.ContactPreference;

public class Nurse extends StaffMember{

    //Constructor for Nurse
    public Nurse(String fullName, int id, String password, LocalDate dateOfBirth, ContactPreference contactInfo,
            String systemUsername){
        super(fullName, dateOfBirth, contactInfo, id, password, systemUsername);
    }

    //Confirm the patient's identity by checking their date of birth
    public boolean confirmIdent(Patient p, LocalDate dob){
        return p.verifyIdentity(p.getFullName(), dob);
    }

    //Escort the patient to their assigned room
    public void escortPatient(Patient p, int roomNumber){
        System.out.println("Escorting " + p.getFullName() + " to room " + roomNumber);
    }
}
