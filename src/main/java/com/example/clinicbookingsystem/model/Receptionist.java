package com.example.clinicbookingsystem.model;

//Import date time
import java.time.LocalDate;


public class Receptionist extends User{
    //Define attributes

    //RID is the recceptionist's unique id 
    private int RID;

    //Constructor for Receptionist
    public Receptionist(int id, String name, String address, String email, LocalDate birthDate, String uname, String pword){
        super(name, address, email, birthDate, uname, pword);
        this.RID = id;
    }

    //Getter method for RID
    public int getRID(){
        return this.RID;
    }
}