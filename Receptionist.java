public class Receptionist{
    //Define attributes

    //RID is the recceptionist's unique id 
    int RID;
    String firstName;
    String lastName;
    //Each receptionist has a password for the system
    String password;

    //Constructor for Receptionist
    public Receptionist(int id, String fname, String lname, String pword){
        this.RID = id;
        this.firstName = fname;
        this.lastName = lname;
        this.password = pword;
    }

    //Getter method for RID
    public int GetRID(){
        return this.RID;
    }

    //Getter method for firstName
    public String GetFirstName(){
        return this.firstName;
    }

    //Getter method for lastName
    public String GetLastName(){
        return this.lastName;
    }

    //Getter method for password
    public String GetPassword(){
        return this.password;
    }
}