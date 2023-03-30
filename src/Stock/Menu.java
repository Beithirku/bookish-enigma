package Stock;



import java.util.Scanner;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Menu {

    private Scanner in = new Scanner (System.in);
    private MemberMenu member = new MemberMenu();
    private StockMenu stock = new StockMenu();

    public void menu()
    {
        if (Login.getMembers().get(Login.getUserIndex()).getMemberType().equals("Admin"))
        {
            adminMenu();
        }
        else
        {
            standardMenu();
        }
    }

    private void loginRecord()
    {
        try
        {
            FileReader file = new FileReader("sessions.log");
            BufferedReader buff = new BufferedReader(file);

            String line = buff.readLine();

            while (line !=null)
            {
                System.out.println(line);
                line = buff.readLine();
            }

            file.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void myloginRecord()
    {
        try
        {
            String fileContent = "";

            FileReader file = new FileReader("sessions.log");
            BufferedReader buff = new BufferedReader(file);

            String line = buff.readLine();

            while(line != null)
            {
                if(line.equals("Username: " + Login.getMembers().get(Login.getUserIndex()).getUsername()))
                {
                    fileContent += (line + "\n"); //Adds the first line to fileContent, then adds subsequent lines below for 6 lines.
                    line = buff.readLine();
                    fileContent += (line + "\n");
                    line = buff.readLine();
                    fileContent += (line + "\n");
                    line = buff.readLine();
                    fileContent += (line + "\n");
                    line = buff.readLine();
                    fileContent += (line + "\n");
                    line = buff.readLine();
                    fileContent += (line + "\n");
                }

                line = buff.readLine();
            }

            file.close();
            System.out.println(fileContent);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void standardMenu()
    {

        String menuItem;
        boolean quit = false;

        do {
            System.out.println("Main Menu");
            System.out.println("1: Member Records");
            System.out.println("2: Stock Records");
            System.out.println("3: Your Login History");
            System.out.println("0: Logout");

            menuItem = in.next();

            switch (menuItem) {
                case "1":
                    System.out.println();
                    member.memRecMenu();
                    break;
                case "2":
                    System.out.println();
                    stock.stockRecMenu();
                    break;
                case "3":
                    System.out.println();
                    myloginRecord();
                    break;
                case "0":
                    System.out.println("Logging out.");
                    logout();
                default:
                    System.out.println("Insert only 0 to 3.");
            }
        } while (!quit);
        in.close();

    }

    private void adminMenu()
    {
        String menuItem;
        boolean quit = false;

        do {
            System.out.println("Main Menu");
            System.out.println("1: Member Records");
            System.out.println("2: Stock Records");
            System.out.println("3: Your Login History");
            System.out.println("4: Full login history");
            System.out.println("0: Logout");

            menuItem = in.next();

            switch (menuItem) {
                case "1":
                    System.out.println();
                    member.memRecMenu();
                    break;
                case "2":
                    System.out.println();
                    stock.stockRecMenu();
                    break;
                case "3":
                    System.out.println();
                    myloginRecord();
                    break;
                case "4":
                    System.out.println();
                    loginRecord();
                    break;
                case "0":
                    quit = true;
                    logout();
                    System.out.println("Insert only 0 to 4.");
            }
        } while (!quit);
        in.close();
    }

    private void logout()
    {
        try
        {

            //Stores the sessions.log contents.
            String fileContent = "";

            //Sets up the reader for sessions.log
            FileReader file = new FileReader("sessions.log");
            //Sets up the buffered reader.
            BufferedReader buff = new BufferedReader(file);

            //Reads and stores a line of the log file.
            String line = buff.readLine();


            while (line != null && ! line.equals("Logout Time: Currently logged in."))
            {
                //Adds line to the fileContents.
                fileContent += (line + "\n");
                //Reads the next line.
                line = buff.readLine();
            }

            file.close();

            //Sets the writer up for input. Writing back the initial data.
            FileWriter input = new FileWriter("sessions.log");

            //Formatting the time to Hour:min:seconds
            DateTimeFormatter myTime = DateTimeFormatter.ofPattern("HH:mm:ss");
            //Stores the formatted time.
            String curTime = LocalTime.now().format(myTime);

            //Amends the final line with the current time for logout time.
            input.write(fileContent + "Logout Time: " + curTime);

            input.close();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        Login.login();
    }

    public MemberMenu getMemberMenu()
    {
        return member;
    }

    public StockMenu getStockMenu()
    {
        return stock;
    }

}
