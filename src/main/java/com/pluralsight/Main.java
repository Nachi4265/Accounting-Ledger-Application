package com.pluralsight;

public class Main {

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
          A- Add Deposit
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
    private static void displayAllEntries() {
        System.out.println("Here are all current entire");
        //todo have this display all entries
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