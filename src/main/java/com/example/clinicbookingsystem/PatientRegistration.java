package com.example.clinicbookingsystem;
import com.example.clinicbookingsystem.*;
import com.example.clinicbookingsystem.model.Patient;
import com.example.clinicbookingsystem.model.Person;

public class PatientRegistration extends Registration {
    //constructor
    public PatientRegistration(){}
    
    @Override 
    public Patient register(){
        //fill in the body based on the input boxes on the UI
        return null;
    }
    
    @Override 
    public boolean validateRegistration(Person Visitor){
        //Confirm a patient was passed
        if(Visitor instanceof Patient patient){
            //Check if patient already exists in database based on name, address, and date of birth
            Patient returned_result = database.selectPatientValidate(patient.getFullName(), patient.getAddress(), patient.getDateOfBirth());
            //If no patient was returned, validation is complete
            if(returned_result == null){
                return true;
            //Otherwise, validation fails
            }else{
                return false;
            }
        }
        //If person is not a patient, return false
        return false;
    }
}