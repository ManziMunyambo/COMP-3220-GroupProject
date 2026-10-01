import java.util.Scanner;
import java.time.LocalDate;
import java.time.DateTimeException;

public class PatientRegistration {
    private Database database; //"database" is just a template. I'll change this once Ashlynn's code is pulled

    public PatientRegistration(Database database){
        this.database = database;
    }


    public Patient registerPatient(Scanner input){
        System.out.println("\n --- REGISTERING NEW PATIENT --- \n");

        System.out.print("Health Card Number: ");
        String healthCardNumber = input.nextLine();

        if(isDuplicate(healthCardNumber)){
            System.out.println("\nA patient with health card number " + healthCardNumber + " already exists");
            return null;
        }

        System.out.print("\nFull Name:");
        String fullName = input.nextLine().trim();

        System.out.print("\nAddress: ");
        String address = input.nextLine().trim();

        System.out.print("\nEmail: ");
        String email = input.nextLine().trim();

        System.out.print("\nDate of Birth (MM-DD-YYYY): ");
        String dobString = input.nextLine().trim();

        System.out.print("\nLogin portal Username: ");
        String username = input.nextLine().trim();

        System.out.print("\nLogin portal Password ");
        String password = input.nextLine().trim();

        if(!validatePatient(healthCardNumber, fullName, address, email, dobString, username, password)){
            return null;
        }

        LocalDate dateOfBirth = LocalDate.parse(dobString);
        Patient newPatient = new Patient(healthCardNumber, fullName, address, email, dateOfBirth, username, password);

        System.out.print("\n\nInitial Health Notes/Allergies (Press Enter to skip): ");
        String notes = input.nextLine().trim();
        if(!notes.isEmpty()){
            newPatient.addMedicalRecord(notes);
        }

        savePatient(newPatient);
        return newPatient;
    }


    public void savePatient(Patient patient){
        if(patient != null){
            database.addPatient(patient);
            System.out.println("\nPatient " + patient.getFullName() + " has been added to database :)");
        }
    }


    boolean isDuplicate(String healthCardNumber){
        return database.healthCardExists(healthCardNumber);
    }


    boolean validatePatient(String healthCardNumber, String fullName, String address, String email, String dobString, String username, String password){
        if(healthCardNumber.isEmpty() || fullName.isEmpty() || address.isEmpty() || email.isEmpty() || dobString.isEmpty() || username.isEmpty() || password.isEmpty()){
            System.out.print("\nRegistration failure - All fields are required");
            return false;
        }

        if(database.usernameExists(username)){
            System.out.print("\nRegistration failure - Username" + username + " is taken");
            return false;
        }

        if(!email.contains("@") || !email.contains(".")){
            System.out.print("\nRegistration failure - Invalid email format");
            return false;
        }

        try{
            LocalDate dob = LocalDate.parse(dobString);
            if(dobString.isAfter(LocalDate.now())){
                System.out.print("\nYou're born in the future? Yeah...sure thing pal");
                return false;
            }
        } catch (DateTimeException e){
            System.out.print("\nRegistration failure - Date must be in MM-DD-YYYY format");
            return false;
        }

        return true;
    }
}
