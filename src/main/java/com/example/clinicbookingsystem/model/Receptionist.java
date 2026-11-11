package com.example.clinicbookingsystem.model;

public class Receptionist extends User{
    //Receptionist have no unique attributes beyond those defined in the User class
    //Constructor for Receptionist
    public Receptionist(String fullName, int id, String password){
        super(fullName, id, password);
    }
}