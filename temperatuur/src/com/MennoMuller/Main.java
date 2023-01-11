package com.MennoMuller;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        double fahrenheit;
        Scanner input = new Scanner(System.in);
        System.out.println("How many degrees Fahrenheit?");
        while (true) {
            try {
                fahrenheit = input.nextDouble();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input detected. Please enter a valid number.");
                input.next();
            }
        }
        System.out.println(fahrenheit+" degrees Fahrenheit.");
        System.out.println(((fahrenheit-32)*(5.0/9.0))+" degrees Celsius.");
    }
}
