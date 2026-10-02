package com.example.clinicbookingsystem;
import com.example.clinicbookingsystem.model.Patient;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class PatientRegistration {
    // this is just to make sure the date is parsed correctly (nth too serious)
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("MM-dd-uuuu").withResolverStyle(ResolverStyle.STRICT);
    private Database database = new Database("database/clinic.db");

    public PatientRegistration(){}
    
    public PatientRegistration(Database database){
        this.database = database;
    }

    public Patient registerPatient(Scanner input){
        System.out.println("\n --- REGISTERING NEW PATIENT --- \n");
        
        System.out.print("Health Card Number: ");
        String healthCardNumber = input.nextLine();

        System.out.print("\nFull Name:");
        String fullName = input.nextLine().trim();

        System.out.print("\nAddress: ");
        String address = input.nextLine().trim();

        System.out.print("\nEmail: ");
        String email = input.nextLine().trim();

        System.out.print("\nDate of Birth (MM-DD-YYYY): ");
        String dobString = input.nextLine().trim();
        LocalDate dateOfBirth = LocalDate.parse(dobString);

        System.out.print("\nLogin portal Username: ");
        String username = input.nextLine().trim();

        System.out.print("\nLogin portal Password ");
        String password = input.nextLine().trim();

        input.close();

        Patient patient = new Patient(healthCardNumber, fullName, address, email, dateOfBirth, username, password);
        return patient;
    }
    
    /**
     * changing validatePatient to work with the Database code
     * 
     * boolean validatePatient(){
     * selectReceptionist(Patient p)
     * 
     * if(null){
     *   createPatient
     * }
     * 
     * else{
     * return P 
     * return false
     * }
     * 
     * code will be implemented later
     * }
    */
}
