package com.mennomuller;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int number;
        while (true) {

            try {
                System.out.print("Geef een natuurlijk getal: ");
                number = input.nextInt();
                if (number < 0) {
                    System.out.println("Getal negatief, fout\n");
                } else if (number == 0) {
                    System.out.println("Getal nul, fout\n");
                } else if (number > 45) {
                    System.out.println("Getal te groot, past niet\n");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Dat is geen natuurlijk getal.\n");
                input.next();
            }
        }
        System.out.println("De eerste " + number + " Lucas-getallen:");
        System.out.println(lucasString(2, 1, number));
    }

    public static String lucasString(int lucas1, int lucas2, int steps) {
        if (steps == 1) {
            return Integer.toString(lucas1);
        } else {
            return lucas1 + " " + lucasString(lucas2, lucas1 + lucas2, steps - 1);
        }
    }
}
