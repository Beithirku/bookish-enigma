package Stock;

import java.time.LocalDate;

public class AdminMember extends Members
{
    AdminMember()
    {
        setMemberType("Admin");
        setBorrowAmount(6);
    }

    //Sets the default values to whatever values are passed here based on member types. Just a parameterised constructor
    //The passed in values are inside the () and they are then subsequently changed in the constructor below using setters.
    //Using parameterised constructors here is a good idea, since we can change some info based on user type.
    //Admin is basically just an extension of Staff but since not all Staff is an Admin (I assume? just basing this off of my knowledge of system like the college one)
    //Thus having a separate Admin class is handy.
    AdminMember(String memberType, String forename, String surname, int userID, String username, String password, String contactNumber, String emailAddress, String streetAddress, String town, String postcode, String dateOfBirth, double incurredFines)
    {
        setMemberType(memberType);
        setForename(forename);
        setSurname(surname);
        setUserID(userID);
        setUsername(username);
        setPassword(password);
        setContactNumber(contactNumber);
        setEmailAddress(emailAddress);
        setStreetAddress(streetAddress);
        setTown(town);
        setPostcode(postcode);
        setDateOfBirth(dateOfBirth);
        setIncurredFines(incurredFines);
    }

}
