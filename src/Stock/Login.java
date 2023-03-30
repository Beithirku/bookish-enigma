package Stock;

import java.util.Scanner;
import java.util.LinkedList;
import java.util.Random;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Login {

    private static LinkedList<Members> members = new LinkedList<Members>();
    private static int userIndex;

    private static LinkedList<Media> media = new LinkedList<Media>();

    private static Scanner in = new Scanner (System.in);
    private static Menu menu = new Menu();

    public static void main(String[] args)
    {

        //Populates the CSVs at statup if they don't exist
        csvCheck();
        //Instantiates them so we can read through them later in the code
        loadDat();
        //Pulls the login menu
        login();
    }

    public static void login()
    {
        String menuItem;
        boolean quit = false;

        do {
            System.out.println("Welcome to Glencaldy Learning Centre.");
            System.out.println("1: Login");
            System.out.println("0: Quit");

            menuItem = in.next();

            //Use a Switch case for the menu

            switch (menuItem)
            {
                case "1":
                    System.out.println();
                    //So when 1 is selected it'll run loginHandler()
                    loginHandler();
                    break;
                case "0":
                    quit = true;
                    System.out.println("Thank you for using Glencaldy Learning Centre, Goodbye.");
            }
        } while (!quit);

        in.close();
        System.exit(0);
    }

    private static void loginHandler()
    {


        String user; //Stores the users username.
        String pass; //Stores the users password.

        System.out.println("Enter your Username: ");
        user = in.next();
        System.out.println();
        System.out.println("Enter your Password: ");
        pass = in.next();

        for(int i = 0; i < members.size(); i++)
        {
            if(members.get(i).getUsername().equals(user) && members.get(i).getPassword().equals(pass))
            {
                userIndex = i;

                //Displays the forename and surname of the logged in user before the menu, also adds an extra space
                System.out.println("Logged in as " + members.get(userIndex).getForename() + " " + members.get(userIndex).getSurname() + "\n");

                //Run recordSession to record the login.
                recordSession();

                //Load the main menu
                menu.menu();
            }
            else if (i == (members.size() - 1))
            {
                System.out.println("Information is incorrect." + "\n");
            }
        }
    }

    private static void recordSession()
    {
        Random rand = new Random();

        if (! new File("sessions.log").isFile());
        {
            try
            {
                File file = new File("sessions.log");
                file.createNewFile();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        try
        {
            String fileContent = ""; //For staring the contents of sessions.log

            FileReader file = new FileReader("sessions.log"); //Reads sessions.log
            BufferedReader buff = new BufferedReader(file); //Same as above, just buffered reader.

            String line = buff.readLine(); //Stores the first line of sessions.log

            while (line != null) //While there is lines in the file (Sessions.log)
            {
                if (line.equals("Logout Time: Currently logged in.")) //If the user is currently logged in-
                {
                    fileContent += ("Logout Time: Unknown" + "\n"); //-Change the logout time to unknown.
                    line = buff.readLine(); //Read the next line
                } else {
                    fileContent += (line + "\n"); //Adds a new line
                    line = buff.readLine(); //Reads next line
                }
            }
            file.close(); //Closes the stream.

            FileWriter input = new FileWriter("sessions.log"); //Writes data to the file and appends new data.

            DateTimeFormatter myTime = DateTimeFormatter.ofPattern("HH:mm:ss"); //Stores time in the format hour:min:second
            String curTime = LocalTime.now().format(myTime); //Stores the formatted time.

            //This part writes the:
            input.write(fileContent + "\n"); //Space.
            input.write("Username: " + members.get(userIndex).getUsername() + "\n"); //Username of logged in user.
            input.write("Computer ID: " + rand.nextInt(10) + "\n"); //Computer ID (just a random number)
            input.write("Date: " + LocalDate.now() + "\n"); //The current local date.
            input.write("Time: " + curTime + "\n"); //The formatted local time.
            input.write("Logout Time: Currently logged in."); //The user is still currently logged in.

            input.close();
        }
        catch (IOException e) //Catches any errors. Good practice
        {
            e.printStackTrace();
        }
    }

    private static void csvCheck() //Checks if the files exist already and creates them if they don't
    {
        if (! new File("members.csv").isFile()) //If the file doesn't exist
        {
            try
            {
                File members = new File("members.csv"); //Create the file

                //List of Characters from games and movies I like, or bands.
                //Later on it'll be split by an sp[number].
                //The sp corresponds to the value in members1 separated by comma.
                //Which in turn is passed into the one of the member classes (based on the member type)
                //So sp[4] is the Username, and sp[8] is the Street address, and so on.
                String members1 = "Member Type, Forename, Surname, User ID,  Username, Password, Phone Number, Email, Street Address, Town, Post Code, Date of Birth, Fine";
                String members2 = "Full, Dick, Gumshoe,01, Scruffy, 1234, 07911871278, detdickgum@gmail.com, 35 Biddulph Way, Ledbury, HR8 2HP, 19-04-1986, 0.00";
                String members3 = "Full, Ted, Logan, 02, Wyld, 2688, 07763639910, theoLo970@gmail.com, 35 Biddulph Way, Ledbury, HR8 2HP, 02-09-1970, 0.00";
                String members4 = "Full, Bill, Preston, 03, Stallyns, 1805, 07868474626, bPres1510@gmail.com, 35 Biddulph Way, Ledbury, HR8 2HP, 15-10-1970, 0.00";
                String members5 = "Staff, Duke, Pantarei,04, Nomos, 4567, 07457371859, deinnomos@gmail.com, 20 Thornton Close, Girton, CB3 0NQ, 08-07-1994, 0.00";
                String members6 = "Staff, Bill, Bruford, 05, BillBru, 1967, 07882722562, billBrudyes@gmail.com, 6 Pembury House, Ashford, TN23 5TY, 17-05-1949, 0.00";
                String members7 = "Staff, Lisa, Lisa, 06, CJam, 82394,07983808537, deinnomos@gmail.com, 20 Thornton Close, Girton, CB3 0NQ, 07-08-1984, 0.00";
                String members8 = "Casual, Tifa, Lockheart, 07, Teefa, 8910, 07181822842, teelock@gmail.com, 53 St James Street, Mangotsfield, BS16 9HE, 03-05-1987, 0.00";
                String members9 = "Casual, Kei, Mori, 08, Kei, 1109, 07841117928, keimo@gmail.com, 16 Maid Marian Road,Norwich, NR4 6DE, 18-09-1998, 0.00";
                String members10 = "Casual, Reno, Rude, 09, Leno, 9381, 07716231290, turkre@gmail.com, 45 Wessex Avenue, Bristol, BS7 0DE, 17-03-1979, 0.00";
                String members11 = "Admin, Cloud, Strife, 10, Puppet, 111213, 07911837432, claudestif@gmail.com, 19 Violet Road, Birkenhead, CH41 0EH, 11-08-1986, 0.00";

                FileWriter input = new FileWriter(members);

                //Writes the above data into the file on new lines.
                input.write(members1 + "\n");
                input.write(members2 + "\n");
                input.write(members3 + "\n");
                input.write(members4 + "\n");
                input.write(members5 + "\n");
                input.write(members6 + "\n");
                input.write(members7 + "\n");
                input.write(members8 + "\n");
                input.write(members9 + "\n");
                input.write(members10 + "\n");
                input.write(members11 + "\n");

                input.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        if (! new File("media.csv").isFile())
        {
            try
            {
                File media = new File("media.csv");

                String media1 = "Media Type, Stock ID, Title, Price, Publisher, Loaner Username, Loan Date, Reserver Username, Reserved till, ISBN, Author, Genre, Number of Pages, ISSN, Issue Number, Date of Issue, Subject Area, Run Time, Video Format, Storage Case, Number of Tracks, Artist";
                String media2 = "Video, 1, Bill & Ted's Excellent Adventure, 7.00, Studiocanal, Nomos, 08-10-2019, N/A, N/A, N/A, N/A, Comedy/Adventure, N/A, N/A, N/A, N/A, N/A, 90, Blu-Ray, Blu-Ray Case, N/A, N/A";
                String media3 = "Video, 2, A Silent Voice, 19.50, Kyoto Animation, Nomos, 15-01-2022, N/A, N/A, N/A, N/A, Drama/Anime, N/A, N/A, N/A, N/A, N/A, 129, Blu-Ray, Blu-Ray Case, N/A, N/A";
                String media4 = "Video, 3, Baby Driver, 6.99, Sony, Nomos, 11-12-2021, N/A, N/A, N/A, N/A, Crime, N/A, N/A, N/A, N/A, N/A, 113, Blu-Ray, Blu-Ray Case, N/A, N/A";
                String media5 = "Book, 4, Berserk Vol 13, 8.99, Dark Horse, , 14-11-2020, N/A, N/A, 1593075006, Kentaro Miura, Dark Fantasy,240, N/A, N/A, N/A, N/A, N/A, N/A, N/A, N/A, N/A";
                String media6 = "Book, 5, The Silmarillion, 50.82, HarperColins, , 15-07-2021, N/A, N/A, 0048231398, J. R. R. Tolkien, Mythopoeia,354, N/A, N/A, N/A, N/A, N/A, N/A, N/A, N/A, N/A";
                String media7 = "Book, 6, Sherlock Holmes: His Greatest Cases, 16.99, Arcturus, N/A, 13-06-2021, N/A, N/A, 1839401109, Sir Arthur Conan Doyle, Detective, 1328, N/A, N/A, N/A, N/A, N/A, N/A, N/A, N/A, N/A";
                String media8 = "Compact Disc, 7, Metaphorical Music, 12.21, Dimid Recordings, Scruffy, 26-02-2010, N/A, N/A,N/A, N/A,Hip Hop, N/A, N/A, N/A, N/A, N/A, 62, N/A, CD Case, 15, Nujabes";
                String media9 = "Compact Disc, 8, Hawaii: Part II, 11.11, Independent, Scruffy, 12-12-2012, N/A, N/A, N/A, N/A,Infinity, N/A, N/A, N/A, N/A, N/A, 45, N/A, CD Case, 12, Miracle Musical";
                String media10 = "Compact Disc, 9, Cojum Dip, 8.00, Independent, Scruffy, 05-06-2019, N/A, N/A, N/A, N/A,Hard Rock, N/A, N/A, N/A, N/A, N/A, 42, N/A, CD Case, 9, Cojum Dip";
                String media11 = "Journal, 10, Journal Of Computer Science, 10.0, Open Access, N/A, N/A, N/A, N/A, N/A, N/A, N/A, 348, 15493636, 1, 14-01-2022, Computer Science, N/A, N/A, N/A, N/A, N/A";
                String media12 = "Journal, 11, Current Research In Virology, 9.99, Open Access, N/A, N/A, N/A, N/A, N/A, N/A, N/A, 457, 19490097, 5, 03-03-2016, Virology, N/A, N/A, N/A, N/A, N/A";
                String media13 = "Journal, 12, Current Research In Bioinformatics, 5.99, Open Access, N/A, N/A, N/A, N/A, N/A, N/A, N/A, 789, 25242172, 9, 22-02-2020, Bioinformatics, N/A, N/A, N/A, N/A, N/A";

                FileWriter input = new FileWriter(media);

                input.write(media1 + "\n");
                input.write(media2 + "\n");
                input.write(media3 + "\n");
                input.write(media4 + "\n");
                input.write(media5 + "\n");
                input.write(media6 + "\n");
                input.write(media7 + "\n");
                input.write(media8 + "\n");
                input.write(media9 + "\n");
                input.write(media10 + "\n");
                input.write(media11 + "\n");
                input.write(media12 + "\n");
                input.write(media13 + "\n");


                input.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        if (! new File("loans.csv").isFile())
        {
            try
            {
                File loans = new File("loans.csv");

                String loans1 = "Username, Stock ID, Title, Loan Date, Return Date";
                String loans2 = "Scruffy, 3, Metaphorical Music, 26-02-2010, 03-03-2010";
                String loans3 = "Wyld, 2, Berserk Vol 13, 14-11-2020";
                String loans4 = "Nomos, 1, Bill & Ted's Excellent Adventure, 08-10-2019";

                FileWriter input = new FileWriter(loans);

                input.write(loans1 + "\n");
                input.write(loans2 + "\n");
                input.write(loans3 + "\n");
                input.write(loans4 + "\n");

                input.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    private static void loadDat()
    {
        try
        {
            FileReader file = new FileReader("members.csv");
            BufferedReader buff = new BufferedReader(file);

            DateTimeFormatter form = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            String line = buff.readLine();
            line = buff.readLine();

            //This bit basically reads each member in the CSV, if the first value (which is member type) says Full, Casual or Staff, it'll create an instance of them in the linkedlist.
            while (line != null)
            {
                //This is just for ease of use, but sp is for splitting, using the delimiter ",". sp being the first 2 letters of split because easy to remember.
                String[] sp = line.split(",");

                //This splits the members, checks the first value (which is Member Type)
                if (sp[0].trim().equals("Full"))
                {
                    //As mentioned further up, the numbers in the sp[] correspond to the above columns.
                    FullMember fullMember = new FullMember(sp[0].trim(), sp[1].trim(), sp[2].trim(), Integer.parseInt(sp[3].trim()), sp[4].trim(), sp[5].trim(), sp[6].trim(), sp[7].trim(), sp[8].trim(), sp[9].trim(), sp[10].trim(), sp[11].trim(), Double.parseDouble(sp[12].trim()));
                    members.add(fullMember);
                }
                else if (sp[0].trim().equals("Casual"))
                {
                    CasualMember casualMember = new CasualMember(sp[0].trim(), sp[1].trim(), sp[2].trim(), Integer.parseInt(sp[3].trim()), sp[4].trim(), sp[5].trim(), sp[6].trim(), sp[7].trim(), sp[8].trim(), sp[9].trim(), sp[10].trim(), sp[11].trim(), Double.parseDouble(sp[12].trim()));
                    members.add(casualMember);
                }
                else if (sp[0].trim().equals("Staff"))
                {
                    StaffMember staffMember = new StaffMember(sp[0].trim(), sp[1].trim(), sp[2].trim(), Integer.parseInt(sp[3].trim()), sp[4].trim(), sp[5].trim(), sp[6].trim(), sp[7].trim(), sp[8].trim(), sp[9].trim(), sp[10].trim(), sp[11].trim(), Double.parseDouble(sp[12].trim()));
                    members.add(staffMember);
                }
                else if (sp[0].trim().equals("Admin"))
                {
                    AdminMember adminMember = new AdminMember(sp[0].trim(), sp[1].trim(), sp[2].trim(), Integer.parseInt(sp[3].trim()), sp[4].trim(), sp[5].trim(), sp[6].trim(), sp[7].trim(), sp[8].trim(), sp[9].trim(), sp[10].trim(), sp[11].trim(), Double.parseDouble(sp[12].trim()));
                    members.add(adminMember);
                }

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
            FileReader file = new FileReader("media.csv");
            BufferedReader buff = new BufferedReader(file);

            DateTimeFormatter form = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            String line = buff.readLine();
            line = buff.readLine();

            //Works the same as the above member split, using commas as a delimiter.
            while (line != null)
            {
                String[] sp = line.split(",");

                if (sp[0].trim().equals("Book"))
                {
                    Book bookStock = new Book(Integer.parseInt(sp[1].trim()), sp[2].trim(), Double.parseDouble(sp[3].trim()), sp[4].trim(), sp[5].trim(), sp[6].trim(), sp[7].trim(), sp[8].trim(), sp[10].trim(), sp[11].trim(), Integer.parseInt(sp[12].trim()), sp[9].trim());
                    media.add(bookStock);
                }
                else if (sp[0].trim().equals("Compact Disc"))
                {
                    CompactDisc compactDisc = new CompactDisc(Integer.parseInt(sp[1].trim()), sp[2].trim(), Double.parseDouble(sp[3].trim()), sp[4].trim(), sp[5].trim(), sp[6].trim(), sp[7].trim(), sp[8].trim(), Integer.parseInt(sp[17].trim()), Integer.parseInt(sp[20].trim()), sp[19].trim(), sp[21].trim(), sp[11].trim());
                    media.add(compactDisc);
                }
                else if (sp[0].trim().equals("Video"))
                {
                    Video videoStock = new Video(Integer.parseInt(sp[1].trim()), sp[2].trim(), Double.parseDouble(sp[3].trim()), sp[4].trim(), sp[5].trim(), sp[6].trim(), sp[7].trim(), sp[8].trim(), Integer.parseInt(sp[17].trim()), sp[18].trim(), sp[11].trim(), sp[19].trim());
                    media.add(videoStock);
                }
                else if (sp[0].trim().equals("Journals"))
                {
                    Journals journalsStock = new Journals(Integer.parseInt(sp[1].trim()), sp[2].trim(), Double.parseDouble(sp[3].trim()), sp[4].trim(), sp[5].trim(), sp[6].trim(), sp[7].trim(), sp[8].trim(), sp[16].trim(), Integer.parseInt(sp[12].trim()), sp[13].trim(), sp[14].trim(), LocalDate.parse(sp[15].trim()));
                    media.add(journalsStock);
                }
                line = buff.readLine();
            }
            file.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    //This gets the user index for storing the positioning of users.
    public static int getUserIndex()
    {
        return userIndex;
    }

    //Getter for the members, stores them in a Linked List of member instances.
    public static LinkedList<Members> getMembers()
    {
        return members;
    }

    //Getter for the menu, nothing fancy.
    public static Menu getMenu()
    {
        return menu;
    }


}
