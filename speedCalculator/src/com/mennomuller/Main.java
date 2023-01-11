package com.mennomuller;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int meters, hours, minutes, seconds;
        Scanner input = new Scanner(System.in);
        System.out.println("How many meters?");
        while (true) {
            try {
                meters = input.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input detected. Please enter a valid number.");
                input.next();
            }
        }
        System.out.println("How many hours?");
        while (true) {
            try {
                hours = input.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input detected. Please enter a valid number.");
                input.next();
            }
        }
        System.out.println("How many minutes?");
        while (true) {
            try {
                minutes = input.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input detected. Please enter a valid number.");
                input.next();
            }
        }
        System.out.println("How many seconds?");
        while (true) {
            try {
                seconds = input.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input detected. Please enter a valid number.");
                input.next();
            }
        }
        minutes += hours * 60;
        seconds += minutes * 60;
        double metersPerSecond = (double)meters/(double)seconds;
        System.out.println(metersPerSecond+" m/s");
        double kph = ((double)meters/1000.0)/((double)seconds/3600.0);
        System.out.println(kph+" km/h");
        double mph = ((double)meters/1609.0)/((double)seconds/3600.0);
        System.out.println(mph+" mph");
    }
}
