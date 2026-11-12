package com.example.clinicbookingsystem.model;
import com.example.clinicbookingsystem.*;
import java.time.LocalDate;

public class StaffMember extends Person{
    //attributes
    private String staffID;
    private String systemUsername;

    //constructor
    public StaffMember(String fullName, LocalDate dateOfBirth, ContactPreference contactPreference, int id, String password, String systemUsername){
        super(fullName, dateOfBirth, contactPreference, id, password);
        this.staffID = staffID;
        this.systemUsername = systemUsername;
    }

    //getters
    public String getStaffID(){return staffID;}
    public String getSystemUsername(){return systemUsername;}
}
