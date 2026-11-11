package com.example.clinicbookingsystem;

public class Address {
    //attributes
    private String streetAddress;
    private String city;
    private String postalCode;

    //Constructor
    public Address(String streetAddress, String city, String postalCode){
        this.streetAddress = streetAddress;
        this.city = city;
        this.postalCode = postalCode;
    }

    //getters    
    public String getStreetAddress(){return streetAddress;}
    public String getCity(){return city;}
    public String getPostalCode(){return postalCode;}
    
    //methods
    public boolean isComplete(){
        return false;
        //fill in the body later
    }
}
