package com.example.clinicbookingsystem;
import com.example.clinicbookingsystem.*;

public class StaffRegistration extends Registration{
    //constructor
    public StaffRegistration(){}
    
    @Override 
    public StaffMember register(){
        //fill in the body based on the input fields in the UI
    }

    @Override 
    public boolean validateRegistration(StaffMember staffmember, String staffType){
        //If a receptionist is registering, check whether the receptionist exists based on name and birth date in the system
        if(staffType.equals("Receptionist")){
            Receptionist returned_result = database.selectReceptionistValidate(staffMember.fullName, staffMember.dateOfBirth);
            //If no receptionist is passed, validation is complete
            if(returned_result = null){
                return true;
            }
            //Otherwise, validation fails
            else{
                return false;
            }
        }

    }
}
