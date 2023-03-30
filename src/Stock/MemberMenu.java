//Author: Kyle Taylor
//Date: 17th Jan 2022
//Completed: 18th Jan 2022.

package Stock;

import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class MemberMenu {

    private Scanner in = new Scanner (System.in);

    public void memRecMenu()
    {
        if (Login.getMembers().get(Login.getUserIndex()) instanceof CasualMember)
        {
            casualMemRecMenu();
        }
        else if (Login.getMembers().get(Login.getUserIndex()) instanceof AdminMember)
        {
            adminMemRecMenu();
        }
        else
        {
            standardMemRecMenu();
        }
    }

    private void casualMemRecMenu()
    {
        String menuItem;
        boolean quit = false;

        do {
            System.out.println("Member Records");
            System.out.println("1: Edit your profile");
            System.out.println("0: Return to previous menu");

            menuItem = in.next();

            switch (menuItem) {
                case "1":
                    System.out.println();
                    editMem();
                    break;
                case "0":
                    quit = true;
                    Login.getMenu().menu();
                    break;
                default:
                    System.out.println("Insert only 0 to 1.");
            }
        } while (!quit);

        in.close();
    }

    private void standardMemRecMenu()
    {
        String menuItem;
        boolean quit = false;

        do {
            System.out.println("Member Records");
            System.out.println("1: Edit your profile");
            System.out.println("2: View loan history");
            System.out.println("0: Return to previous menu");

            menuItem = in.next();

            switch (menuItem) {
                case "1":
                    System.out.println();
                    editMem();
                    break;
                case "2":
                    System.out.println();
                    myLoans();
                    break;
                case "0":
                    quit = true;
                    Login.getMenu().menu();
                    break;
                default:
                    System.out.println("Insert only 0 to 2.");
            }
        } while (!quit);

        in.close();
        System.exit(0);

    }

    private void adminMemRecMenu()
    {
        String menuItem;
        boolean quit = false;

        do {
            System.out.println("Member Records");
            System.out.println("1: View users");
            System.out.println("2: Add new user");
            System.out.println("3: Edit your profile");
            System.out.println("4: Edit a users profile");
            System.out.println("5: Add a loan");
            System.out.println("6: Return a loan");
            System.out.println("7: View all loan history");
            System.out.println("8: View your loan history");
            System.out.println("0: Return to previous menu");

            menuItem = in.next();

            switch (menuItem) {
            case "1":
                 System.out.println();
                 membersList();
                 break;
            case "2":
                System.out.println();
                createNewMembers();
                break;
            case "3":
                System.out.println();
                editMem();
                break;
            case "4":
                System.out.println();
                editUser();
                break;
            case "5":
                System.out.println();
                loanRec();
                break;
            case "6":
                System.out.println();
                loanReturn();
                break;
            case "7":
                System.out.println();
                allLoans();
                break;
            case "8":
                System.out.println();
                myLoans();
                break;
            case "0":
                quit = true;
                System.out.println();
                Login.getMenu().menu();
                break;
            default:
                System.out.println("Insert only 0 to 8.");
            }
        } while (!quit);

        in.close();
    }

    private void editMem()
    {
        String username = Login.getMembers().get(Login.getUserIndex()).getUsername();
        String[] columns;
        int colNum = 13;
        String colDat = "";
        String finishedFile = "";
        boolean formatCheck = false; //To check if the format is valid.

        try
        {
            FileReader file = new FileReader("members.csv");
            BufferedReader buff = new BufferedReader(file);

            String line = buff.readLine();
            finishedFile = line + "\n";
            columns = line.split(",");
            line = buff.readLine();

            while (line != null)
            {
                String[] sp = line.split(",");

                if (username.equals(sp[4].trim()))
                {
                    System.out.println();

                    for (int i = 0; i < 12; i++)
                    {
                        System.out.println((i+1) + ") " + columns[i].trim() + ": " + sp[i].trim());
                    }
                    System.out.println();

                    while ( colNum < 0 || colNum == 0 || colNum == 3 || colNum == 4 || colNum > 11)
                    {
                        System.out.println("Enter the column number you wish to edit. Member Type, User ID and Username cannot be edited.");
                        try
                        {
                            colNum = in.nextInt() - 1;
                        }
                        catch (Exception e)
                        {
                            System.out.println("Only numbers are accepted as a valid input.");
                            in.nextLine();
                        }
                    }
                    in.nextLine();

                    while (formatCheck == false) {
                        System.out.println("Enter the new information");
                        colDat = (in.nextLine());

                        if (colNum == 9) {
                            try {
                                LocalDate.parse(colDat);
                                formatCheck = true;
                            } catch (Exception e) {
                                System.out.println("Please use the format 'DD-MM-YYYY'");
                            }
                        }
                        else if (colNum == 12 && !colDat.matches("^[0-9]+.[0-9]+$"))
                        {
                            System.out.println("Format is 0.00");
                        }
                        else
                        {
                            formatCheck = true;
                        }
                    }
                    sp[colNum] = colDat;
                    line = "";

                    for (int i = 0; i < 11; i++)
                    {
                        //Here we add the separation for the CSVs
                        //So that we can save the file
                        //You can do "," but ", " adds back the space that was trimmed off, better for readability.
                        //We also check if its the last value on the line, and if so, don't print the ", "
                        if (i == 10)
                        {
                            line += sp[10].trim() + ", " + sp[11].trim() + ", " + sp[12].trim();
                        }
                        else
                        {
                            line += (sp[i].trim() + ", ");
                        }
                    }
                }
                finishedFile += (line + "\n");
                line = buff.readLine();
            }

            file.close();

            try
            {
                FileWriter input = new FileWriter("members.csv");

                input.write(finishedFile);

                input.close();

                System.out.println("Changes have been made.");

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void editUser()
    {
        String username;
        String[] columns;
        int colNum = 12;
        String colDat = "";
        String finishedFile = "";
        boolean uNameExists = false;
        boolean formatCheck = false;

        membersList();

        System.out.println("Enter the username, or press 0.");
        username = in.next();

        if (!username.equals(""))
        {
            try
            {
                FileReader file = new FileReader("members.csv");
                BufferedReader buff = new BufferedReader(file);

                String line = buff.readLine();
                finishedFile = line + "\n";
                columns = line.split(",");
                line = buff.readLine();

                while (line != null)
                {
                    String[] sp = line.split(",");

                    if (username.equals(sp[4].trim()))
                    {
                        uNameExists = true;

                        for (int i = 0; i < 12; i++)
                        {
                            System.out.println((i+1) + ") " + columns[i].trim() + ": " + columns[i].trim());
                        }

                        while ( colNum < 0 || colNum == 3 || colNum == 4|| colNum > 11)
                        {
                            System.out.println("Enter the column number you wish to edit. User ID and Username cannot be edited.");
                            try
                            {
                                colNum = in.nextInt() -1;
                            }
                            catch (Exception e)
                            {
                                System.out.println("Only numbers.");
                                in.nextLine();
                            }
                        }

                        in.nextLine();

                        while (formatCheck == false)
                        {
                            System.out.println("Enter new information for the column.");
                            colDat = in.nextLine();

                            if (colNum == 11) {
                                try {
                                    LocalDate.parse(colDat);
                                    formatCheck = true;
                                } catch (Exception e) {
                                    System.out.println("Please use the format 'DD-MM-YYYY'");
                                }
                            }
                            else if (colNum == 12 && !colDat.matches("^[0-9]+.[0-9]+$"))
                            {
                                System.out.println("Format is 0.00");
                            }
                            else
                            {
                                formatCheck = true;
                            }
                        }
                        sp[colNum] = colDat;
                        line = "";

                        for (int i = 0; i < 11; i++)
                        {
                            //Here we add the separation for the CSVs
                            //So that we can save the file
                            //You can do "," but ", " adds back the space that was trimmed off, better for readability.
                            //We also check if its the last value on the line, and if so, don't print the ", "
                            if (i == 10)
                            {
                                line += sp[10].trim() + ", " + sp[11].trim() + ", " + sp[12].trim();
                            }
                            else
                            {
                                line += (sp[i].trim() + ", ");
                            }
                        }
                    }
                    finishedFile += (line + "\n");
                    line = buff.readLine();
                }
                file.close();

                if (uNameExists)
                {
                    try
                    {
                        FileWriter input = new FileWriter("members.csv");

                        input.write(finishedFile);

                        input.close();

                        System.out.println("Profile edited.");
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
                else
                {
                    System.out.println("Username not found");
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

    }

    private void membersList()
    {

        String[] columns;

        try
        {
            FileReader file = new FileReader("members.csv");
            BufferedReader buff = new BufferedReader(file);

            String line = buff.readLine();
            columns = line.split(",");
            line = buff.readLine();

            while (line != null)
            {
                String[] sp = line.split(",");

                //Prints the member list to console.
                for (int i = 0; i < 12; i++)
                {
                    if (i == 11)
                    {
                        //Sets it so the date is formatted as Day-Month-Year
                        //Default for LocalDate is Year-Month-Day
                        DateTimeFormatter form = DateTimeFormatter.ofPattern("dd-MM-yyyy");

                        //It then parses the date vale from the member list as the format.
                        LocalDate date = LocalDate.parse(sp[11].trim(), form);

                        //Then if formats it again into the "MEDIUM" style which makes it look like "8 Oct 1997"
                        String formDate = date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));

                        //Prints the result to console with the column name to look like "Date of Birth: 8 Oct 1997"
                        System.out.println(columns[i].trim() + ": " + formDate);
                    }
                    else if (i == 12)
                    {
                        //This else statement checks if it's the last column, and if so adds a new line at the end
                        //Specifically for splitting the members by set rather than split by line.
                        System.out.println(columns[12].trim() + ": " + sp[12] + "\n");
                    }
                    else
                    {
                        //Prints every other value that isn't 9 or 11.
                        System.out.println(columns[i].trim() + ": " + sp[i].trim());
                    }
                }
                System.out.println();
                line = buff.readLine();
            }
            file.close();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void createNewMembers()
    {

        //Self explanatory, but tracks the userInput as a variable
        String userInput;
        //For checking the format (used later)
        boolean formatCheck = false;
        boolean dupeChck = false;

        //Asks for input on member type
        System.out.println("Member type: (Casual, Full, Staff or Admin)");
        in.nextLine();
        userInput = in.nextLine().toLowerCase();
        //Bit of input validation so you need to insert a correct value in order to proceed.
        while (!(userInput.equals("casual") || userInput.equals("full") || userInput.equals("staff") || userInput.equals("admin")))
        {
            System.out.println("Enter a valid Member Type: (Casual, Full, Staff or Admin)");
            userInput = in.nextLine().toLowerCase();
        }


        //If Casual is entered it begins the process of adding a Casual user.
        if (userInput.equals("casual"))
        {
            //Creates a new instance of the CasualMember.
            CasualMember addCasual = new CasualMember();
            //Asks for the Forename.
            System.out.println("Enter the Forename: ");
            userInput = in.nextLine();
            //Just a little bit of validation
            //While the userInput is blank inform the user that the input is blank
            //Continue asking for a valid input until one is entered.
            while (userInput.isBlank())
            {
                System.out.println("Please enter data. String is blank.");
                System.out.println("Enter a valid Forename: ");
                userInput = in.nextLine();
            }
            addCasual.setForename(toTitleCase(userInput));

            System.out.println("Enter the Surname: ");
            userInput = in.nextLine();
            while (userInput.isBlank())
            {
                System.out.println("Please enter data. String is blank.");
                System.out.println("Enter a valid Surname: ");
                userInput = in.nextLine();
            }
            addCasual.setSurname(toTitleCase(userInput));

            //Sets the new users userID to what was returned from userIDHandler + 1
            //userIDHandler should always return the last userID in the file
            //Adding 1 to it to create a new one that won't conflict with existing data.
            addCasual.setUserID(userIDHandler() + 1);
            System.out.println("Enter the Username: ");
            userInput = in.nextLine();

            while (dupeChck == false)
            {
                if (!userInput.isBlank())
                {
                    try
                    {
                        FileReader file = new FileReader("members.csv");
                        BufferedReader buff = new BufferedReader(file);

                        String line = buff.readLine();

                        while (line != null)
                        {
                            String[] sp = line.split(",");

                            if(!userInput.equals(sp[4].trim()))
                            {
                                addCasual.setUsername(userInput);
                                dupeChck = true;
                            }
                            else if (userInput.equals(sp[4].trim()))
                            {

                                System.out.println("Username already exists, try another.");
                                System.out.println("Enter a Username: ");
                                userInput = in.nextLine();
                                dupeChck = false;
                            }
                            line = buff.readLine();

                        }
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                }
                else if (userInput.isBlank())
                {
                    System.out.println("Please enter data. String is blank.");
                    System.out.println("Enter a valid Username: ");
                    userInput = in.nextLine();
                    dupeChck = false;
                }
            }

            System.out.println("Enter the Password: ");
            userInput = in.nextLine();

            while (userInput.isBlank())
            {
                System.out.println("Please enter data. String is blank.");
                System.out.println("Enter a valid Surname: ");
                userInput = in.nextLine();
            }
            addCasual.setPassword(userInput);

            System.out.println("Enter the Contact Number: ");
            userInput = in.nextLine();
            while (userInput.isBlank())
            {
                System.out.println("Please enter data. String is blank.");
                System.out.println("Enter a valid Contact Number: ");
                userInput = in.nextLine();
            }
            addCasual.setContactNumber(userInput);

            System.out.println("Enter the e-mail address: ");
            userInput = in.nextLine();
            while (userInput.isBlank())
            {
                System.out.println("Please enter data. String is blank.");
                System.out.println("Enter a valid e-mail address: ");
                userInput = in.nextLine();
            }
            addCasual.setEmailAddress(userInput);

            System.out.println("Enter the Street Address: ");
            userInput = in.nextLine();
            while (userInput.isBlank())
            {
                System.out.println("Please enter data. String is blank.");
                System.out.println("Enter a valid Street Address: ");
                userInput = in.nextLine();
            }
            addCasual.setStreetAddress(toTitleCase(userInput));

            System.out.println("Enter the Town: ");
            userInput = in.nextLine();
            while (userInput.isBlank())
            {
                System.out.println("Please enter data. String is blank.");
                System.out.println("Enter a valid Town: ");
                userInput = in.nextLine();
            }
            addCasual.setTown(toTitleCase(userInput));

            System.out.println("Enter the PostCode: ");
            userInput = in.nextLine();
            while (userInput.isBlank())
            {
                System.out.println("Please enter data. String is blank.");
                System.out.println("Enter a valid PostCode: ");
                userInput = in.nextLine();
            }
            addCasual.setPostcode(userInput.toUpperCase());



            while (formatCheck == false)
            {

                try
                {
                    DateTimeFormatter form = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    System.out.println("Enter members date of birth (DD-MM-YYYY): ");
                    userInput = in.nextLine();
                    LocalDate date = LocalDate.parse(userInput, form);
                    String dateFormat = form.format(date);
                    addCasual.setDateOfBirth(dateFormat);
                    formatCheck = true;
                }
                catch (Exception e)
                {
                    System.out.println("Incorrect format - DD-MM-YYYY");
                }
            }
            addCasual.writeMember();
        }
        else if (userInput.equals("full"))
        {
            //Creates a new instance of the FullMember.
            FullMember addFull = new FullMember();

            System.out.println("Enter the Forename: ");
            userInput = in.nextLine();
            while (userInput.isBlank())
            {
                System.out.println("Please enter data. String is blank.");
                System.out.println("Enter a valid Forename: ");
                userInput = in.nextLine();
            }
            addFull.setForename(toTitleCase(userInput));

            System.out.println("Enter the Surname: ");
            userInput = in.nextLine();
            while (userInput.isBlank())
            {
                System.out.println("Please enter data. String is blank.");
                System.out.println("Enter a valid Surname: ");
                userInput = in.nextLine();
            }
            addFull.setSurname(toTitleCase(userInput));
            //Sets the new users userID to what was returned from userIDHandler + 1
            //userIDHandler should always return the last userID in the file
            //Adding 1 to it to create a new one that won't conflict with existing data.
            addFull.setUserID(userIDHandler() + 1);
            System.out.println("Enter the Username: ");
            userInput = in.nextLine();

            //This part is similar to the editMembers uNameExists.
            //Basically just checks if the userInput is the same as any existing username
            //If it is the same, it informs you - because users can't have similar usernames.
            while (dupeChck == false)
            {
                if (!userInput.isBlank())
                {
                    try
                    {
                        FileReader file = new FileReader("members.csv");
                        BufferedReader buff = new BufferedReader(file);

                        String line = buff.readLine();

                        while (line != null)
                        {
                            String[] sp = line.split(",");

                            if(!userInput.equals(sp[4].trim()))
                            {
                                addFull.setUsername(userInput);
                                dupeChck = true;
                            }
                            else if (userInput.equals(sp[4].trim()))
                            {

                                System.out.println("Username already exists, try another.");
                                System.out.println("Enter a Username: ");
                                userInput = in.nextLine();
                                dupeChck = false;
                            }
                            line = buff.readLine();

                        }
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                }
                else if (userInput.isBlank())
                {
                    System.out.println("Please enter data. String is blank.");
                    System.out.println("Enter a valid Username: ");
                    userInput = in.nextLine();
                    dupeChck = false;
                }
            }
            System.out.println("Enter the Password: ");
            userInput = in.nextLine();
            while (userInput.isBlank())
            {
                System.out.println("Please enter data. String is blank.");
                System.out.println("Enter a valid Password: ");
                userInput = in.nextLine();
            }
            addFull.setPassword(userInput);

            System.out.println("Enter the Contact Number: ");
            userInput = in.nextLine();
            while (userInput.isBlank())
            {
                System.out.println("Please enter data. String is blank.");
                System.out.println("Enter a valid Contact Number: ");
                userInput = in.nextLine();
            }
            addFull.setContactNumber(userInput);

            System.out.println("Enter the e-mail address: ");
            userInput = in.nextLine();
            while (userInput.isBlank())
            {
                System.out.println("Please enter data. String is blank.");
                System.out.println("Enter a valid e-mail address: ");
                userInput = in.nextLine();
            }
            addFull.setEmailAddress(userInput);

            System.out.println("Enter the Street Address: ");
            userInput = in.nextLine();
            while (userInput.isBlank())
            {
                System.out.println("Please enter data. String is blank.");
                System.out.println("Enter a valid Street Address: ");
                userInput = in.nextLine();
            }
            addFull.setStreetAddress(toTitleCase(userInput));

            System.out.println("Enter the Town: ");
            userInput = in.nextLine();
            while (userInput.isBlank())
            {
                System.out.println("Please enter data. String is blank.");
                System.out.println("Enter a valid Town: ");
                userInput = in.nextLine();
            }
            addFull.setTown(toTitleCase(userInput));

            System.out.println("Enter the PostCode: ");
            userInput = in.nextLine();
            while (userInput.isBlank())
            {
                System.out.println("Please enter data. String is blank.");
                System.out.println("Enter a valid PostCode: ");
                userInput = in.nextLine();
            }
            addFull.setPostcode(userInput.toUpperCase());

            while (formatCheck == false)
            {

                try
                {
                    DateTimeFormatter form = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    System.out.println("Enter members date of birth (DD-MM-YYYY): ");
                    userInput = in.nextLine();
                    LocalDate date = LocalDate.parse(userInput, form);
                    String dateFormat = form.format(date);
                    addFull.setDateOfBirth(dateFormat);
                    formatCheck = true;
                }
                catch (Exception e)
                {
                    System.out.println("Incorrect format - DD-MM-YYYY");
                }
            }
            addFull.writeMember();
        }
        else if (userInput.equals("staff"))
        {
            //Creates a new instance of the StaffMember.
            StaffMember addStaff = new StaffMember();

            System.out.println("Enter the Forename: ");
            userInput = in.nextLine();
            while (userInput.isBlank())
            {
                System.out.println("Please enter data. String is blank.");
                System.out.println("Enter a valid Forename: ");
                userInput = in.nextLine();
            }
            addStaff.setForename(toTitleCase(userInput));

            System.out.println("Enter the Surname: ");
            userInput = in.nextLine();
            while (userInput.isBlank())
            {
                System.out.println("Please enter data. String is blank.");
                System.out.println("Enter a valid Surname: ");
                userInput = in.nextLine();
            }
            addStaff.setSurname(toTitleCase(userInput));
            //Sets the new users userID to what was returned from userIDHandler + 1
            //userIDHandler should always return the last userID in the file
            //Adding 1 to it to create a new one that won't conflict with existing data.
            addStaff.setUserID(userIDHandler() + 1);
            System.out.println("Enter the Username: ");
            userInput = in.nextLine();

            //This part is similar to the editMembers uNameExists.
            //Basically just checks if the userInput is the same as any existing username
            //If it is the same, it informs you - because users can't have similar usernames.
            while (dupeChck == false)
            {
                if (!userInput.isBlank())
                {
                    try
                    {
                        FileReader file = new FileReader("members.csv");
                        BufferedReader buff = new BufferedReader(file);

                        String line = buff.readLine();

                        while (line != null)
                        {
                            String[] sp = line.split(",");

                            if(!userInput.equals(sp[4].trim()))
                            {
                                addStaff.setUsername(userInput);
                                dupeChck = true;
                            }
                            else if (userInput.equals(sp[4].trim()))
                            {

                                System.out.println("Username already exists, try another.");
                                System.out.println("Enter a Username: ");
                                userInput = in.nextLine();
                                dupeChck = false;
                            }
                            line = buff.readLine();

                        }
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                }
                else if (userInput.isBlank())
                {
                    System.out.println("Please enter data. String is blank.");
                    System.out.println("Enter a valid Username: ");
                    userInput = in.nextLine();
                    dupeChck = false;
                }
            }
            System.out.println("Enter the Password: ");
            userInput = in.nextLine();
            while (userInput.isBlank())
            {
                System.out.println("Please enter data. String is blank.");
                System.out.println("Enter a valid Password: ");
                userInput = in.nextLine();
            }
            addStaff.setPassword(userInput);

            System.out.println("Enter the Contact Number: ");
            userInput = in.nextLine();

            while (userInput.isBlank())
            {
                System.out.println("Please enter data. String is blank.");
                System.out.println("Enter a valid Contact Number: ");
                userInput = in.nextLine();
            }
            addStaff.setContactNumber(userInput);

            System.out.println("Enter the e-mail address: ");
            userInput = in.nextLine();
            while (userInput.isBlank())
            {
                System.out.println("Please enter data. String is blank.");
                System.out.println("Enter a valid e-mail address: ");
                userInput = in.nextLine();
            }
            addStaff.setEmailAddress(userInput);

            System.out.println("Enter the Street Address: ");
            userInput = in.nextLine();
            while (userInput.isBlank())
            {
                System.out.println("Please enter data. String is blank.");
                System.out.println("Enter a valid Street Address: ");
                userInput = in.nextLine();
            }
            addStaff.setStreetAddress(toTitleCase(userInput));

            System.out.println("Enter the Town: ");
            userInput = in.nextLine();
            while (userInput.isBlank())
            {
                System.out.println("Please enter data. String is blank.");
                System.out.println("Enter a valid Town: ");
                userInput = in.nextLine();
            }
            addStaff.setTown(toTitleCase(userInput));

            System.out.println("Enter the PostCode: ");
            userInput = in.nextLine();
            while (userInput.isBlank())
            {
                System.out.println("Please enter data. String is blank.");
                System.out.println("Enter a valid PostCode: ");
                userInput = in.nextLine();
            }
            addStaff.setPostcode(userInput.toUpperCase());


            while (formatCheck == false)
            {

                try
                {
                    DateTimeFormatter form = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    System.out.println("Enter members date of birth (DD-MM-YYYY): ");
                    userInput = in.nextLine();
                    LocalDate date = LocalDate.parse(userInput, form);
                    String dateFormat = form.format(date);
                    addStaff.setDateOfBirth(dateFormat);
                    formatCheck = true;
                }
                catch (Exception e)
                {
                    System.out.println("Incorrect format - DD-MM-YYYY");
                }
            }
            addStaff.writeMember();
        }
        else if (userInput.equals("admin"))
        {
            //Creates a new instance of the AdminMember.
            AdminMember addAdmin = new AdminMember();

            System.out.println("Enter the Forename: ");
            userInput = in.nextLine();
            while (userInput.isBlank())
            {
                System.out.println("Please enter data. String is blank.");
                System.out.println("Enter a valid Forename: ");
                userInput = in.nextLine();
            }
            addAdmin.setForename(toTitleCase(userInput));

            System.out.println("Enter the Surname: ");
            userInput = in.nextLine();
            while (userInput.isBlank())
            {
                System.out.println("Please enter data. String is blank.");
                System.out.println("Enter a valid Surname: ");
                userInput = in.nextLine();
            }
            addAdmin.setSurname(toTitleCase(userInput));
            //Sets the new users userID to what was returned from userIDHandler + 1
            //userIDHandler should always return the last userID in the file
            //Adding 1 to it to create a new one that won't conflict with existing data.
            addAdmin.setUserID(userIDHandler() + 1);
            System.out.println("Enter the Username: ");
            userInput = in.nextLine();

            //This part is similar to the editMembers uNameExists.
            //Basically just checks if the userInput is the same as any existing username
            //If it is the same, it informs you - because users can't have similar usernames.
            while (dupeChck == false)
            {
                if (!userInput.isBlank())
                {
                    try
                    {
                        FileReader file = new FileReader("members.csv");
                        BufferedReader buff = new BufferedReader(file);

                        String line = buff.readLine();

                        while (line != null)
                        {
                            String[] sp = line.split(",");

                            if(!userInput.equals(sp[4].trim()))
                            {
                                addAdmin.setUsername(userInput);
                                dupeChck = true;
                            }
                            else if (userInput.equals(sp[4].trim()))
                            {

                                System.out.println("Username already exists, try another.");
                                System.out.println("Enter a Username: ");
                                userInput = in.nextLine();
                                dupeChck = false;
                            }
                            line = buff.readLine();

                        }
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                }
                else if (userInput.isBlank())
                {
                    System.out.println("Please enter data. String is blank.");
                    System.out.println("Enter a valid Username: ");
                    userInput = in.nextLine();
                    dupeChck = false;
                }
            }
            System.out.println("Enter the Password: ");
            userInput = in.nextLine();
            while (userInput.isBlank())
            {
                System.out.println("Please enter data. String is blank.");
                System.out.println("Enter a valid Password: ");
                userInput = in.nextLine();
            }
            addAdmin.setPassword(userInput);

            System.out.println("Enter the Contact Number: ");
            userInput = in.nextLine();

            while (userInput.isBlank())
            {
                System.out.println("Please enter data. String is blank.");
                System.out.println("Enter a valid Contact Number: ");
                userInput = in.nextLine();
            }
            addAdmin.setContactNumber(userInput);

            System.out.println("Enter the e-mail address: ");
            userInput = in.nextLine();
            while (userInput.isBlank())
            {
                System.out.println("Please enter data. String is blank.");
                System.out.println("Enter a valid e-mail address: ");
                userInput = in.nextLine();
            }
            addAdmin.setEmailAddress(userInput);

            System.out.println("Enter the Street Address: ");
            userInput = in.nextLine();
            while (userInput.isBlank())
            {
                System.out.println("Please enter data. String is blank.");
                System.out.println("Enter a valid Street Address: ");
                userInput = in.nextLine();
            }
            addAdmin.setStreetAddress(toTitleCase(userInput));

            System.out.println("Enter the Town: ");
            userInput = in.nextLine();
            while (userInput.isBlank())
            {
                System.out.println("Please enter data. String is blank.");
                System.out.println("Enter a valid Town: ");
                userInput = in.nextLine();
            }
            addAdmin.setTown(toTitleCase(userInput));

            System.out.println("Enter the PostCode: ");
            userInput = in.nextLine();
            while (userInput.isBlank())
            {
                System.out.println("Please enter data. String is blank.");
                System.out.println("Enter a valid PostCode: ");
                userInput = in.nextLine();
            }
            addAdmin.setPostcode(userInput.toUpperCase());

            while (formatCheck == false)
            {

                try
                {
                    DateTimeFormatter form = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    System.out.println("Enter members date of birth (DD-MM-YYYY): ");
                    userInput = in.nextLine();
                    LocalDate date = LocalDate.parse(userInput, form);
                    String dateFormat = form.format(date);
                    addAdmin.setDateOfBirth(dateFormat);
                    formatCheck = true;
                }
                catch (Exception e)
                {
                    System.out.println("Incorrect format - DD-MM-YYYY");
                }
            }
            addAdmin.writeMember();
        }

    }

    private void loanRec()
    {
        //Tracks the loaners username.
        String loanerUN = "";
        //Tracks the stock title.
        String title = "";
        //Prepares the writing of the full final media file.
        String finishedMedFile = "";
        //Prepares the writing of the full final member file.
        String finishedMemFile = "";
        //This the yes/no result for fine payments.
        String finePay = "";
        //Checks the current borrow.
        int curBorrow = 0;
        //Checks the borrowamount based on member type.
        int borrowAmount = 0;
        //Checks if the loaner was found. Since a member has to exist for them to borrow.
        boolean loanerChck = false;
        //Checks if the item was found. Since the item has to exist for it to be borrowed.
        boolean itemChck = false;
        String stockID = "";
        DateTimeFormatter form = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.now();
        String nowDate = date.format(form);

        //Asks for the title of the sought after item
        System.out.println("Enter the Title you wish to loan.");
        //Makes the variable title equal the user input.
        in.nextLine();
        title = in.nextLine();
        while (title.isBlank())
        {
            System.out.println("Please enter data. String is blank.");
            System.out.println("Enter a title for loaning: ");
            title = in.nextLine();
        }
        //Asks for the username of the borrower.
        System.out.println("Enter the username of the member that wishes to loan.");
        //Makes the username variable equal the user input.
        loanerUN = in.nextLine();
        while (loanerUN.isBlank())
        {
            System.out.println("Please enter data. String is blank.");
            System.out.println("Enter a username of the user loaning: ");
            loanerUN = in.nextLine();
        }

        try
        {
            FileReader file = new FileReader("members.csv");
            BufferedReader buff = new BufferedReader(file);

            String line = buff.readLine();
            finishedMemFile += line + "\n";
            line = buff.readLine();

            while (line != null)
            {
                String[] memColumn = line.split(", ");

                if (memColumn[3].trim().equals(loanerUN))
                {
                    loanerChck = true;

                    //Sets the borrow amount based on member type.
                    if(memColumn[0].trim().equals("Full"))
                    {
                        borrowAmount = 4;
                    }
                    else if (memColumn[0].trim().equals("Staff"))
                    {
                        borrowAmount = 6;
                    }
                    else
                    {
                        System.out.println("User is a Casual user, they cannot loan items.");
                        memRecMenu();
                    }
                }

                //Checks if the fine is about 0.
                if (memColumn[4].trim().equals(loanerUN) && Double.parseDouble(memColumn[12].trim()) > 0)
                {
                    //Informs the user of their outstanding fine, and how much is due.
                    System.out.println("Outstanding fine: " + memColumn[12].trim());

                    //A while loop to check if the fine has or hasn't been paid
                    while (!(finePay.equals("yes") || finePay.equals("no")))
                    {
                        //Setting the vale of finePay.
                        System.out.println("Has the fine been paid? Yes/No");
                        finePay = in.next().toLowerCase();
                    }

                    //These check if the fines have been paid, and if they haven't it informs the user of that and doesn't allow them to borrow.
                    if (finePay.equals("no"))
                    {
                        System.out.println("Outstanding fine, pay fine first in order to borrow.");
                        memRecMenu();
                    }
                    else
                    {
                        //If this fine has been paid
                        //Set the Fine
                        line = "";
                        memColumn[12] = "0.00";

                        for (int i = 0; i <= 11; i++)
                        {
                            line += (memColumn[i].trim() + ", ");

                            if (i == 11)
                            {
                                line += memColumn[12].trim();
                            }
                        }
                    }

                }

                finishedMemFile += line + "\n";
                line = buff.readLine();
            }
            file.close();

            //If loanerChck isn't found
            if (!loanerChck)
            {
                //Display a message to the user telling them the username doesn't exist.
                System.out.println("Username not found. Might be a typo or they don't exist.");
                memRecMenu();
            }

            //If the fine has been paid.
            if (finePay.equals("yes"))
            {
                //Write the finishedMemFile, overwriting members.csv - as it stores previous info.
                Login.getMenu().getStockMenu().writeFile("members.csv", finishedMemFile);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        try
        {
            FileReader file = new FileReader("media.csv");
            BufferedReader buff = new BufferedReader(file);

            String line = buff.readLine();
            finishedMedFile = line + "\n";
            line = buff.readLine();

            while (line != null)
            {
                String[] sp = line.split(",");

                if (sp[5].trim().equals(loanerUN))
                {
                    curBorrow++;
                }

                if ((sp[5].trim().equals(loanerUN)) && (ChronoUnit.DAYS.between(LocalDate.parse(sp[6].trim(), form), LocalDate.parse(nowDate, form)) > 10))
                {
                    System.out.println("Member currently has overdue items.");
                    memRecMenu();
                }

                if (sp[1].trim().equals(title) && !sp[7].trim().equals(loanerUN) && !sp[7].equals("N/A") && ChronoUnit.DAYS.between(LocalDate.parse(sp[8].trim(), form), LocalDate.parse(nowDate, form)) >= 0)
                {
                    System.out.println("Item is not in stock.");
                    memRecMenu();
                }

                if (sp[2].trim().equals(title))
                {
                    itemChck = true;

                    stockID = sp[1].trim();

                    line = "";
                    sp[5] = loanerUN;
                    sp[6] = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    sp[7] = "N/A";
                    sp[8] = "N/A";

                    for (int i = 0; i <= 20; i++)
                    {
                        line += (sp[i].trim() + ", ");

                        if (i == 20)
                        {
                            line += sp[21].trim();                        }
                    }
                }
                finishedMedFile += line + "\n";
                line = buff.readLine();
            }
            file.close();

            //If the item wasn't found, inform the user.
            if (!itemChck)
            {
                System.out.println("Item was not found.");
                memRecMenu();
            }

            //If the users borrow amount has been reached
            else if (! (curBorrow < borrowAmount))
            {
                //Informing them that they cannot borrow any more items.
                System.out.println("Borrow quota reached.");
                memRecMenu();
            }

            //If the user still has borrows and the item is available.
            else if (curBorrow < borrowAmount && itemChck == true)
            {
                //Write the loan to the media file.
                Login.getMenu().getStockMenu().writeFile("media.csv", finishedMedFile);
                //Write the loan to the loans file.
                writeLoan(loanerUN, stockID, title);
                //Display text confirming that it has successfully been loaned.
                System.out.println("Item has been successfully loaned.");
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


    }

    //Similar to the above method, except for returning loans.
    private void loanReturn()
    {
        String loanerUN = "";
        String title = "";
        String stockID = "";
        //For storing the media file for writing later.
        String finishedMedFile = "";
        //For storing the member file for writing later.
        String finishedMemFile = "";
        //This boolean tracks if the item exists.
        boolean itemChck = false;
        //This boolean tracks if the item has been returned.
        boolean returnChck = false;
        double fine = 0.00;
        String payFine = "";
        DateTimeFormatter form = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        System.out.println("Enter the title of the item you're returning:");
        //Stores the user input in the variable title.
        in.nextLine();
        title = in.nextLine();
        while (title.isBlank())
        {
            System.out.println("Please enter data. String is blank.");
            System.out.println("Enter a title you're returning: ");
            title = in.nextLine();
        }
        System.out.println("Enter the username of the member returning said item:");
        loanerUN = in.nextLine();
        while (loanerUN.isBlank())
        {
            System.out.println("Please enter data. String is blank.");
            System.out.println("Enter the username of the person returning an item: ");
            loanerUN = in.nextLine();
        }

        try
        {
            FileReader file = new FileReader("media.csv");
            BufferedReader buff = new BufferedReader(file);

            String line = buff.readLine();
            finishedMedFile = line + "\n";
            line = buff.readLine();

            while (line != null)
            {
                String[] sp = line.split(",");

                //If the 2nd value of the line in media.csv
                //Is equal to the variable title
                if (sp[2].trim().equals(title))
                {
                    //Set the boolean itemChck to true. As the item has been found.
                    itemChck = true;

                    //If the 5th value is equal to the username.
                    if (sp[5].trim().equals(loanerUN))
                    {
                        //Set returnChck to true
                        returnChck = true;

                        //This is for calculating the days between
                        //If the days between the loanDate and returnDate is more than 10 days
                        if (ChronoUnit.DAYS.between(LocalDate.parse(sp[6].trim(), form), LocalDate.now()) > 10)
                        {
                            //Apply a fine of 0.50 per-day.
                            fine = (ChronoUnit.DAYS.between(LocalDate.parse(sp[6].trim(), form), LocalDate.now()) - 10) * 0.5;

                            //Display to user that they have an outstanding fine of whatever amount
                            System.out.println("User has an outstanding fine of: " + fine);

                            //Then while the payFine variable isn't equal to yes or no
                            while (!payFine.equals("yes") && !payFine.equals("no"))
                            {
                                //Print the question and ask for input
                                System.out.println("Has the fine been paid? (yes/no)");
                                payFine = in.next().toLowerCase();
                            }

                            //If payFine equals no
                            if (payFine.equals("no"))
                            {
                                try
                                {
                                    FileReader payNo = new FileReader("members.csv");
                                    BufferedReader buffNo = new BufferedReader(payNo);

                                    String lineNo = buffNo.readLine();
                                    finishedMemFile = line + "\n";
                                    lineNo = buffNo.readLine();

                                    while (lineNo != null)
                                    {
                                        String[] spM = lineNo.split(",");

                                        if (spM[4].trim().equals(loanerUN))
                                        {
                                            //Adds the fine to the field on the file
                                            fine += Double.parseDouble(spM[12]);
                                            spM[12] = Double.toString(fine);

                                            lineNo = "";

                                            for (int i = 0; i < 12; i++)
                                            {
                                                //Creates the line by adding back the split content and using a ", " to neatly split them apart.
                                                lineNo += (spM[i] + ", ");
                                            }
                                        }
                                        finishedMemFile += lineNo + "\n";
                                        lineNo = buffNo.readLine();
                                    }
                                    payNo.close();
                                    //Calls the method to write the file properly.
                                    Login.getMenu().getStockMenu().writeFile("members.csv", finishedMemFile);
                                }
                                catch (IOException e)
                                {
                                    e.printStackTrace();
                                }
                                System.out.println("User has incurred a fine.");
                            }
                        }

                        //Replaces the 5th and 6th part of the media.csv file with "N/A"
                        //These values are the Loaner Username and Loaner Date fields.
                        sp[5] = "N/A";
                        sp[6] = "N/A";
                        line = "";
                        for (int i = 0; i < 21; i++)
                        {
                            line += (sp[i] + ", ");
                        }

                        System.out.println("Returned successfully.");

                    }
                    else
                    {
                        System.out.println("Item is not loaned to this members username.");
                    }
                }
                finishedMedFile += line + "\n";
                line = buff.readLine();
            }
            file.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        if (!itemChck)
        {
            System.out.println("Item was not found.");
        }

        if (returnChck)
        {
            System.out.println("Retchck");
            Login.getMenu().getStockMenu().writeFile("media.csv", finishedMedFile);
            updateLoan(loanerUN, title);
        }




    }


    //Similar to the myLoans method with a few differences
    //Differences being: Doesn't search for a Username
    //Just prints all the data in the loans.csv file.
    private void allLoans()
    {

        try
        {
            FileReader file = new FileReader("loans.csv");
            BufferedReader buff = new BufferedReader(file);

            String line = buff.readLine();
            line = buff.readLine();

            while (line !=null)
            {
                //This prints the contents of the loans.csv
                String[] sp = line.split(",");


                //If statements are mostly just for the purpose of neat display
                //If the length of the array is 4, print the data and add a space between the last value and the next line.
                if (sp.length == 4)
                {
                    //Prints all the loan data available for items that haven't been returned yet.
                    System.out.println("Username: " + sp[0]);
                    System.out.println("Stock ID: " + sp[1]);
                    System.out.println("Title: " + sp[2]);
                    System.out.println("Loan Date: " + sp[3] + "\n");
                }

                //Same as above, if the array is 4, print all the data but add a space at the Return Date rather than the Loan Date.
                if (sp.length == 5)
                {
                    //Prints all the loan data including the return date.
                    System.out.println("Username: " + sp[0]);
                    System.out.println("Stock ID: " + sp[1]);
                    System.out.println("Title: " + sp[2]);
                    System.out.println("Loan Date: " + sp[3]);
                    System.out.println("Return Date: " + sp[4] + "\n");
                }
                line = buff.readLine();
            }
            System.out.println();
            file.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    private void myLoans()
    {

        try
        {
            FileReader file = new FileReader("loans.csv");
            BufferedReader buff = new BufferedReader(file);

            String line = buff.readLine();
            line = buff.readLine();

            while (line != null)
            {
                String[] sp = line.split(",");

                //This checks the loans.csv for the existence of the currently Logged in users username.
                //If the username exists it means they have a record of loans.
                if (sp[0].trim().equals(Login.getMembers().get(Login.getUserIndex()).getUsername()))
                {
                    //Display the contents of each line in Loans.csv that have the username.
                    System.out.println("Username: " + sp[0]);
                    System.out.println("Stock ID: " + sp[1]);
                    System.out.println("Title: " + sp[2]);
                    System.out.println("Loan Date: " + sp[3]);

                    //If sp contains 5 values it means the last value is a return date.
                    //Print the return date - if it exists.
                    if (sp.length == 5)
                    {
                        System.out.println("Return Date: " + sp[4]);
                    }
                }
                line = buff.readLine();
            }
            System.out.println();
            file.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    //This is for returning userIDs of newly created users
    //Does this by reading the members.csv
    //Then updating the userID to be equal to that of the last added users userID
    private int userIDHandler() {
        //Initialises the variable userID with the value of 0, this is changed later.
        int userID = 0;

        try {

            //Nothing that hasn't been done before
            //Sets up the filereader, and bufferedreader
            FileReader file = new FileReader("members.csv");
            BufferedReader buff = new BufferedReader(file);

            //Reads the first line of the file, which is just the columns.
            //Then reads the next line.
            String line = buff.readLine();
            line = buff.readLine();

            while (line != null) //Again just a while loop that iterates through the file until it has no more lines.
            {
                //Splits each line into an array using comma as a delimiter.
                String[] sp = line.split(",");
                //This then updates the userID variable with the value found in the 3rd index.
                //This should be the userID value when split, trimming it to remove any spaces.
                userID = Integer.parseInt(sp[3].trim());

                //Then we continue to read the next line = loop until no more lines.
                line = buff.readLine();
            }
            //Again closing the file stream to open it up for any usages elsewhere
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        //Returning the userID from the last user for us to update.
        return userID;
    }

    private void writeLoan(String loanerUN, String stockID, String title)
    {
        String finishedLoanFile = "";

        try
        {
            FileReader file = new FileReader("loans.csv");
            BufferedReader buff = new BufferedReader(file);

            String line = buff.readLine();
            finishedLoanFile = line + "\n";
            line = buff.readLine();

            while(line != null)
            {
                finishedLoanFile += line + "\n";
                line = buff.readLine();
            }
            file.close();

            finishedLoanFile += loanerUN.trim() + ", " + stockID + ", " + title + ", " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        try
        {
            File file = new File("loans.csv");

            FileWriter input = new FileWriter(file);

            input.write(finishedLoanFile);

            input.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public void updateLoan(String loanerUN, String title)
    {
        String finishedLoanFile = "";

        try
        {
            FileReader file = new FileReader("loans.csv");
            BufferedReader buff = new BufferedReader(file);

            String line = buff.readLine();
            finishedLoanFile = line + "\n";
            line = buff.readLine();

            while (line != null)
            {
                String[] sp = line.split(",");

                if (sp[0].trim().equals(loanerUN) && sp[2].trim().equals(title))
                {
                    line = "";

                    for (int i = 0; i < 4; i++)
                    {
                        line += sp[i].trim() + ", ";
                    }

                    line += ", " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    System.out.println("Here");
                }

                finishedLoanFile += line + "\n";
                line = buff.readLine();
            }
            file.close();


        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        try
        {
            File file = new File("loans.csv");

            FileWriter input = new FileWriter(file);

            input.write(finishedLoanFile);

            input.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    private static String toTitleCase(String str)
    {
        if(str == null || str.isEmpty())
        {
            return "";
        }
        if (str.length() == 1)
        {
            return str.toUpperCase();
        }

        String[] sp = str.split(" ");

        StringBuilder sb = new StringBuilder(str.length());

        for (String part : sp)
        {
            if (part.length() > 1)
            {
                sb.append(part.substring(0, 1).toUpperCase()).append(part.substring(1).toLowerCase());
            }
            else
            {
                sb.append(part.toUpperCase());
            }
            sb.append(" ");
        }
        return sb.toString().trim();
    }

}
