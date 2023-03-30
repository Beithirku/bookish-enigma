//Author: Kyle Taylor
//Date: 17th Jan 2022
//Completed: 26th Jan 2022.

package Stock;

import javax.annotation.processing.Filer;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class StockMenu {

    //A lot of the stockMenu is similar to the MemberMenu
    private Scanner in = new Scanner (System.in);

    public void stockRecMenu()
    {
        //If the user is an instance of Casual Member, show the relevant menu.
        if (Login.getMembers().get(Login.getUserIndex()) instanceof CasualMember)
        {
            casualStock();
        }
        //Else if the user is an instance of Admin Member, show the admins menu.
        else if (Login.getMembers().get(Login.getUserIndex()) instanceof AdminMember)
        {
            adminStock();
        }
        //Else the user is an instance of Staff Member, show the relevant menu.
        else if (Login.getMembers().get(Login.getUserIndex()) instanceof StaffMember)
        {
            staffStock();
        }
        //Else the user is an instance of Standard Member, show the relevant menu.
        else if (Login.getMembers().get(Login.getUserIndex()) instanceof FullMember)
        {
            standardStock();
        }

    }

    private void casualStock()
    {
        String menuItem;
        boolean quit = false;

        do
        {

            System.out.println("Casual Stock Menu");
            System.out.println("1: View current stock.");
            System.out.println("2: Search stock by title.");
            System.out.println("0: Return to main menu.");

            menuItem = in.next();

            switch (menuItem)
            {
                case "1":
                    curInventory();
                    break;
                case "2":
                    searchInv();
                    break;
                case "0":
                    quit = true;
                    System.out.println();
                    Login.getMenu().menu();
                    break;
                default:
                    System.out.println("\n Insert only 0 to 2. \n");
            }
        } while (!quit);
        in.close();
    }

    private void adminStock()
    {
        String menuItem;
        boolean quit = false;

        do
        {

            System.out.println("Admin Stock Menu");
            System.out.println("1: View current stock.");
            System.out.println("2: Search stock by title.");
            System.out.println("3: Reserve a stock item.");
            System.out.println("4: Create new stock item.");
            System.out.println("5: Edit a stock item.");
            System.out.println("0: Return to main menu.");

            menuItem = in.next();

            switch (menuItem)
            {
                case "1":
                    curInventory();
                    break;
                case "2":
                    searchInv();
                    break;
                case "3":
                    reserveStock();
                    break;
                case "4":
                    createNewStock();
                    break;
                case "5":
                    editStock();
                    break;
                case "0":
                    quit = true;
                    System.out.println();
                    Login.getMenu().menu();
                    break;
                default:
                    System.out.println("\n Insert only 0 to 5. \n");
            }
        } while (!quit);
        in.close();

    }

    private void staffStock()
    {
        String menuItem;
        boolean quit = false;

        do
        {

            System.out.println("Staff Stock Menu");
            System.out.println("1: View current stock.");
            System.out.println("2: Search stock by title.");
            System.out.println("3: Reserve a stock item.");
            System.out.println("4: Create new stock item.");
            System.out.println("5: Edit a stock item.");
            System.out.println("0: Return to main menu.");

            menuItem = in.next();

            switch (menuItem)
            {
                case "1":
                    curInventory();
                    break;
                case "2":
                    searchInv();
                    break;
                case "3":
                    reserveStock();
                    break;
                case "4":
                    createNewStock();
                    break;
                case "5":
                    editStock();
                    break;
                case "0":
                    quit = true;
                    System.out.println();
                    Login.getMenu().menu();
                    break;
                default:
                    System.out.println("\n Insert only 0 to 5. \n");
            }
        } while (!quit);
        in.close();

    }

    private void standardStock()
    {
        String menuItem;
        boolean quit = false;

        do
        {

            System.out.println("Standard Stock Menu");
            System.out.println("1: View current stock.");
            System.out.println("2: Search stock by title.");
            System.out.println("3: Reserve a stock item.");
            System.out.println("0: Return to main menu.");

            menuItem = in.next();

            switch (menuItem)
            {
                case "1":
                    curInventory();
                    break;
                case "2":
                    searchInv();
                    break;
                case "3":
                    reserveStock();
                    break;
                case "0":
                    quit = true;
                    System.out.println();
                    Login.getMenu().menu();
                    break;
                default:
                    System.out.println("\n Insert only 0 to 3. \n");
            }
        } while (!quit);
        in.close();

    }

    private void curInventory()
    {
        String[] columns;

        try
        {
            FileReader file = new FileReader("media.csv");
            BufferedReader buff = new BufferedReader(file);

            String line = buff.readLine();

            columns = line.split(",");

            line = buff.readLine();

            while (line != null)
            {
                String[] colDat = line.split(",");

                for (int i = 0; i < 22; i++)
                {
                    //If the user is an admin
                    if (Login.getMembers().get(Login.getUserIndex()).getMemberType().equals("Admin"))
                    {
                        //Remove any value that is N/A in the file.
                        if (! colDat[i].trim().equals("N/A"))
                        {

                            //If i == 3 that means it's the price value
                            if (i == 3)
                            {
                                //Add the poundsign before the price and print out.
                                System.out.println(columns[3].trim() + ": £" + colDat[3].trim());
                            }
                            //Else if i is 17, it's the runtime
                            else if (i == 17)
                            {
                                //Add the word "minutes" to the end.
                                System.out.println(columns[i].trim() + ": " + colDat[i].trim() + " minutes.");
                            }
                            //Else print everything out normally.
                            else
                            {
                                System.out.println(columns[i].trim() + ": " + colDat[i].trim());
                            }
                        }
                    }
                    //Else the user isn't an admin.
                    else
                    {
                        //Remove any data that equals N/A and don't print loan related information.
                        if (! colDat[i].trim().equals("N/A") && i != 5 && i != 6 && i != 7 && i != 8)
                        {
                            //If i == 3 that means it's the price value
                            if (i == 3)
                            {
                                //Add the poundsign before the price and print out.
                                System.out.println(columns[3].trim() + ": £" + colDat[3].trim());
                            }
                            //Else if i is 17, it's the runtime
                            else if (i == 17)
                            {
                                //Add the word "minutes" to the end.
                                System.out.println(columns[i].trim() + ": " + colDat[i].trim() + " minutes.");
                            }
                            //Else print everything out normally.
                            else
                            {
                            System.out.println(columns[i].trim() + ": " + colDat[i].trim());
                            }
                        }
                    }
                }
                //Add a space between each dataset
                System.out.println();
                //Read a new line
                line = buff.readLine();
            }
            //Close the file stream.
            file.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    private void searchInv()
    {
        String title;
        String[] columns;
        boolean found = false;

        //Asks the user to insert a title, or partial title to search.
        System.out.println("Enter the title for the media you're searching for:");
        title = in.next();

        try
        {
            FileReader file = new FileReader("media.csv");
            BufferedReader buff = new BufferedReader(file);

            String line = buff.readLine();
            columns = line.split(",");
            line = buff.readLine();

            while (line != null)
            {
                String[] sp = line.split(",");

                //This searches through the media.csv file for anything that contains whatever the user typed
                //So Bill will return Bill & Ted, without having to type the full title.
                if (sp[2].toLowerCase().contains(title.toLowerCase()))
                {
                    found = true;

                    //Then iterate through the array which should contain 22 indexes.
                    for (int i = 0; i < 22; i++)
                    {
                        //If the user is an admin
                        if (Login.getMembers().get(Login.getUserIndex()).getMemberType().equals("Admin"))
                        {
                            //Remove any value that is N/A in the file.
                            if (! sp[i].trim().equals("N/A"))
                            {

                                //If i == 3 that means it's the price value
                                if (i == 3)
                                {
                                    //Add the poundsign before the price and print out.
                                    System.out.println(columns[3].trim() + ": £" + sp[3].trim());
                                }
                                //Else if i is 17, it's the runtime
                                else if (i == 17)
                                {
                                    //Add the word "minutes" to the end.
                                    System.out.println(columns[i].trim() + ": " + sp[i].trim() + " minutes.");
                                }
                                //Else print everything out normally.
                                else
                                {
                                    System.out.println(columns[i].trim() + ": " + sp[i].trim());
                                }
                            }
                        }
                        //Else the user isn't an admin.
                        else
                        {
                            //Remove any data that equals N/A and don't print loan related information.
                            if (! sp[i].trim().equals("N/A") && i != 5 && i != 6 && i != 7 && i != 8)
                            {
                                //If i == 3 that means it's the price value
                                if (i == 3)
                                {
                                    //Add the poundsign before the price and print out.
                                    System.out.println(columns[3].trim() + ": £" + sp[3].trim());
                                }
                                //Else if i is 17, it's the runtime
                                else if (i == 17)
                                {
                                    //Add the word "minutes" to the end.
                                    System.out.println(columns[i].trim() + ": " + sp[i].trim() + " minutes.");
                                }
                                //Else print everything out normally.
                                else
                                {
                                    System.out.println(columns[i].trim() + ": " + sp[i].trim());
                                }
                            }
                        }
                    }
                    //Add a space between each dataset
                    System.out.println();
                }

                //Read a new line
                line = buff.readLine();
            }
            file.close();
            if (!found)
            {
                //Inform the user that an item matching the title they searched wasn't found.
                System.out.println(title + " was not found on the system. It likely doesn't exist or a typing error occurred");
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void reserveStock()
    {
        curInventory();
        int stockID = 0;
        String userInput;
        String finishedMedFile = "";
        boolean found = false;
        boolean resChck = false;
        String finalResID = "";
        String finalResTitle = "";

        //Asks for the ID of the stock they wish to reserve, this is why curInventory() is called at the start
        //To display all the current stock.
        System.out.println("Enter the Stock ID of the item you wish to reserve.");
        stockID = Integer.parseInt(in.next());

        try
        {
            FileReader file = new FileReader("media.csv");
            BufferedReader buff = new BufferedReader(file);

            String line = buff.readLine();
            finishedMedFile = line + "\n";
            line = buff.readLine();
            //This is just a formatter to keep things consistent, and because LocalDate uses the default value of YYYY-MM-DD
            DateTimeFormatter form = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate date = LocalDate.now();
            String nowDate = form.format(date);

            while (line != null)
            {
                String[] columns = line.split(",");

                //If the username is found to have a reserved item, remove it from their reservations.
                //The ! columns[1]~~.equals(stockID) is checking if the item that they previously reserved matches the one they searched for.
                if (columns[7].trim().equals(Login.getMembers().get(Login.getUserIndex()).getUsername()) && ! columns[1].trim().equals(Integer.toString(stockID))) {
                    columns[7] = "N/A";
                    columns[8] = "N/A";

                    finalResID = columns[1].trim();
                    finalResTitle = columns[2].trim();

                    line = "";

                    for (int i = 0; i < 22; i++)
                    {
                        line += (columns[i] + ", ");
                    }
                }

                //Checking if the item is found by iterating through the file for the stock ID.
                if (columns[1].trim().equals(Integer.toString(stockID)))
                {
                    //Make the found boolean = true, because the item was found
                    found = true;

                    //Check the loan date of the item.
                    if (columns[6].trim().equals("N/A"))
                    {
                        //Inform the user that it cannot be reserved, but can be loaned.
                        System.out.println("Item cannot be reserved. Loan it instead.");
                    }
                    //Check if the item has been loaned for more than 10 days, if so then it is overdue and cannot be loaned.
                    else if ((LocalDate.parse(columns[6].trim(), form).compareTo(LocalDate.now().minusDays(11))) < 0)
                    {
                        System.out.println("Item is currently overdue. Check again at a later date.");
                    }
                    //Else if the item isn't reserved, or the last reserve date is less than the current date.
                    else if (columns[8].trim().equals("N/A") || ((LocalDate.parse(columns[8].trim(), form).compareTo(LocalDate.now())) < 0))
                    {
                        //Item can be reserved.
                        resChck = true;

                        //Make the reservation Username equal the current user.
                        columns[7] = Login.getMembers().get(Login.getUserIndex()).getUsername();
                        //Make the return date equal to the current date + 11
                        columns[8] = (LocalDate.now().plusDays(11)).format(form);
                        line = "";

                        //Recreate the lines with the separator ", "
                        for (int i = 0; i < 22; i++)
                        {
                            line += (columns[i] + ", ");
                        }

                        //Inform the user that the item has been reserved until the given date.
                        System.out.println("Item: " + columns[2] + " was reserved for: " + columns[8]);

                        //This checks if the user has anything currently reserved
                        //If they do it unreserves the item as you can only reserve one item at a time.
                        if (! finalResTitle.equals(""))
                        {
                            System.out.println(finalResTitle + " has been unreserved.");
                        }
                    }
                    else
                    {
                        //Display that the item is currently reserved.
                        System.out.println("Item is currently reserved.");
                    }
                }
                finishedMedFile += line + "\n";
                line = buff.readLine();
            }
            file.close();

            if (!found)
            {
                //Inform the user that the ID couldn't be found.
                System.out.println("Stock ID: " + stockID + " could not be found." + "\n");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        if (resChck == true)
        {
            //If resChck is true it means the item is successfully reserved, so write the file
            writeFile("media.csv", finishedMedFile);
        }
    }

    private void createNewStock()
    {
        String mediaType;
        String fixMedType;
        int stockID = 0;
        String addMedia = "";
        boolean valChck = false;
        int valInt;
        double valDouble;
        LocalDate valDate;
        DateTimeFormatter form = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String finishedStockFile = "";

        try
        {
            FileReader file = new FileReader("media.csv");
            BufferedReader buff = new BufferedReader(file);

            String line = buff.readLine();
            finishedStockFile = line + "\n";
            line = buff.readLine();

            while (line != null)
            {
                finishedStockFile += line + "\n";
                String[] columns = line.split(",");
                stockID = Integer.parseInt(columns[1].trim());

                line = buff.readLine();
            }
            file.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        //Ask the user for an input of media type.
        System.out.println("Enter the media type: (Book, Journal, Video or Compact Disc)");
        in.nextLine();
        //Convert the media type to lowercase for easier validation.
        mediaType = in.nextLine().toLowerCase();

        while (!(mediaType.equals("book") || mediaType.equals("journal") || mediaType.equals("video") || mediaType.equals("compact disc")))
        {
            System.out.println("Enter a valid media type: (Book, Journal, Video or Compact Disc)");
            mediaType = in.nextLine().toLowerCase();
        }

        if (mediaType.equals("book"))
        {
            //Since mediaType gets passed in as lowercase, we convert it to titlecase using this method.
            fixMedType = toTitleCase(mediaType);
            addMedia += fixMedType + ", ";
            //Stock ID becomes the previous stock ID + 1
            addMedia += (stockID + 1) + ", ";

            //Ask for the title of the book
            System.out.println("Enter the title of the Book: ");
            //Convert the title to titleCase for neater formatting
            //Also gets rid of any mistakes in files, wouldn't want sherlock Holmes.
            addMedia += toTitleCase(in.nextLine()) + ", ";

            while (valChck == false)
            {
                //Asks the user for the price of the book.
                System.out.println("Enter the price of the book: (e.g 8.99)");

                try
                {
                    //Check if the price is a double
                    valDouble = Double.parseDouble(in.nextLine());
                    addMedia += valDouble + ", ";
                    //If so valChck becomes true since it is a valid format.
                    valChck = true;
                }
                catch (Exception e)
                {
                    //If not it throws this and asks again for input.
                    System.out.println("Incorrect format, insert it in full correct format is '8.99'");
                }
            }
            //Reset valChck for later use.
            valChck = false;

            //Ask for the publisher.
            System.out.println("Enter the publisher of the book: ");
            //Put the spacing in-between the publisher and the next data value.
            addMedia += toTitleCase(in.nextLine()) + ", N/A, N/A, N/A, N/A, ";

            while (valChck == false)
            {
                //Ask for the ISBN
                System.out.println("Enter the books ISBN");

                try
                {
                    //Check that the input is an integer.
                    valInt = Integer.parseInt(in.nextLine());
                    addMedia += valInt + ", ";
                    valChck = true;
                }
                catch (Exception e)
                {
                    System.out.println("Incorrect format. Correct ISBN format is '1593075006'");
                }
            }
            valChck = false;

            System.out.println("Enter the author of the books name: ");
            addMedia += toTitleCase(in.nextLine()) + ", ";
            System.out.println("Enter the books genre: ");
            addMedia += toTitleCase(in.nextLine()) + ", ";

            while (valChck == false)
            {
                System.out.println("Enter the books number of pages: ");
                try
                {
                    valInt = Integer.parseInt(in.nextLine());
                    addMedia += valInt + ", N/A, N/A, N/A, N/A, N/A, N/A, N/A, N/A, N/A";
                    valChck = true;
                }
                catch (Exception e)
                {
                    System.out.println("Incorrect format. Correct format is '256'");
                }
            }
            valChck = false;

            finishedStockFile += addMedia;

            writeFile("media.csv", finishedStockFile);
        }

        if (mediaType.equals("journal"))
        {
            //Since mediaType gets passed in as lowercase, we convert it to titlecase using this method.
            fixMedType = toTitleCase(mediaType);
            addMedia += fixMedType + ", ";
            addMedia += (stockID + 1) + ", ";

            System.out.println("Enter the title of the Journal: ");
            addMedia += toTitleCase(in.nextLine()) + ", ";

            while (valChck == false)
            {
                System.out.println("Enter the price of the Journal: (e.g 8.99)");

                try
                {
                    valDouble = Double.parseDouble(in.nextLine());
                    addMedia += valDouble + ", ";
                    valChck = true;
                }
                catch (Exception e)
                {
                    System.out.println("Incorrect format, insert it in full correct format is '8.99'");
                }
            }
            valChck = false;

            System.out.println("Enter the publisher of the Journal: ");
            addMedia += toTitleCase(in.nextLine()) + ", N/A, N/A, N/A, N/A, N/A, N/A, N/A, ";

            while (valChck == false)
            {
                System.out.println("Enter the books number of pages: ");
                try
                {
                    valInt = Integer.parseInt(in.nextLine());
                    addMedia += valInt + ", ";
                    valChck = true;
                }
                catch (Exception e)
                {
                    System.out.println("Incorrect format. Correct format is '256'");
                }
            }
            valChck = false;

            while (valChck == false)
            {
                System.out.println("Enter the Journals ISSN");

                try
                {
                    valInt = Integer.parseInt(in.nextLine());
                    addMedia += valInt + ", ";
                    valChck = true;
                }
                catch (Exception e)
                {
                    System.out.println("Incorrect format. Correct ISSN format is '1593075006'");
                }
            }
            valChck = false;

            while (valChck == false)
            {
                System.out.println("Enter the Journals Issue Number: ");

                try
                {
                    valInt = Integer.parseInt(in.nextLine());
                    addMedia += valInt + ", ";
                    valChck = true;
                }
                catch (Exception e)
                {
                    System.out.println("Incorrect format. Correct Issue Number format is '18'");
                }
            }
            valChck = false;

            while (valChck == false)
            {
                System.out.println("Enter the issue date of the Journal: (e.g 12-12-2012)");

                try
                {
                    valDate = LocalDate.parse(in.nextLine(), form);
                    addMedia += valDate + ", ";
                    valChck = true;
                }
                catch (Exception e)
                {
                    System.out.println("Incorrect format. Correct Issue Date Format is '12-12-2012'");
                }
            }

            System.out.println("Enter the subject area of the Journal: ");
            addMedia += toTitleCase(in.nextLine()) + ", N/A, N/A, N/A, N/A, N/A";

            finishedStockFile += addMedia;

            writeFile("media.csv", finishedStockFile);
        }

        if (mediaType.equals("video"))
        {
            //Since mediaType gets passed in as lowercase, we convert it to titlecase using this method.
            fixMedType = toTitleCase(mediaType);
            addMedia += fixMedType + ", ";
            addMedia += (stockID + 1) + ", ";

            System.out.println("Enter the title of the Video: ");
            addMedia += toTitleCase(in.nextLine()) + ", ";

            while (valChck == false)
            {
                System.out.println("Enter the price of the Video: (e.g 8.99)");

                try
                {
                    valDouble = Double.parseDouble(in.nextLine());
                    addMedia += valDouble + ", ";
                    valChck = true;
                }
                catch (Exception e)
                {
                    System.out.println("Incorrect format, insert it in full correct format is '8.99'");
                }
            }
            valChck = false;

            System.out.println("Enter the publisher of the Video: ");
            addMedia += toTitleCase(in.nextLine()) + ", N/A, N/A, N/A, N/A, N/A, N/A, ";

            System.out.println("Enter the Videos genre: ");
            addMedia += toTitleCase(in.nextLine()) + ", N/A, N/A, N/A, N/A, N/A, ";

            while (valChck == false)
            {
                System.out.println("Enter the Videos Runtime: ");
                try
                {
                    valInt = Integer.parseInt(in.nextLine());
                    addMedia += valInt + ", N/A, N/A, N/A, N/A, N/A, N/A, N/A, N/A, N/A";
                    valChck = true;
                }
                catch (Exception e)
                {
                    System.out.println("Incorrect format. Correct format is '256'");
                }
            }
            valChck = false;

            System.out.println("Enter the Videos format: ");
            addMedia += toTitleCase(in.nextLine()) + ", ";
            System.out.println("Enter the Videos storage case: ");
            addMedia += toTitleCase(in.nextLine()) + ", N/A, N/A";

            finishedStockFile += addMedia;

            writeFile("media.csv", finishedStockFile);
        }

        if (mediaType.equals("compact disc"))
        {
            //Since mediaType gets passed in as lowercase, we convert it to titlecase using this method.
            fixMedType = toTitleCase(mediaType);
            addMedia += fixMedType + ", ";
            addMedia += (stockID + 1) + ", ";

            System.out.println("Enter the title of the CD: ");
            addMedia += toTitleCase(in.nextLine()) + ", ";

            while (valChck == false)
            {
                System.out.println("Enter the price of the CD: (e.g 8.99)");

                try
                {
                    valDouble = Double.parseDouble(in.nextLine());
                    addMedia += valDouble + ", ";
                    valChck = true;
                }
                catch (Exception e)
                {
                    System.out.println("Incorrect format, insert it in full correct format is '8.99'");
                }
            }
            valChck = false;

            System.out.println("Enter the publisher of the CD: ");
            addMedia += toTitleCase(in.nextLine()) + ", N/A, N/A, N/A, N/A, N/A, N/A, ";

            System.out.println("Enter the CD genre: ");
            addMedia += toTitleCase(in.nextLine()) + ", N/A, N/A, N/A, N/A, N/A, ";

            while (valChck == false)
            {
                System.out.println("Enter the CD Runtime: ");
                try
                {
                    valInt = Integer.parseInt(in.nextLine());
                    addMedia += valInt + ", N/A, ";
                    valChck = true;
                }
                catch (Exception e)
                {
                    System.out.println("Incorrect format. Correct format is '256'");
                }
            }
            valChck = false;

            System.out.println("Enter the CDs storage case: ");
            addMedia += toTitleCase(in.nextLine()) + ", ";

            while (valChck == false)
            {
                System.out.println("Enter the CDs track count: ");
                try
                {
                    valInt = Integer.parseInt(in.nextLine());
                    addMedia += valInt + ", ";
                    valChck = true;
                }
                catch (Exception e)
                {
                    System.out.println("Incorrect format. Correct format is '256'");
                }
            }
            valChck = false;

            System.out.println("Enter the CDs artist: ");
            addMedia += toTitleCase(in.nextLine()) + ", ";

            finishedStockFile += addMedia;

            writeFile("media.csv", finishedStockFile);
        }
    }

    //This method is really similar to other methods like editUser() and creation.
    //for example, checking the validation of ints, the validation of dates and so on
    //So I won't litter it with too many comments mostly to conserve time.
    //The only "varying" information is how it prints and stops users from entering data for mediaTypes that shouldn't have it
    //Lines like while ( colNum < 0 || colNum == 0 || colNum == 1 || colNum > 5 && colNum < 8 || colNum > 12)
    //All it does is check if the user input is equal to any of the above ==, or greater than/lower than certain numbers.
    //This blocks some fields from being edited stopping users from editing the Musical Artist of a Journal and so on
    //As doing so will likely break code later on.
    private void editStock()
    {
        String stockID;
        String mediaType;
        String columnsStore;
        String[] columns;
        int colNum = 22;
        String colDat = "";
        String finishedFile = "";
        boolean formatChck = false;
        boolean idChck = false;
        LocalDate valDate;
        DateTimeFormatter form = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        //Display the Stock Inventory to the user for easier selection.
        curInventory();

        //Ask for the stockID, I could have done title here, but I feel like it would've been way too hard
        //And a bit needlessly complex to do.
        System.out.println("Enter the Stock ID of the item, or 0 to return to the menu.");
        stockID = in.next();

        if (!stockID.equals("0"))
        {
            try
            {
                FileReader file = new FileReader("media.csv");
                BufferedReader buff = new BufferedReader(file);

                String line = buff.readLine();
                columnsStore = line;
                columns = line.split(",");
                line = buff.readLine();

                finishedFile = (columnsStore + "\n");

                //Here is where things get fun!
                while (line != null)
                {
                    String[] sp = line.split(",");

                    //If the stockID is found
                    if (stockID.equals(sp[1].trim()))
                    {
                        //Set the boolean to true because it is a valid ID.
                        idChck = true;
                        //Set the mediaType to the first value found on the line.
                        mediaType = sp[0].trim();

                        //Now we distinguish the different media types
                        //I do this because not all values extend to all mediaTypes
                        //Example, a book doesn't have a Runtime.
                        if (mediaType.equals("Book"))
                        {
                            //This is for iterating through the initial data all mediaTypes contain
                            //Those being type, stockid, title, price and publisher.
                            for (int i = 0; i < 5; i++)
                            {
                                //If the value is 3 (Price)
                                if (i == 3)
                                {
                                    //Add the poundsign before the price and print out.
                                    System.out.println((i+1) + ") " + columns[3].trim() + ": £" + sp[3].trim());
                                }
                                //Else it prints all the other data.
                                else
                                {
                                    System.out.println((i+1) + ") " + columns[i].trim() + ": " + sp[i].trim());
                                }
                            }
                            //I do another iterator to print the data relevant to Book only.
                            for (int k = 9; k < 13; k++)
                            {
                                System.out.println((k+1) + ") " + columns[k].trim() + ": " + sp[k].trim());
                            }

                            //Now while the colNum selected for editing is one of these values it won't run it
                            //This stops users from being able to edit the Video Format of a book and so on.
                            while ( colNum < 0 || colNum == 0 || colNum == 1 || colNum > 5 && colNum < 8 || colNum > 12)
                            {
                                System.out.println("Enter the column number you wish to edit. Media Type and Stock ID cannot be edited.");
                                try
                                {
                                    //colNum is - 1 because the displayed numbers don't reflect their index in the array.
                                    colNum = in.nextInt() - 1;
                                }
                                catch (Exception e)
                                {
                                    //Throws an error if the input isn't an int.
                                    System.out.println("Only numbers are accepted as a valid input.");
                                    in.nextLine();
                                }
                                in.nextLine();
                            }

                            while (formatChck == false)
                            {

                                //If price is selected
                                if (colNum == 3)
                                {
                                    //Ask for the new price
                                    System.out.println("Enter the new price");
                                    colDat = in.nextLine();

                                    //Validate the userinput with a regex for a price double.
                                    if (colNum == 3 && !colDat.matches("^[0-9]+.[0-9]+$"))
                                    {
                                        //If it doesn't match, tell the user the expect format.
                                        System.out.println("Format is 0.00");
                                    }
                                    else
                                    {
                                        //If everything is inserted properly set it to true to break out of the while loop.
                                        formatChck = true;
                                    }
                                }
                                //Else if ISBN is selected
                                else if (colNum == 9)
                                {
                                    //Ask for a new ISBN
                                    System.out.println("Enter the new ISBN");
                                    colDat = in.nextLine();

                                    //Validate the input to int.
                                    if (colNum == 9 && !colDat.matches("^[0-9]+$"))
                                    {
                                        //Display what's expected if the input is incorrect.
                                        System.out.println("Format is '1123313'");
                                    }
                                    else
                                    {
                                        //Break the while loop.
                                        formatChck = true;
                                    }
                                }
                                //Else if page count is selected
                                else if (colNum == 12)
                                {
                                    //Ask for a new page count
                                    System.out.println("Enter the new page count");
                                    colDat = in.nextLine();

                                    //Perform a bit of validation to check if it is an int.
                                    if (colNum == 12 && !colDat.matches("^[0-9]+$"))
                                    {
                                        //Inform the user of the format when it isn't an int
                                        System.out.println("Format is '234'");
                                    }
                                    else
                                    {
                                        //Else accept the format and break the while loop.
                                        formatChck = true;
                                    }
                                }
                                else
                                {
                                    System.out.println("Enter the new information: ");
                                    colDat = in.nextLine();
                                    formatChck = true;
                                }
                            }
                            System.out.println();
                        }
                        //A lot of the input validation is the same as above
                        //Just checking if any ints are ints, and changing how things are printed on the screen
                        //So I won't comment those since it's more or less the same.
                        //However, I will comment on issue date below.

                        if (mediaType.equals("Journal"))
                        {
                            for (int i = 0; i < 5; i++)
                            {
                                if (i == 3)
                                {
                                    //Add the poundsign before the price and print out.
                                    System.out.println((i+1) + ") " + columns[3].trim() + ": £" + sp[3].trim());
                                }
                                else
                                {
                                    System.out.println((i+1) + ") " + columns[i].trim() + ": " + sp[i].trim());
                                }
                            }
                            for (int k = 12; k < 17; k++)
                            {
                                System.out.println((k+1) + ") " + columns[k].trim() + ": " + sp[k].trim());
                            }

                            while ( colNum < 0 || colNum == 0 || colNum == 1 || colNum > 5 && colNum < 12 || colNum > 16) {
                                System.out.println("Enter the column number you wish to edit. Media Type and Stock ID cannot be edited.");
                                try {
                                    colNum = in.nextInt() - 1;
                                } catch (Exception e) {
                                    System.out.println("Only numbers are accepted as a valid input.");
                                    in.nextLine();
                                }
                                in.nextLine();
                            }
                            while (formatChck == false)
                            {
                                if (colNum == 3)
                                {
                                    System.out.println("Enter the new price");
                                    colDat = in.nextLine();

                                    if (colNum == 3 && !colDat.matches("^[0-9]+.[0-9]+$"))
                                    {
                                        System.out.println("Format is 0.00");
                                    }
                                    else
                                    {
                                        formatChck = true;
                                    }
                                }
                                else if (colNum == 12)
                                {
                                    System.out.println("Enter the new page count");
                                    colDat = in.nextLine();

                                    if (colNum == 13 && !colDat.matches("^[0-9]+$"))
                                    {
                                        System.out.println("Format is '234'");
                                    }
                                    else
                                    {
                                        formatChck = true;
                                    }
                                }
                                else if (colNum == 13)
                                {
                                    System.out.println("Enter the new ISSN");
                                    colDat = in.nextLine();

                                    if (colNum == 13 && !colDat.matches("^[0-9]+$"))
                                    {
                                        System.out.println("Format is '1123313'");
                                    }
                                    else
                                    {
                                        formatChck = true;
                                    }
                                }
                                else if (colNum == 14)
                                {
                                    System.out.println("Enter the new issue number");
                                    colDat = in.nextLine();

                                    if (colNum == 14 && !colDat.matches("^[0-9]+$"))
                                    {
                                        System.out.println("Format is '13'");
                                    }
                                    else
                                    {
                                        formatChck = true;
                                    }
                                }

                                //If issue date is selected
                                //This is very much like creation of member, stock and editing member.
                                else if (colNum == 15)
                                {
                                    //Ask for a new date displaying the expected format
                                    System.out.println("Enter the new issue date: '12-12-2012'");
                                    colDat = in.nextLine();

                                    try
                                    {
                                        //Check the format by parsing the input from colDat and using the pre-defined format above, called form.
                                        //If correct, break the loop and set it.
                                        //Else just throw an error and loop.
                                        LocalDate.parse(colDat, form);
                                        formatChck = true;
                                    }
                                    catch (Exception e)
                                    {
                                        System.out.println("Incorrect format. Correct Issue Date Format is '12-12-2012'");
                                    }
                                }
                                else
                                {
                                    System.out.println("Enter the new information: ");
                                    colDat = in.nextLine();
                                    formatChck = true;
                                }

                            }
                            System.out.println();
                        }
                        if (mediaType.equals("Video"))
                        {
                            for (int i = 0; i < 12; i++)
                            {
                                if (i == 3)
                                {
                                    //Add the poundsign before the price and print out.
                                    System.out.println((i+1) + ") " + columns[3].trim() + ": £" + sp[3].trim());
                                }
                                else if (i == 11)
                                {
                                    //Add the word "minutes" to the end.
                                    System.out.println((i+1) + ") " + columns[11].trim() + ": " + sp[11].trim());
                                }
                                else if (i < 5)
                                {
                                    System.out.println((i+1) + ") " + columns[i].trim() + ": " + sp[i].trim());
                                }
                            }
                            for (int k = 17; k < 20; k++)
                            {
                                if (k == 17)
                                {
                                //Add the word "minutes" to the end.
                                System.out.println((k+1) + ") " + columns[k].trim() + ": " + sp[k].trim() + " minutes.");
                                }
                                else
                                {
                                    System.out.println((k+1) + ") " + columns[k].trim() + ": " + sp[k].trim());
                                }
                            }
                            System.out.println();

                            while ( colNum < 0 || colNum == 0 || colNum == 1 ||colNum > 17 || colNum > 5 && colNum < 17) {
                                System.out.println("Enter the column number you wish to edit. Media Type and Stock ID cannot be edited.");
                                try {
                                    colNum = in.nextInt() - 1;
                                } catch (Exception e) {
                                    System.out.println("Only numbers are accepted as a valid input.");
                                    in.nextLine();
                                }
                                in.nextLine();
                            }
                            while (formatChck == false)
                            {
                                if (colNum == 3)
                                {
                                    System.out.println("Enter the new price");
                                    colDat = in.nextLine();

                                    if (colNum == 3 && !colDat.matches("^[0-9]+.[0-9]+$"))
                                    {
                                        System.out.println("Format is 0.00");
                                    }
                                    else
                                    {
                                        formatChck = true;
                                    }
                                }
                                else if (colNum == 17)
                                {
                                    System.out.println("Enter the new runtime");
                                    colDat = in.nextLine();

                                    if (colNum == 17 && !colDat.matches("^[0-9]+$"))
                                    {
                                        System.out.println("Format is '123'");
                                    }
                                    else
                                    {
                                        formatChck = true;
                                    }
                                }
                                else
                                {
                                    System.out.println("Enter the new information: ");
                                    colDat = in.nextLine();
                                    formatChck = true;
                                }
                            }
                            System.out.println();
                        }

                        if (mediaType.equals("Compact Disc"))
                        {
                            for (int i = 0; i < 12; i++)
                            {
                                if (i == 3)
                                {
                                    //Add the poundsign before the price and print out.
                                    System.out.println((i+1) + ") " + columns[3].trim() + ": £" + sp[3].trim());
                                }
                                else if (i == 11)
                                {
                                    //Add the word "minutes" to the end.
                                    System.out.println((i+1) + ") " + columns[11].trim() + ": " + sp[11].trim());
                                }
                                else if (i < 5)
                                {
                                    System.out.println((i+1) + ") " + columns[i].trim() + ": " + sp[i].trim());
                                }
                            }
                            for (int k = 17; k <= 21; k++)
                            {
                                if (k == 17)
                                {
                                    //Add the word "minutes" to the end.
                                    System.out.println((k+1) + ") " + columns[k].trim() + ": " + sp[k].trim() + " minutes.");
                                }
                                else if (k > 18)
                                {
                                    System.out.println((k+1) + ") " + columns[k].trim() + ": " + sp[k].trim());
                                }
                            }
                            System.out.println();

                            while ( colNum < 0 || colNum == 0 || colNum == 1 ||colNum > 17 || colNum > 5 && colNum < 17 || colNum == 18)  {
                                System.out.println("Enter the column number you wish to edit. Media Type and Stock ID cannot be edited.");
                                try {
                                    colNum = in.nextInt() - 1;
                                } catch (Exception e) {
                                    System.out.println("Only numbers are accepted as a valid input.");
                                    in.nextLine();
                                }
                                in.nextLine();
                            }
                            while (formatChck == false)
                            {
                                if (colNum == 3)
                                {
                                    System.out.println("Enter the new price");
                                    colDat = in.nextLine();

                                    if (colNum == 3 && !colDat.matches("^[0-9]+.[0-9]+$"))
                                    {
                                        System.out.println("Format is 0.00");
                                    }
                                    else
                                    {
                                        formatChck = true;
                                    }
                                }
                                else if (colNum == 17)
                                {
                                    System.out.println("Enter the new runtime");
                                    colDat = in.nextLine();

                                    if (colNum == 17 && !colDat.matches("^[0-9]+$"))
                                    {
                                        System.out.println("Format is '123'");
                                    }
                                    else
                                    {
                                        formatChck = true;
                                    }
                                }
                                else if (colNum == 20)
                                {
                                    System.out.println("Enter the new track count");
                                    colDat = in.nextLine();

                                    if (colNum == 17 && !colDat.matches("^[0-9]+$"))
                                    {
                                        System.out.println("Format is '23'");
                                    }
                                    else
                                    {
                                        formatChck = true;
                                    }
                                }
                                else
                                {
                                    System.out.println("Enter the new information: ");
                                    colDat = in.nextLine();
                                    formatChck = true;
                                }
                            }
                            System.out.println();
                        }

                        sp[colNum] = colDat;
                        line = "";

                        for (int i = 0; i < 21; i++)
                        {
                            if (i == 20)
                            {
                                line += sp[20].trim() + ", " + sp[21].trim();
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

                if (idChck)
                {
                    try
                    {
                        FileWriter input = new FileWriter("media.csv");

                        input.write(finishedFile);

                        input.close();

                        System.out.println("Changes have been made to the stock item. \n");
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }


    }

    //Originally wasn't going to do this
    //But it came in handy for the title of stock, and other things that require it.
    //Also added it to MemberMenu for the names, street and town.
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

    //Just a simple method for writing the file, helps keep the somewhat complex code neat.
    //Accepts the input of a CSV file and String data, this way I can just pass in the file
    //Say "media.csv" and then the edited, or new lines of data.
    public void writeFile(String csv, String data)
    {
        try
        {
            File file = new File(csv);

            FileWriter input = new FileWriter(file);

            input.write(data);

            input.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
