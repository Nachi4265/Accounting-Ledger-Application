package com.pluralsight;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Objects;



public class Main {
    public static ArrayList<Transaction> ledger = new ArrayList<>();



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

        //If the User Input equals some form of start the application will load Home Menu
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

        //Using a while statement to infinitely loop until we hit a break "Option is selected"
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

        //variables that will hold the transaction information for later use
        LocalDate date;
        LocalTime time = LocalTime.now();
        String description;
        String vendor;
        double amount;


        //Prompting for user information for transaction
        date = InputCollector.promptForDate("Enter the date(YYYY-mm-dd)");
        description = InputCollector.promptForString("Enter Item Description");
        vendor = InputCollector.promptForString("Enter Vendor");
        amount = InputCollector.promptForDouble("Enter amount");


        //create the transaction using constructor.
        Transaction newTransaction = new Transaction(date,time,description,vendor,amount);

        //add that transaction to my ledger
        ledger.add(newTransaction);

        //Using a Method to the new transaction to ledger.
        addTransactionToLedger(newTransaction);
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
        Transaction newTransaction = new Transaction(date,time,description,vendor,amount);

        //add that transaction to my ledger
        ledger.add(newTransaction);

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
                    //'R' Sends you to the Reports menu which returns a boolean true/false
                    //If true then we will head back to Main Menu if False then we stay in Ledger Menu
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

    //Method we are using to add our new transactions to our Ledger
    //Passing arguments to our Method to use
    private static void addTransactionToLedger(Transaction newTransaction){


        try{
            //Create a FileWriter to write to transactions.csv in append mode (Using a buffered writer for efficient writing)
            FileWriter fileWriter = new FileWriter("data/transactions.csv",true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);



            //write the new Transaction that has been converted to a string (.toString) in my writer.
            bufferedWriter.write("\n"+ newTransaction.toString());



            //confirmation transaction was added
            System.out.println("Transaction Added");
            System.out.println();

            //Always close filewriter to prevent resource Leaks
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
        System.out.println("Here are all current entries");
        System.out.println("----------------------------");

        //Iterates through our ledger and displays all transactions
        // t - is our loop varaible that holds each transaction
        // ledger - is the collection that we are iterating through
        for (Transaction t : ledger) {
            System.out.println(t);
        }
    }

    private static void displayDeposits() {
        System.out.println("Here are all of the current deposits");
        System.out.println("------------------------------------");

        for (Transaction t : ledger) {
            //if the amount of our transaction is greater than 0 , display it
            if (t.getAmount() > 0) {
                System.out.println(t);
            }
        }

    }

    private static void paymentsMade() {
        System.out.println("Here are all of the current payments");

        //if the amount of our transaction is less than 0 , display it
        for (Transaction t : ledger) {
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

    //todo finish adding comments from here down
    private static void loadLedgerFromFile(){
        try {
            //Create a FileReader to read the transactions from my file!
            FileReader fileReader = new FileReader("data/transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            //String variable for later use
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

                Transaction t = new Transaction(transactionDate, transactionTime, transactionDescription, transactionVendor, transactionPrice);
                ledger.add(t);
            }
            bufferedReader.close();
            fileReader.close();

        }catch (Exception e){
            System.out.println("Could not read from Ledger.");
//            e.printStackTrace();
        }


    }






    //REPORTS MENU
    private static void monthToDate() {
        System.out.println("Reports from the past month");
        for (Transaction t : ledger) {
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

        //Variable LocalDate that will get the current date
        LocalDate today = LocalDate.now();

        //
        YearMonth currentMonth = YearMonth.from(today);
        YearMonth previousMonth = currentMonth.minusMonths(1);

        for(Transaction t :ledger){
            YearMonth transactionMonth = YearMonth.from(t.getDate());

            if(transactionMonth.equals(previousMonth)){
                System.out.println(t);
            }
        }
    }

    private static void yearToDate() {
        System.out.println("Reports from the past year");
        for (Transaction t : ledger) {

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

        for(Transaction t :ledger){
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
        for (Transaction t : ledger){
            if(Objects.equals(t.getVendor(), vendorSearchedFor)){
                System.out.println(t);
            }
        }
    }//todo add ignore case
}