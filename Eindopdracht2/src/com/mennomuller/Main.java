package com.mennomuller;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int myNum;
        Scanner input = new Scanner(System.in);
        System.out.println("What number?");
        while (true) {
            try {
                myNum = input.nextInt();
                if (myNum < 0) {
                    System.out.println("Please enter a positive number.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input detected. Please enter a valid number.");
                input.next();
            }
        }
        int sumOdd = 0;
        for (int i = 1; i <= myNum; i += 2) {
            sumOdd += i;
        }
        System.out.println("Sum of odd numbers: " + sumOdd);
        int sumEven = 0;
        for (int i = 2; i <= myNum; i += 2) {
            sumEven += i;
        }
        System.out.println("Sum of even numbers: " + sumEven);
        System.out.println("Difference is " + (sumOdd - sumEven));
    }
}
