package Stock;

import java.time.LocalDate;

public class CasualMember extends Members
{
    CasualMember()
    {
        setMemberType("Casual");
        setBorrowAmount(0);
    }

    //Sets the default values to whatever values are passed here based on member types. Just a parameterised constructor
    //The passed in values are inside the () and they are then subsequently changed in the constructor below using setters.
    CasualMember(String memberType, String forename, String surname, int userID, String username, String password, String contactNumber, String emailAddress, String streetAddress, String town, String postcode, String dateOfBirth, double incurredFines)
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
