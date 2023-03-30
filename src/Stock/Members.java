package Stock;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Members
{

    private String memberType; //This stores what level of membership they have.
    private int borrowAmount; //The allowed amount of borrows per user.
    private String forename; //First name of the user.
    private String surname; //Last name of the user.
    private String dateOfBirth; //Stores a members date of birth
    private DateTimeFormatter dobForm; //Formats the date of birth
    private int userID; //User ID of the User, this is only used for loaning.
    private String username; //Username of the user.
    private String password; //The users password.
    private String contactNumber; //Stores their contact number.
    private String emailAddress; //Stores their email address.
    private String streetAddress; //Stores their street address.
    private String town; //Stores their town.
    private String postcode; //Stores their postcode.
    private double incurredFines; //Stores any incurred unpaid fines.

    //Initialises and sets default values of variables.
    public void Member()
    {
        memberType = null;
        borrowAmount = 6;
        forename = null;
        surname = null;
        username = null;
        password = null;
        contactNumber = null;
        emailAddress = null;
        streetAddress = null;
        town = null;
        postcode = null;
        dateOfBirth = null;
        dobForm = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        incurredFines = 0.00;
    }

    //That's right folks it's GETTER AND SETTERS TIMEEEEEEEEEE
    public String getMemberType()
    {
        return memberType;
    }

    public void setMemberType(String memberType)
    {
        this.memberType = memberType;
    }

    public int getBorrowAmount()
    {
        return borrowAmount;
    }

    public void setBorrowAmount(int borrowAmount)
    {
        this.borrowAmount = borrowAmount;
    }

    public String getForename()
    {
        return forename;
    }

    public void setForename(String forename)
    {
        this.forename = forename;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getContactNumber()
    {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber)
    {
        this.contactNumber = contactNumber;
    }

    public String getEmailAddress()
    {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }

    public String getStreetAddress()
    {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress)
    {
        this.streetAddress = streetAddress;
    }

    public String getTown()
    {
        return town;
    }

    public void setTown(String town)
    {
        this.town = town;
    }

    public String getPostcode()
    {
        return postcode;
    }

    public void setPostcode(String postcode)
    {
        this.postcode = postcode;
    }

    public double getIncurredFines()
    {
        return incurredFines;
    }

    public void setIncurredFines(double incurredFines)
    {
        this.incurredFines = incurredFines;
    }

    //Writes members to a CSV and/or creates the CSV.
    //Oddly enough this is done quite similarly in JavaScript.
    public void writeMember()
    {
        String addMember = "";
        try
        {
            FileReader file = new FileReader("members.csv");
            BufferedReader buff = new BufferedReader(file);

            String line = buff.readLine(); //This reads the first line.
            addMember = "";

            //Basically just, continue changing the line and reading data from it until the lines contain no data.
            while (line != null)
            {
                addMember += line +"\n";
                line = buff.readLine(); //This one changes the line.
            }
            file.close();
        }
        //Catches any error which may occur.
        catch (IOException e)
        {
            e.printStackTrace();
        }

        //Adds data from the object to addMember.
        addMember += memberType + ", ";
        addMember += forename + ", ";
        addMember += surname + ", ";
        addMember += userID + ", ";
        addMember += username + ", ";
        addMember += password + ", ";
        addMember += contactNumber + ", ";
        addMember += emailAddress + ", ";
        addMember += streetAddress + ", ";
        addMember += town + ", ";
        addMember += postcode + ", ";
        addMember += dateOfBirth + ", ";
        addMember += incurredFines;

        try
        {
            //Overwrites data in the file.
            FileWriter input = new FileWriter("members.csv");
            input.write(addMember);
            input.close();
        }
        //Catchs any errors which may occur.
        catch (IOException e)
        {
            e.printStackTrace();
        }


    }


}
