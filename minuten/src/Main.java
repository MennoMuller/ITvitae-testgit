import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static byte years;
    static short months;
    static short weeks;
    static int days;
    static int hours;
    static int minutes;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("How many years?");
        while (true) {
            try {
                years = input.nextByte();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input detected. Please enter a number lower than 128.");
                input.next();
            }
        }
        System.out.println(years + (years == 1 ? " year." : " years."));
        months = (short) (years * 12);
        System.out.println("That is " + months + " months.");
        weeks = (short) (years * 52);
        System.out.println("Or " + weeks + " weeks.");
        days = years * 365;
        System.out.println("Or " + days + " days.");
        hours = days * 24;
        System.out.println("Or " + hours + " hours.");
        minutes = hours * 60;
        System.out.println("Or " + minutes + " minutes.");
    }
}
