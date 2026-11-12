package com.example.clinicbookingsystem.model;

import java.time.LocalDate;

import com.example.clinicbookingsystem.ContactPreference;
import com.example.clinicbookingsystem.model.Person;


public class Receptionist extends StaffMember{
    private int deskId;

    //Constructor for Receptionist
    public Receptionist(String fullName, int id, String password, LocalDate dateOfBirth, ContactPreference contactInfo,
            String systemUsername, int deskId){
        super(fullName, dateOfBirth, contactInfo, id, password, systemUsername);
        this.deskId = deskId;
    }

    public int getDeskId(){
        return deskId;
    }

    //Find an available room for a patient's appointment
    public void findRoom(){
        //TODO: look up available rooms from the RoomAssignment table
    }

    //Search patient records
    public void searchRecs(){
        //TODO: search patient records through the Database
    }
}
