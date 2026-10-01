//Import JDBC packages
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;


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
                    Pid INTEGER PRIMARY KEY,
                    First_Name TEXT NOT NULL,
                    Last_Name TEXT NOT NULL,
                    Password TEXT NOT NULL
                );
                CREATE TABLE IF NOT EXISTS Receptionist(
                    Rid INTEGER PRIMARY KEY,
                    First_Name TEXT NOT NULL,
                    Last_Name TEXT NOT NULL,
                    Password TEXT NOT NULL
                );
                CREATE TABLE IF NOT EXISTS Doctor(
                    Did INTEGER PRIMARY KEY,
                    First_Name TEXT NOT NULL,
                    Last_Name TEXT NOT NULL,
                    Password TEXT NOT NULL,
                    Specialty TEXT
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
    public Patient selectPatient(String first_Name, String last_Name, String password){
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            //Connect to database
            conn = DriverManager.getConnection(connectionString);
            //Define SQL query for receptionist
            String patientSelect = """   
                SELECT * 
                FROM Patient P
                WHERE P.First_Name = ?
                AND P.Last_Name = ?
                AND P.Password = ?;
                    """;
            //Add arguments into dynamic SQL statement
            pstmt = conn.prepareStatement(patientSelect);
            pstmt.setString(1, first_Name);
            pstmt.setString(2, last_Name);
            pstmt.setString(3, password);
            //Execute the command and store the result
            ResultSet rs = pstmt.executeQuery();
            //If a row is selected, return it as a Receptionist object
            if(rs.next()){
                int pid_Returned = rs.getInt("Pid");
                String first_Name_Returned = rs.getString("First_Name");
                String last_Name_Returned = rs.getString("Last_Name");
                String password_Returned = rs.getString("Password");
                System.out.println("Patient found");
                Patient CreatedPatient = new Patient(pid_Returned, first_Name_Returned, last_Name_Returned, password_Returned);
                return CreatedPatient;
            //If no row is selected, return null
            }else{
                System.out.println("No patients found with that information");
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
    public Receptionist selectReceptionist(String first_Name, String last_Name, String password){
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            //Connect to database
            conn = DriverManager.getConnection(connectionString);
            //Define SQL query for receptionist
            String receptionistSelect = """   
                SELECT * 
                FROM Receptionist R
                WHERE R.First_Name = ?
                AND R.Last_Name = ?
                AND R.Password = ?;
                    """;
            //Add arguments into dynamic SQL statement
            pstmt = conn.prepareStatement(receptionistSelect);
            pstmt.setString(1, first_Name);
            pstmt.setString(2, last_Name);
            pstmt.setString(3, password);
            //Execute the command and store the result
            ResultSet rs = pstmt.executeQuery();
            //If a row is selected, return it as a Receptionist object
            if(rs.next()){
                int rid_Returned = rs.getInt("Rid");
                String first_Name_Returned = rs.getString("First_Name");
                String last_Name_Returned = rs.getString("Last_Name");
                String password_Returned = rs.getString("Password");
                System.out.println("Patient found");
                Receptionist CreatedReceptionist = new Receptionist(rid_Returned, first_Name_Returned, last_Name_Returned, password_Returned);
                return CreatedReceptionist;
            //If no row is selected, return null
            }else{
                System.out.println("No receptionists found with that information");
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
                    INSERT INTO Patient (Pid, First_Name, Last_Name, Password)
                    Values (?, ?, ?, ?)
                    """;
            //Add arguments to dynamic SQL
            pstmt = conn.prepareStatement(addPatient);
            pstmt.setInt(1, P.getPID());
            pstmt.setString(2, P.getFirstName());
            pstmt.setString(3, P.getLastName());
            pstmt.setString(4, P.getPassword());
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
                    INSERT INTO Receptionist (Rid, First_Name, Last_Name, Password)
                    Values (?, ?, ?, ?)
                    """;
            //Add arguments to dynamic SQL
            pstmt = conn.prepareStatement(addReceptionist);
            pstmt.setInt(1, R.getRID());
            pstmt.setString(2, R.getFirstName());
            pstmt.setString(3, R.getLastName());
            pstmt.setString(4, R.getPassword());
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
