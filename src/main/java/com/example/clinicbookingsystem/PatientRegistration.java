package com.example.clinicbookingsystem;

public class PatientRegistration extends Registration {
    @Override 
    public Patient register(){
        //fill in the body based on the input boxes on the UI
    }

    @Override 
    public boolean validateRegistration(Patient patient){
        //Check if patient already exists in database based on name, address, and date of birth
        Patient returned_result = database.selectPatientValidate(patient.fullName, patient.address, patient.dateOfBirth);
        //If no patient was returned, validation is complete
        if(returned_result == null){
            return true;
        //Otherwise, validation fails
        }else{
            return false;
        }
    }
}