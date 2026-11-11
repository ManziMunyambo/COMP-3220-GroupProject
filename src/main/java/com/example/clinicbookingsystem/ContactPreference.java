package com.example.clinicbookingsystem;

public class ContactPreference{
    //attributes
    private String phoneNumber;
    private boolean isSharedHouseholdLine;
    private boolean allowMedicalRecord;

    //getters
    public String getPhoneNumber(){return phoneNumber;}
    public boolean getIsSharedHouseholdLine(){return isSharedHouseholdLine;}
    public boolean getAllowMedicalRecord(){return allowMedicalRecord;}

    //methods
    public boolean canLeaveDetailedMessage(){
        return false;
        //fill in the body later
    }
}
