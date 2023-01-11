package com.mennomuller;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numberGrade;
        while (true) {
            try {
                System.out.print("Voer het cijfer in: ");
                numberGrade = input.nextInt();
                if (numberGrade >= 0) {
                    break;
                } else {
                    System.out.println("Negatieve cijfers zijn niet geldig.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Dat is geen cijfer.");
                input.next();
            }
        }
        if (numberGrade < 25) {
            System.out.println("F");
        } else if (numberGrade < 45) {
            System.out.println("E");
        } else if (numberGrade < 50) {
            System.out.println("D");
        } else if (numberGrade < 60) {
            System.out.println("C");
        } else if (numberGrade < 80) {
            System.out.println("B");
        } else {
            System.out.println("A");
        }
        System.out.println("\n\n");
        for (int i = 0; i <= 10; i++) {
            System.out.print(fibonacci(i) + " ");
        }
    }

    static int fibonacci(int n) {
        return n < 2 ? n : fibonacci(n - 1) + fibonacci(n - 2);
    }
}
