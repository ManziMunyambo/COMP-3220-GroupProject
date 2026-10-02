public class Receptionist{
    //Define attributes

    //RID is the recceptionist's unique id 
    private int RID;
    private String firstName;
    private String lastName;
    //Each receptionist has a password for the system
    private String password;

    //Constructor for Receptionist
    public Receptionist(int id, String fname, String lname, String pword){
        this.RID = id;
        this.firstName = fname;
        this.lastName = lname;
        this.password = pword;
    }

    //Getter method for RID
    public int getRID(){
        return this.RID;
    }

    //Getter method for firstName
    public String getFirstName(){
        return this.firstName;
    }

    //Getter method for lastName
    public String getLastName(){
        return this.lastName;
    }

    //Getter method for password
    public String getPassword(){
        return this.password;
    }
}