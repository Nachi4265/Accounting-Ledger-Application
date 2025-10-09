package com.pluralsight;

public class Main {
    public static void main(String[] args) {


        //HOME SCREEN!
        String mainMenu = """
         -----LEDGER.APP------
         
         Welcome to the Ledger app!
         -Please choose and option-
          D- Add Deposit
          P- Make Payment
          L- Ledger
          X- Exit
          ----------------------
         """;

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

    //MAIN MENU OPTIONS
    private static void addDeposit() {
    }

    private static void addDebitInfo() {
    }

    private static void ledgerMenu() {
    }


}