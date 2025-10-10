package com.pluralsight;

public class Main {
    public static void main(String[] args) {

        //HOME SCREEN!
        String mainMenu = """
         -------LEDGER.APP-------
         Welcome to the Ledger app!
         
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
                    return;
                default:
                    System.out.println("Invalid Input! Please choose valid command.");
                    break;
            }
        }
    }



    //MAIN MENU OPTIONS
    private static void addDeposit() {
    }

    private static void addDebitInfo() {
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
                    reportsMenu();
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

    private static void reportsMenu() {

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
                    return;
                case 'H':
                    return;
                default:
                    System.out.println("Invalid Input! Please choose valid command.");
                    break;

            }
        }
    }
    //todo fix going to the home menu




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