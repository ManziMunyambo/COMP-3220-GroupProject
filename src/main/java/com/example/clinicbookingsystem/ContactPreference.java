package com.example.clinicbookingsystem;

public class ContactPreference{
    //attributes
    private String phoneNumber;
    private boolean isSharedHouseholdLine;
    private boolean allowMedicalRecord;

    //Constructors
    public ContactPreference(String phoneNumber, boolean isSharedHouseholdLine, boolean allowMedicalRecord){
        this.phoneNumber = phoneNumber;
        this.isSharedHouseholdLine = isSharedHouseholdLine;
        this.allowMedicalRecord = allowMedicalRecord;
    }

    public ContactPreference(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

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
