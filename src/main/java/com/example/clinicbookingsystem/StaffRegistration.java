package com.example.clinicbookingsystem;
import com.example.clinicbookingsystem.*;
import com.example.clinicbookingsystem.model.StaffMember;
import com.example.clinicbookingsystem.model.Receptionist;
import com.example.clinicbookingsystem.model.Person;

public class StaffRegistration extends Registration{
    //constructor
    public StaffRegistration(){}
    
    @Override 
    public StaffMember register(){
        //fill in the body based on the input fields in the UI
        return null;
    }

    @Override 
    public boolean validateRegistration(Person staffmember){
        //If a receptionist is registering, check whether the receptionist exists based on name and birth date in the system
        if(staffmember instanceof Receptionist receptionist){
            Receptionist returned_result = database.selectReceptionistValidate(receptionist.getFullName(), receptionist.getDateOfBirth());
            //If no receptionist is passed, validation is complete
            if(returned_result == null){
                return true;
            }
            //Otherwise, validation fails
            else{
                return false;
            }
        }
        //For now, if the staff member is not a receptionist, return false - will change as system is extended
        return false;

    }
}
