package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;

public class Main {

    public static ArrayList<transaction> ledger = displayAllEntries();

    //APPLICATION START-UP
    public static void main(String[] args) {

        //APPLICATION START UP!

        String appStartUp = """
         --------LEDGER.APP--------
         Welcome to the Ledger app!
         """;

        System.out.println(appStartUp);

        String startCommand;
        startCommand = InputCollector.promptForString("Enter 'Start' to load Application");
        System.out.println();
        if (startCommand.equalsIgnoreCase("start")){
            homeMenu();
        }
    }



    //HOME SCREEN
    private static void homeMenu() {

        String mainMenu = """
         -------HOME MENU--------
         -Please choose and option-
          D- Add Deposit
          P- Make Payment
          L- Ledger
          X- Exit
          ----------------------
         """;

        while(true){
            System.out.println(mainMenu);

            char command;
            command = InputCollector.promptForChar("Enter a command");

            switch(command){
                case 'D':
                    addDeposit();
                    break;
                case 'P':
                    addDebitInfo();
                    break;
                case 'L':
                    ledgerMenu();
                    break;
                case'X':
                    System.exit(0);
                default:
                    System.out.println("Invalid Input! Please choose valid command.");
                    break;
            }
        }
    }



    //MAIN MENU OPTIONS
    private static void addDeposit() {
        System.out.println("ADD DEPOSIT");
        System.out.println();

        //Initialize my variables so they can be changed and used.
        LocalDate date ;
        LocalTime time ;
        String description = "";
        String vendor = "";
        double amount = 0.00;

        date = InputCollector.promptForLocalDate("Enter the current date (YYYY-mm-dd)");
        time = LocalTime.now();
        description = InputCollector.promptForString("Enter description");
        vendor = InputCollector.promptForString("Enter vendor");
        amount = InputCollector.promptFoDouble("Enter the amount");

        transaction newTransaction = new transaction(date,time,description,vendor,amount);
        transaction.add(newTransaction);

    }

    private static void addDebitInfo() {
        System.out.println("DEBIT-INFO");
        System.out.println();
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

        while(true){
            System.out.println(ledgerMenu);

            char command;
            command = InputCollector.promptForChar("Enter a command");

            switch(command){
                case 'A':
                    displayAllEntries();
                    break;
                case 'D':
                    displayDeposits();
                    break;
                case 'P':
                    paymentsMade();
                    break;
                case'R':
                    boolean goToMain = reportsMenu();
                    if(goToMain){
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




    //LEDGER MENU
    private static ArrayList<transaction> displayAllEntries() {
        System.out.println("Here are all current entires");
        System.out.println("----------------------------");
        //todo have this display all entries
        ArrayList<transaction>ledger = new ArrayList<>();
        try{
            //Made a FileReader to grab the transactions from my file!
            //Passed it into a BufferedReader so it saves time when reading ( Not that well see it )
            FileReader fileReader = new FileReader(" transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String readFileLine;

            //reads first line of file before entering while loop.
            bufferedReader.readLine();

            while((readFileLine = bufferedReader.readLine())!=null){
                //We need to split the transaction into pieces
                String[]transactionParts = readFileLine.split("\\|");
                LocalDate transactionDate = LocalDate.parse(transactionParts[0]);
                LocalTime transactionTime = LocalTime.parse(transactionParts[1]);
                String transactionDescription = transactionParts[2];
                String transactionVendor = transactionParts[3];
                double transactionPrice = Double.parseDouble(transactionParts[4]);

                transaction t = new transaction(transactionDate,transactionTime,transactionDescription,transactionVendor,transactionPrice);
                ledger.add(t);
            }
            for (transaction t : ledger){
                System.out.println(t);
            }
        }
        catch (Exception e){
            System.out.println("Could not read from Ledger.");
//            e.printStackTrace();
        }
        return ledger;

    }

    private static void displayDeposits() {
        System.out.println("Here are all of the current deposits");
        //todo display ONLY deposits
    }

    private static void paymentsMade() {
        System.out.println("Here are all of the current payments");
        //todo display ONlY payments
    }

    private static boolean reportsMenu() {

        String mainMenu = """
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

        while(true){
            System.out.println(mainMenu);
            char command;
            command = InputCollector.promptForChar("Enter a number command");

            switch(command){
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






    //REPORTS MENU
    private static void monthToDate() {
        System.out.println("Reports from the past month");
        //todo
    }

    private static void previousMonth() {
        System.out.println("Reports from Previous Month");
        //todo
    }

    private static void yearToDate() {
        System.out.println("Reports from the past year");
        //todo
    }

    private static void previousYear() {
        System.out.println("Reports from last year");
        //todo
    }

    private static void searchByVendor() {
        System.out.println("Searching by Vendor");
        //todo
    }



}