package com.example.clinicbookingsystem;

//Import JDBC packages
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

//Import date time
import java.time.LocalDate;
import com.example.clinicbookingsystem.model.Patient;
import com.example.clinicbookingsystem.model.Receptionist;

//Import lists
import java.util.List;
import java.util.ArrayList;

public class Database {
    //Constants for connection to SQLite
    private static String connectionString;
    //INCLUDE database name
    private static String databaseLocation; 

    //Constructor to pass connection strings to the Database
    //Private constructor as there should be a single database
    public Database(String dbLocation){
        this.databaseLocation = dbLocation;
        this.connectionString = "jdbc:sqlite:"+databaseLocation;
    }

    //initializeDatabase creates the database and tables if they do not already exist
    public static void initializeDatabase(){
        Connection conn = null;
        Statement stmt = null;
        try{
            //Connect to the database, or create it if it doesn't already exist
            conn = DriverManager.getConnection(connectionString);
            //Define SQL command for creating tables
            String tableCreation = """
                CREATE TABLE IF NOT EXISTS Patient(
                    Full_Name TEXT NOT NULL,
                    Pid INTEGER NOT NULL PRIMARY KEY,
                    Password TEXT NOT NULL,
                    Date_Of_Birth TEXT NOT NULL,
                    Phone_Number TEXT NOT NULL,
                    Is_Shared_Household_Line TEXT,
                    Allow_Medical_Voicemail TEXT,
                    Street_Address TEXT NOT NULL,
                    City TEXT NOT NULL,
                    Postal_Code TEXT NOT NULL,
                    Email TEXT NOT NULL,
                    Family_Doctor TEXT,
                    Health_Insurance INTEGER
                );
                CREATE TABLE IF NOT EXISTS MedicalRecords(
                    Pid INTEGER NOT NULL,
                    Record_ID INTEGER NOT NULL PRIMARY KEY,
                    Note TEXT,
                    FOREIGN KEY (Pid) REFERENCES Patient(Pid)
                );
                CREATE TABLE IF NOT EXISTS Receptionist(
                    Full_Name TEXT NOT NULL,
                    Rid INTEGER PRIMARY KEY,
                    Password TEXT NOT NULL,
                    Date_Of_Birth TEXT NOT NULL,
                    Phone_Number TEXT NOT NULL,
                    System_Username TEXT NOT NULL,
                    Desk_ID INTEGER
                    
                );
                CREATE TABLE IF NOT EXISTS Nurse(
                    Full_Name TEXT NOT NULL,
                    Nid INTEGER PRIMARY KEY,
                    Date_Of_Birth TEXT NOT NULL,
                    Phone_Number TEXT NOT NULL,
                    Password TEXT NOT NULL,
                    System_Username TEXT NOT NULL
                );
                CREATE TABLE IF NOT EXISTS Doctor(
                    Did INTEGER PRIMARY KEY,
                    Full_Name TEXT NOT NULL,
                    Password TEXT NOT NULL,
                    Specialty TEXT,
                    Days_Available TEXT,
                    Medical_License INTEGER NOT NULL,
                    Phone_Number INTEGER NOT NULL,
                    Date_Of_Birth TEXT NOT NULL,
                    System_Username TEXT NOT NULL
                );
                CREATE TABLE IF NOT EXISTS Appointment(
                    Start_Time TEXT NOT NULL,
                    End_Time TEXT NOT NULL,
                    Did INTEGER NOT NULL,
                    Pid INTEGER NOT NULL,
                    Status TEXT,
                    PRIMARY KEY(Start_Time, End_Time, Did),
                    FOREIGN KEY (Did) REFERENCES Doctor(Did),
                    FOREIGN KEY (Pid) REFERENCES Patient(Pid)
                );
                CREATE TABLE IF NOT EXISTS RoomAssignment(
                    Room_Number INTEGER NOT NULL,
                    Start_Time TEXT NOT NULL,
                    End_Time TEXT NOT NULL,
                    DID INTEGER NOT NULL,
                    PRIMARY KEY (Room_Number, Start_Time, End_Time, DID),
                    FOREIGN KEY (DID) REFERENCES Doctor(Did)
                );
                    """;
            //Execute SQL query and store the exit code
            stmt = conn.createStatement();
            int createTableCode = stmt.executeUpdate(tableCreation);
            if(createTableCode == 0){
                System.out.println("Tables successfully created");
            }else{
                System.out.println("Tables could not be created");
            }
        }catch(SQLException e){
            System.out.println("Could not create tables:\n"+e);
        }finally{
            //Close statement and connection 
            if(stmt != null){
                try{
                    stmt.close();
                }catch(SQLException e){
                    System.out.println("Could not close statement:\n"+e);
                }
            }
            if(conn != null){
                try{
                    conn.close();
                }catch(SQLException e){
                    System.out.println("Could not close connection:\n"+e);
                }
            }
        }  
    }

    //selectPatient returns the patient entry based on the name and password provided
    public Patient selectPatient(int id, String password){
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            //Connect to database
            conn = DriverManager.getConnection(connectionString);
            //Define SQL query for receptionist
            String patientSelect = """   
                SELECT * 
                FROM Patient P
                WHERE P.Pid = ?
                AND P.Password = ?
                    """;
            //Add arguments into dynamic SQL statement
            pstmt = conn.prepareStatement(patientSelect);
            pstmt.setInt(1, id);
            pstmt.setString(2, password);
            //Execute the command and store the result
            ResultSet rs = pstmt.executeQuery();
            //If a row is selected, return it as a Receptionist object
            if(rs.next()){
                String full_Name_Returned = rs.getString("Full_Name");
                LocalDate birth_Date_Returned = LocalDate.parse(rs.getString("Date_Of_Birth"));
                String street_Address_Returned = rs.getString("Street_Address");
                String city_Returned = rs.getString("City");
                String postal_Code_Returned = rs.getString("Postal_Code");
                Address address_Returned = new Address(street_Address_Returned, city_Returned, postal_Code_Returned);
                int phone_Number_Returned = rs.getInt("Phone_Number");
                boolean shares_line = Boolean.valueOf(rs.getString("Is_Shared_Household_line"));
                boolean allow_medical_voicemail = Boolean.valueOf(rs.getString("Allow_Medical_Voicemail"));
                ContactPreference contact_Info_Returned = new ContactPreference(phone_Number_Returned, shares_line, allow_medical_voicemail);
                String email_Returned = rs.getString("Email");
                String family_Doctor_Returned = rs.getString("Family_Doctor");
                int health_Insurance_Returned = rs.getInt("Health_Insurance");
                //Access the medical records
                List <String> medical_Records_Returned = getMedicalRecords(id);
                System.out.println("Patient found");
                Patient CreatedPatient = new Patient(full_Name_Returned, id, password, birth_Date_Returned, address_Returned, contact_Info_Returned, email_Returned, family_Doctor_Returned, health_Insurance_Returned, medical_Records_Returned);
                return CreatedPatient;
            //If no row is selected, return null
            }else{
                System.out.println("No patients found with that id and password");
                return null;
            }
        }catch(SQLException e){
            System.out.println("SQL error occured: "+e);
        }finally{
            //Close statement and connection 
            if(pstmt != null){
                try{
                    pstmt.close();
                }catch(SQLException e){
                    System.out.println("Could not close statement:\n"+e);
                }
            }
            if(conn != null){
                try{
                    conn.close();
                }catch(SQLException e){
                    System.out.println("Could not close connection:\n"+e);
                }
            }
        }
        //Return null if an error occurs
        return null;
    }

    //selectReceptionist returns the receptionist entry based on the name and password provided
    public Receptionist selectReceptionist(int RID, String password){
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            //Connect to database
            conn = DriverManager.getConnection(connectionString);
            //Define SQL query for receptionist
            String receptionistSelect = """   
                SELECT * 
                FROM Receptionist R
                WHERE R.Rid = ?
                And R.Password = ?
                    """;
            //Add arguments into dynamic SQL statement
            pstmt = conn.prepareStatement(receptionistSelect);
            pstmt.setInt(1, RID);
            pstmt.setString(2, password);
            //Execute the command and store the result
            ResultSet rs = pstmt.executeQuery();
            //If a row is selected, return it as a Receptionist object
            if(rs.next()){
                String full_Name_Returned = rs.getString("Full_Name");
                LocalDate birth_Date_Returned = LocalDate.parse(rs.getString("Date_Of_Birth"));
                String phone_Number_Returned = rs.getString("Phone_Number");
                ContactPreference contact_Info_Returned = new ContactPreference(phone_Number_Returned);
                String system_Username_Returned = rs.getString("System_Username");
                int desk_Returned = rs.getInt("Desk_ID");
                System.out.println("Receptionist found");
                Receptionist CreatedReceptionist = new Receptionist(full_Name_Returned, RID, password, birth_Date_Returned, contact_Info_Returned, system_Username_Returned, desk_Returned);
                return CreatedReceptionist;
            //If no row is selected, return null
            }else{
                System.out.println("No receptionists found with that id and password");
                return null;
            }
        }catch(SQLException e){
            System.out.println("SQL Exception occurred: \n"+e);
        }finally{
            //Close statement and connection 
            if(pstmt != null){
                try{
                    pstmt.close();
                }catch(SQLException e){
                    System.out.println("Could not close statement:\n"+e);
                }
            }
            if(conn != null){
                try{
                    conn.close();
                }catch(SQLException e){
                    System.out.println("Could not close connection:\n"+e);
                }
            }
        }
        //Return null if an error occurs
        return null;
    }

    //createPatient adds a row to the Patient table with the provided information
    public int createPatient(Patient P){
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            //Connect to database
            conn = DriverManager.getConnection(connectionString);
            //SQL query to add patient
            String addPatient = """
                    INSERT INTO Patient (Full_Name, Pid, Password, Date_Of_Birth, Phone_Number, Is_Shared_Household_Line, Allow_Medical_Voicemail, Street_Address, City, Postal_Code, Email, Family_Doctor, Health_Insurance)
                    Values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                    """;
            //Add arguments to dynamic SQL
            pstmt = conn.prepareStatement(addPatient);
            pstmt.setString(1, P.getFullName());
            //Generate a unique integer ID for the patient
            int pid = generateUniqueID("Patient");
            pstmt.setInt(2, pid);
            pstmt.setString(3, P.getPassword());
            String birthDate = P.getDateOfBirth().toString();
            pstmt.setString(4, birthDate);
            pstmt.setString(5, P.contactInfo.getPhoneNumber());
            pstmt.setString(6, P.contactInfo.getIsSharedHousehold().toString());
            pstmt.setString(7, P.contactInfo.getAllowMedicalVoicemail().toString());
            pstmt.setString(8, P.address.getStreetAddress());
            pstmt.setString(9, P.address.getCity());
            pstmt.setString(10, P.address.getPostalCode());
            pstmt.setString(11, P.getEmail());
            pstmt.setString(12, P.getFamilyDoctor());
            pstmt.setInt(13, P.getHealthInsurance());
            //Execute SQL query and store the number of rows impacted
            int rowsAdded = pstmt.executeUpdate();
            //Add medical notes to the MedicalRecord table
            createMedicalRecord(P.medicalRecords, pid);
            if(rowsAdded == 1){
                System.out.println("1 row sucessfully added to Patient table");
                return 1;
            }else if (rowsAdded == 0){
                System.out.println("Row could not be added");
                return 0;
            }else{
                System.out.println("An error occurred with inserting a row. Returned value is greater than 1");
            }
        }catch(SQLException e){
            System.out.println("SQL Exception occurred: \n"+e);
        }finally{
            //Close statement and connection 
            if(pstmt != null){
                try{
                    pstmt.close();
                }catch(SQLException e){
                    System.out.println("Could not close statement:\n"+e);
                }
            }
            if(conn != null){
                try{
                    conn.close();
                }catch(SQLException e){
                    System.out.println("Could not close connection:\n"+e);
                }
            }
        }
        //Return -1 if an error occurred
        return -1;
    }

    //createReceptionist adds a row to the Patient table with the provided information
    public int createReceptionist(Receptionist R) throws SQLException{
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            //Connect to database
            conn = DriverManager.getConnection(connectionString);
            //SQL query to add patient
            String addReceptionist = """
                    INSERT INTO Receptionist (Full_Name, Rid, Password, Date_Of_Birth, Phone_Number, System_Username, Desk_ID)
                    Values (?, ?, ?, ?, ?, ?, ?)
                    """;
            //Add arguments to dynamic SQL
            pstmt = conn.prepareStatement(addReceptionist);
            pstmt.setString(1, R.getFullName());
            //Generate a unique id to create for the receptionist
            pstmt.setInt(2, generateUniqueID("Receptionist"));
            pstmt.setString(3, R.getPassword());
            pstmt.setString(4, R.getDateOfBirth());
            pstmt.setString(5, R.contactInfo.getPhoneNumber());
            pstmt.setString(6, R.getSystemUsername());
            pstmt.setString(7, R.getDeskID());
            //Execute SQL query and store the number of rows impacted
            int rowsAdded = pstmt.executeUpdate();
            if(rowsAdded == 1){
                System.out.println("1 row sucessfully added to Receptionist table");
            }else if (rowsAdded == 0){
                System.out.println("Row could not be added");
            }else{
                System.out.println("An error occurred with inserting a row. Returned value is greater than 1");
            }
            return rowsAdded;
        }catch(SQLException e){
            System.out.println("SQL Exception occurred:\n"+e);
        }finally{
            //Close statement and connection 
            if(pstmt != null){
                try{
                    pstmt.close();
                }catch(SQLException e){
                    System.out.println("Could not close statement:\n"+e);
                }
            }
            if(conn != null){
                try{
                    conn.close();
                }catch(SQLException e){
                    System.out.println("Could not close connection:\n"+e);
                }
            }
        }
        //Return -1 if an error occurred
        return -1;
    }

    //generateUniqueID takes the table and particular id type for the object, checks the corresponding table for the largest id created, then returns the next integer
    public int generateUniqueID(String table) throws SQLException{
        Connection conn = null;
        PreparedStatement pstmt = null;
        int highestID;
        String findMax = "";
        try{
            //Connect to database
            conn = DriverManager.getConnection(connectionString);
            //Define SQL statement based on the table passed
            if(table.equals("Receptionist")){
                findMax = """
                    SELECT MAX(Rid) FROM Receptionist
                    """; 
            }else if(table.equals("Patient")){
                findMax = """
                    SELECT MAX(Pid) FROM Patient
                    """; 
            }else if(table.equals("MedicalRecords")){
                findMax = """
                    SELECT MAX(Record_ID) FROM MedicalRecords
                    """; 
            }else if(table.equals("Nurse")){
                findMax = """
                    SELECT MAX(Nid) FROM Nurse
                    """; 
            }else if(table.equals("Doctor")){
                findMax = """
                    SELECT MAX(DID) FROM Doctor
                    """; 
            //If table is invalid, return -1 and print a message
            }else{
                System.out.println("Invalid table type chosen");
                return -1;
            }
            pstmt = conn.prepareStatement(findMax);
            //Execute query
            ResultSet rs = pstmt.executeQuery();
            //Access the highest ID
            if(rs.next()){
                highestID = rs.getInt(1);
            //Set highestID to 0 if the table is empty
            }else{
                highestID = 0;
            }
            //Increment the highest id and return it
            highestID += 1;
            return highestID;
        }catch (SQLException e){
            System.out.println("Could not access the max id: "+e);
        }finally{
            //Close statement and connection 
            if(pstmt != null){
                try{
                    pstmt.close();
                }catch(SQLException e){
                    System.out.println("Could not close statement:\n"+e);
                }
            }
            if(conn != null){
                try{
                    conn.close();
                }catch(SQLException e){
                    System.out.println("Could not close connection:\n"+e);
                }
            }
        }
        //Return -1 if an error occurred
        return -1;
    }

    //createMedicalRecord creates a table row in MedicalRecord for each note passed - should only be used once per medical note
    public int createMedicalRecord(List<String> medicalRecords, int pid) throws SQLException{
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            //Connect to database
            conn = DriverManager.getConnection(connectionString);
            //Define the SQL command
            String addMedicalRecord = """
                    INSERT INTO MedicalRecords(Pid, Record_ID, Note)
                    Values (?, ?, ?)
                    """;
            pstmt = conn.prepareStatement(addMedicalRecord);
            //Generate a unique id and then reference it rather than making lots of queries
            int nextRecordID = generateUniqueID("MedicalRecords");
            //Iterate through the medical records
            for(String medicalRecord : medicalRecords){
                //Set the parameters for the SQL statement
                pstmt.setInt(1, pid);
                pstmt.setInt(2, nextRecordID);
                pstmt.setString(3, medicalRecord);
                //Execute the query
                pstmt.executeUpdate();
                //Increment nextRecordID
                nextRecordID = nextRecordID +1;
            }
            //Return 0 if successful
            return 0;
        }catch (SQLException e){
            System.out.println("Could not add all medical records: "+e);
        }finally{
            //Close statement and connection 
            if(pstmt != null){
                try{
                    pstmt.close();
                }catch(SQLException e){
                    System.out.println("Could not close statement:\n"+e);
                }
            }
            if(conn != null){
                try{
                    conn.close();
                }catch(SQLException e){
                    System.out.println("Could not close connection:\n"+e);
                }
            }
        }
        //Return -1 if an error occurred
        return -1;
    } 
    
    //getMedicalRecords returns all medical nots stored for a particular patient
    public List<String> getMedicalRecords(int pid) throws SQLException{
        Connection conn = null;
        PreparedStatement pstmt = null;
        //Define list 
        List<String> medicalRecords = new ArrayList<String>();
        try{
            //Connect to database
            conn = DriverManager.getConnection(connectionString);
            //Define SQL statement
            String findRecords = """
                    SELECT * FROM MedicalRecords WHERE Pid = ?
                    """; 
            pstmt = conn.prepareStatement(findRecords);
            pstmt.setInt(1, pid);
            //Loop through returned values and add them to the list
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                String medicalNote = rs.getString("Note");
                medicalRecords.add(medicalNote);
            }
            //Return the medicalRecords 
            return medicalRecords;
        }catch (SQLException e){
            System.out.println("Could not access all medical records: "+e);
        }finally{
            //Close statement and connection 
            if(pstmt != null){
                try{
                    pstmt.close();
                }catch(SQLException e){
                    System.out.println("Could not close statement:\n"+e);
                }
            }
            if(conn != null){
                try{
                    conn.close();
                }catch(SQLException e){
                    System.out.println("Could not close connection:\n"+e);
                }
            }
        }
        //Return null if an error occurred
        return null;
    }
}
