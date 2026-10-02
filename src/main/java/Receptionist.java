public class Receptionist{
    //Define attributes

    //RID is the recceptionist's unique id 
    private int RID;
    private String fullName;
    private String Address;
    private String email;
    private String birthDate;
    private String username;
    //Each receptionist has a password for the system
    private String password;

    //Constructor for Receptionist
    public Receptionist(int id, String name, String address, String email, String birhtDate, String uname, String pword){
        this.RID = id;
        this.fullName = name;
        this.Address = address;
        this.email = email;
        this.birthDate = birthDate;
        this.username = uname;
        this.password = pword;
    }

    //Getter method for RID
    public int getRID(){
        return this.RID;
    }

    //Getter method for ame
    public String getName(){
        return this.fullName;
    }

    //Getter method for address
    public String getAddress(){
        return this.address;
    }

    //Getter method for email
    public String getEmail(){
        return this.email;
    }

    //Getter method for birthDate
    public String getBirthDate(){
        return this.birthDate;
    }

    //Getter method for user name
    public String getUsername(){
        return this.username;
    }

    //Getter method for password
    public String getPassword(){
        return this.password;
    }
}