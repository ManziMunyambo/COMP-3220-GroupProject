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
                    Address TEXT NOT NULL,
                    Phone_Number INTEGER NOT NULL,
                    Email TEXT NOT NULL,
                    Family_Doctor TEXT,
                    Health_Insurance INTEGER
                );
                CREATE TABLE IF NOT EXISTS Receptionist(
                    Full_Name TEXT NOT NULL,
                    Rid INTEGER PRIMARY KEY,
                    Password TEXT NOT NULL
                );
                CREATE TABLE IF NOT EXISTS Doctor(
                    Did INTEGER PRIMARY KEY,
                    Full_Name TEXT NOT NULL,
                    Password TEXT NOT NULL,
                    Specialty TEXT,
                    Days_Available TEXT,
                    Medical_License INTEGER NOT NULL,
                    Phone_Number INTEGER NOT NULL,
                    Email TEXT NOT NULL,
                );
                CREATE TABLE IF NOT EXISTS Appointment(
                    Start_Time TEXT NOT NULL,
                    End_Time TEXT NOT NULL,
                    Did INTEGER NOT NULL,
                    Pid INTEGER NOT NULL,
                    PRIMARY KEY(Start_Time, End_Time, Did),
                    FOREIGN KEY (Did) REFERENCES Doctor(Did),
                    FOREIGN KEY (Pid) REFERENCES Patient(Pid)
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
                String address_Returned = rs.getString("Address");
                int phone_Number_Returned = rs.getInt("Phone_Number");
                String email_Returned = rs.getString("Email");
                String family_Doctor_Returned = rs.getString("Family_Doctor");
                int health_Insurance_Returned = rs.getInt("Health_Insurance");
                System.out.println("Patient found");
                Patient CreatedPatient = new Patient(full_Name_Returned, id, password, birth_Date_Returned, address_Returned, phone_Number_Returned, email_Returned, family_Doctor_Returned, health_Insurance_Returned);
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
                System.out.println("Receptionist found");
                Receptionist CreatedReceptionist = new Receptionist(full_Name_Returned, RID, password);
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
                    INSERT INTO Patient (Full_Name, Pid, Password, Date_Of_Birth, Address, Phone_Number, Email, Family_Doctor, Health_Insurance)
                    Values (?, ?, ?, ?, ?, ?, ?, ?, ?)
                    """;
            //Add arguments to dynamic SQL
            pstmt = conn.prepareStatement(addPatient);
            pstmt.setString(1, P.getFullName());
            pstmt.setInt(2, P.getID());
            pstmt.setString(3, P.getPassword());
            String birthDate = P.getDateOfBirth().toString();
            pstmt.setString(4, birthDate);
            pstmt.setString(5, P.getAddress());
            pstmt.setInt(6, P.getPhoneNumber());
            pstmt.setString(7, P.getEmail());
            pstmt.setString(8, P.getFamilyDoctor());
            pstmt.setInt(9, P.getHealthInsurance());
            //Execute SQL query and store the number of rows impacted
            int rowsAdded = pstmt.executeUpdate();
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
                    INSERT INTO Receptionist (Full_Name, Rid, Password)
                    Values (?, ?, ?)
                    """;
            //Add arguments to dynamic SQL
            pstmt = conn.prepareStatement(addReceptionist);
            pstmt.setString(1, R.getFullName());
            pstmt.setInt(2, R.getID());
            pstmt.setString(3, R.getPassword());
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
}
