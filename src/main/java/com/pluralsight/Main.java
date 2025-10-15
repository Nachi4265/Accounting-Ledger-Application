package com.pluralsight;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;



public class Main {
    public static ArrayList<transaction> ledger = new ArrayList<>();

    //todo Polish code and add try/catch where needed.
    //todo add comments to code for presentation
    //todo add visual appeal for user
    //todo Make two different displays for terminal display and File display
    //todo do not rewrite data to Leader , Refresh ledger and add updated data


    //APPLICATION START-UP
    public static void main(String[] args) {

        //Load Ledger at start of application
        loadLedgerFromFile();

        //APPLICATION START UP!
        String appStartUp = """
                --------LEDGER.APP--------
                Welcome to the Ledger app!
                """;

        System.out.println(appStartUp);

        String startCommand;
        startCommand = InputCollector.promptForString("Enter 'Start' to load Application");
        if (startCommand.equalsIgnoreCase("start")) {
            homeMenu();
        }
    }




    //HOME SCREEN
    private static void homeMenu() {

        String mainMenu = """
                -------HOME MENU--------
                -Please choose an option-
                 D- Add Deposit
                 P- Make Payment
                 L- Ledger
                 X- Exit
                 ----------------------
                """;

        while (true) {
            System.out.println(mainMenu);

            char command;
            command = InputCollector.promptForChar("Enter a command");

            switch (command) {
                case 'D':
                    addDeposit();
                    break;
                case 'P':
                    addPayment();
                    break;
                case 'L':
                    ledgerMenu();
                    break;
                case 'X':
                    System.exit(0);
                default:
                    System.out.println("Invalid Input! Please choose valid command.");
                    break;
            }
        }
    }



    //MAIN MENU OPTIONS
    public static void addDeposit() {
        System.out.println("DEPOSIT-INFO");
        System.out.println();

        //Create transaction -> Add to Ledger -> Write that specific transaction to file.

        //variables that will hold the user input

        LocalDate date;
        LocalTime time = LocalTime.now();
        String description;
        String vendor;
        double amount;


        //Prompt for user information for transaction
        date = InputCollector.promptForDate("Enter the date(YYYY-mm-dd)");
        description = InputCollector.promptForString("Enter Item Description");
        vendor = InputCollector.promptForString("Enter Vendor");
        amount = InputCollector.promptForDouble("Enter amount");


        //create the transaction using constructor.
        transaction newTransaction = new transaction(date,time,description,vendor,amount);

        //add that transaction to my ledger
        ledger.add(newTransaction);

        //add our converted
        addTransactionToLedger( newTransaction);


    }

    private static void addPayment() {
        System.out.println("PAYMENT-INFO");
        System.out.println();

        LocalDate date;
        LocalTime time = LocalTime.now();
        String description;
        String vendor;
        double amount;


        //Prompt for user information for transaction
        date = InputCollector.promptForDate("Enter the date(YYYY-mm-dd)");
        description = InputCollector.promptForString("Enter Item Description");
        vendor = InputCollector.promptForString("Enter Vendor");
        amount = InputCollector.promptForDouble("Enter amount");


        //create the transaction using constructor.
        transaction newTransaction = new transaction(date,time,description,vendor,amount);

        //add that transaction to my ledger
        ledger.add(newTransaction);

        //add our converted
        addTransactionToLedger( newTransaction);
    }

    private static void ledgerMenu() {

        String ledgerMenu = """
                -----LEDGER.APP------
                
                ------Ledger Menu------
                -Please choose and option-
                 A- All entries
                 D- Deposits
                 P- Payments
                 R- Reports
                 H- Home
                 ----------------------
                """;


        while (true) {
            System.out.println(ledgerMenu);

            char command;
            command = InputCollector.promptForChar("Enter a command");

            switch (command) {
                case 'A':
                    displayAllEntries();
                    break;
                case 'D':
                    displayDeposits();
                    break;
                case 'P':
                    paymentsMade();
                    break;
                case 'R':
                    boolean goToMain = reportsMenu();
                    if (goToMain) {
                        return;
                    }
                    break;
                case 'H':
                    return;
                default:
                    System.out.println("Invalid Input! Please choose valid command.");
                    break;
            }


        }

    }

    private static void addTransactionToLedger(transaction newTransaction){


        try{

            //Use the file writer to choose where to write transaction.csv file
            FileWriter fileWriter = new FileWriter("data/transactions.csv",true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);



            //pass the new Transaction that has been converted to a string (.toString) in my writer.
            bufferedWriter.write("\n"+ newTransaction.toString());
            bufferedWriter.newLine();

            //confirmation transaction was added
            System.out.println("Transaction Added");
            System.out.println();


            bufferedWriter.close();
            fileWriter.close();
        }
        catch(Exception e){
            System.out.println("Could not add transaction");
//            e.printStackTrace();
        }
    }





    //LEDGER MENU
    private static void displayAllEntries() {
        System.out.println("Here are all current entire");
        System.out.println("----------------------------");

        for (transaction t : ledger) {
            System.out.println(t);
        }
    }

    private static void displayDeposits() {
        System.out.println("Here are all of the current deposits");
        System.out.println("------------------------------------");
        //todo display ONLY deposits
        for (transaction t : ledger) {
            if (t.getAmount() > 0) {
                System.out.println(t);
            }
        }

    }

    private static void paymentsMade() {
        System.out.println("Here are all of the current payments");
        //todo display ONlY payments
        for (transaction t : ledger) {
            if (t.getAmount() < 0) {
                System.out.println(t);
            }
        }
    }

    private static boolean reportsMenu() {

        String reportsMenu = """
                
                -----LEDGER.APP------
                
                ----REPORTS MENU----
                -Please choose and option-
                 1 - Month To Date
                 2 - Previous Month
                 3 - Year to Date
                 4 - Previous Year
                 5 - Search by Vendor
                 0 - Back
                 H - Home
                 ----------------------
                """;

        while (true) {
            System.out.println(reportsMenu);
            char command;
            command = InputCollector.promptForChar("Enter a number command");

            switch (command) {
                case '1':
                    monthToDate();
                    break;
                case '2':
                    previousMonth();
                    break;
                case '3':
                    yearToDate();
                    break;
                case '4':
                    previousYear();
                    break;
                case '5':
                    searchByVendor();
                    break;
                case '0':
                    return false;
                case 'H':
                    return true;
                default:
                    System.out.println("Invalid Input! Please choose valid command.");
                    break;

            }
        }
    }

    private static void loadLedgerFromFile(){
        try {
            //Made a FileReader to grab the transactions from my file!
            //Passed it into a BufferedReader so it saves time when reading ( Not that well see it )
            FileReader fileReader = new FileReader("data/transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String readFileLine;

            //reads first line of file before entering while loop.
            bufferedReader.readLine();


            while ((readFileLine = bufferedReader.readLine()) != null) {

                //If the Ledger data is empty it will skip over
                if(readFileLine.isEmpty()){
                    continue;
                }

                //Split the transaction into pieces
                String[] transactionParts = readFileLine.split("\\|");


                LocalDate transactionDate = LocalDate.parse(transactionParts[0]);
                LocalTime transactionTime = LocalTime.parse(transactionParts[1]);
                String transactionDescription = transactionParts[2];
                String transactionVendor = transactionParts[3];
                double transactionPrice = Double.parseDouble(transactionParts[4]);

                transaction t = new transaction(transactionDate, transactionTime, transactionDescription, transactionVendor, transactionPrice);
                ledger.add(t);
            }
            bufferedReader.close();
            fileReader.close();

        }catch (Exception e){
            System.out.println("Could not read from Ledger.");
            e.printStackTrace();
        }


    }






    //REPORTS MENU
    private static void monthToDate() {
        System.out.println("Reports from the past month");
        for (transaction t : ledger) {
            LocalDate transactionDate = t.getDate();
            LocalDate todayDate = LocalDate.now();
            if (transactionDate.getYear() == todayDate.getYear() && transactionDate.getMonth() == todayDate.getMonth()) {
                System.out.print(t);
            }
        }
        System.out.println();
    }

    private static void previousMonth() {
        System.out.println("Reports from Previous Month");

        LocalDate today = LocalDate.now();
        YearMonth currentMonth = YearMonth.from(today);
        YearMonth previousMonth = currentMonth.minusMonths(1);

        for(transaction t :ledger){
            YearMonth transactionMonth = YearMonth.from(t.getDate());

            if(transactionMonth.equals(previousMonth)){
                System.out.println(t);
            }
        }
    }

    private static void yearToDate() {
        System.out.println("Reports from the past year");
        for (transaction t : ledger) {

            LocalDate transactionDate = t.getDate();
            LocalDate todayDate = LocalDate.now();
            if (transactionDate.getYear() == todayDate.getYear()) {
                System.out.println(t);
            }
        }
        System.out.println();
    }

    private static void previousYear() {
        System.out.println("Reports from last year");
        //Variable for the current date
        LocalDate today = LocalDate.now();

        //Year Object
        Year currentYear = Year.from(today);
        Year previousYear = currentYear.minusYears(1);

        for(transaction t :ledger){
            Year transactionYear = Year.from(t.getDate());
            if(transactionYear.equals(previousYear)){
                System.out.println(t);
            }
        }
    }

    private static void searchByVendor() {
        System.out.println("Search by Vendor");

        //Ask for which vendor they want to search for.
       String vendorSearchedFor = InputCollector.promptForString("Enter vendor name");

        //Look through my Ledger
        for (transaction t : ledger){
            if(Objects.equals(t.getVendor(), vendorSearchedFor)){
                System.out.println(t);
            }
        }
    }//todo add ignore case
}