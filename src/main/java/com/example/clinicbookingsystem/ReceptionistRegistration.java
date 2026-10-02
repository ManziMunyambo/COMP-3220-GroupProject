package com.example.clinicbookingsystem;
import com.example.clinicbookingsystem.model.Receptionist;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.DateTimeException; 
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class ReceptionistRegistration {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM-dd-uuuu").withResolverStyle(ResolverStyle.STRICT);
    private Database database = new Database("database/clinic.db"); 
    
    public ReceptionistRegistration(){}
    
    public ReceptionistRegistration(Database database){
        this.database = database;
    }

    /**
     * registerReceptionist(Scanner input)
     * 
     * ask user for their fullName, address, email, birthDate (LocalDate), username, password
     * make a receptionist, return it
     * 
     */
    public Receptionist registerReceptionist(Scanner input){
        System.out.println("\n--- NEW RECEPTIONIST --- \n");

        System.out.print("\nFull Name:");
        String fullName = input.nextLine().trim();

        System.out.print("\nAddress: ");
        String address = input.nextLine().trim();

        System.out.print("\nEmail: ");
        String email = input.nextLine().trim();

        System.out.print("\nDate of Birth (MM-DD-YYYY): ");
        String dobString = input.nextLine().trim();
        LocalDate dateOfBirth = LocalDate.parse(dobString);

        System.out.print("\nSystem Username: ");
        String username = input.nextLine().trim();

        System.out.print("\nSystem Password ");
        String password = input.nextLine().trim();

        input.close();

        Random rand = new Random();
        int rid = rand.nextInt(101); //<- idk 
        Receptionist receptionist = new Receptionist(rid, fullName, address, email, dateOfBirth, username, password);
        return receptionist;
    }


    /**
     * changing validateReceptionist to work with the Database code
     * 
     * boolean validateReceptionist(){
     * selectReceptionist(Receptioinst R)
     * 
     * if(null){
     *   createReceptionist
     * }
     * 
     * else{
     * return R  
     * return false
     * }
     * 
     * 
     * }
     */

}
