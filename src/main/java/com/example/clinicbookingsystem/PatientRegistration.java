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

        System.out.print("\nFull Name:");
        String fullName = input.nextLine().trim();

        System.out.print("\nSystem ID:");
        int id = Integer.parseInt(input.nextLine().trim());

        System.out.print("\nDate of Birth (MM-DD-YYYY): ");
        String dobString = input.nextLine().trim();
        LocalDate dateOfBirth = LocalDate.parse(dobString);

        System.out.print("\nAddress: ");
        String address = input.nextLine().trim();

        System.out.print("\nPhone Number (no hyphens): ");
        int phone = Integer.parseInt(input.nextLine().trim());

        System.out.print("\nEmail: ");
        String email = input.nextLine().trim();

        System.out.print("\nFamily Doctor: ");
        String familyDoctor = input.nextLine().trim();

        System.out.print("\nHealth Insurance Number: ");
        int healthInsurance = Integer.parseInt(input.nextLine().trim());

        System.out.print("\nLogin portal Password ");
        String password = input.nextLine().trim();

        input.close();

        Patient patient = new Patient(fullName, id, password, dateOfBirth, address, phone, email, familyDoctor, healthInsurance);
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
