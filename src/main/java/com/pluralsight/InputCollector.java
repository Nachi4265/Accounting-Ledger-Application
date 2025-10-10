package com.pluralsight;
import java.util.Scanner;

public class InputCollector {

    //This is what will collect all our input
    private static final Scanner scanner = new Scanner(System.in);

    public static String promptForString(String prompt){
        System.out.print(prompt + ": ");
        return scanner.nextLine().trim();
    }

    public static char promptForChar(String prompt){
        System.out.print(prompt + ": ");
        return scanner.next().toUpperCase().charAt(0);
    }

    public static float promptForFloat(String prompt){

        boolean isFloat = false;
        float result = 0;

        do{
            try{
                System.out.print(prompt + ": ");
                result = scanner.nextFloat();
                scanner.nextLine();
                isFloat = true;

            }catch(Exception e){
                scanner.nextLine();
                System.out.println("please enter a number with decimal");
            }
        }while (!isFloat);

        return result;

    }

    public static int promptForInt(String prompt) {

        boolean Num = false;
        int result = 0;
        do{
            try{
                System.out.print(prompt + ": ");
                result = scanner.nextInt();
                scanner.nextLine();
                Num = true;
            }
            catch(Exception ex){
                scanner.nextLine();
                System.out.println("Invalid Entry, please enter a whole number");
            }
        } while (!Num);

        return result;
    }

    public static long promptForLong(String prompt){
        System.out.print(prompt + ": ");
        long result = scanner.nextLong();
        scanner.nextLine();
        return result;
    }

}
