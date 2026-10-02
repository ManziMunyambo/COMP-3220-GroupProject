import java.util.Scanner;
import java.time.LocalDate;
import java.time.DateTimeException; 

public class ReceptionistRegistration {
    private Database database; //"database" is just a template. I'll change this once Ashlynn's code is pulled

    public ReceptionistRegistration(Database database){
        this.database = database;
    }

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

        System.out.print("\nSystem Username: ");
        String username = input.nextLine().trim();

        System.out.print("\nSystem Password ");
        String password = input.nextLine().trim();

        String employeeID = generateEmployeeID();

        if(!validateReceptionist(employeeID, fullName, address, email, dobString, username, password)){
            return null;
        }

        String dateOfBirth = LocalDate.parse(dobString);
        Receptionist newReceptionist = new Receptionist(employeeId, fullName, address, email, dateOfBirth, username, password);

        saveReceptionist(newReceptionist);
        return newReceptionist;
    }


    public void saveReceptionist(Receptionist receptionist){
        if(receptionist != null){
            database.addReceptionist(receptionist);
            System.out.println("\nReceptionist " + receptionist.getFullName() + " has been added to database :)");
        }
    }


    boolean validateReceptionist(String employeeID, String fullName, String address, String email, String dobString, String username, String password){
        if(employeeID.isEmpty() || fullName.isEmpty() || address.isEmpty() || email.isEmpty() || dobString.isEmpty() || username.isEmpty() || password.isEmpty()){
            System.out.print("\nRegistration failure - All fields are required");
            return false;
        }

        if(database.usernameExists(username)){
            System.out.print("\nRegistration failure - Username " + username + " is taken");
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
        //test
    }
}
