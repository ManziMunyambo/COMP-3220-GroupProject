package com.example.clinicbookingsystem;

public class Address {
    //attributes
    private String streetAddress;
    private String city;
    private String postalCode;

    //getters    
    public String getStreetAddress(){return streetAddress;}
    public String getCity(){return city;}
    public String getPostalCode(){return postalCode;}
    
    //methods
    public boolean isComplete(){
        return false;
        //fill in the body later
    }

    public void updatePostalCode(String city){
        this.city = city;
        //although this breaks encapsulation, some exceptions gotta be made
    }
}
