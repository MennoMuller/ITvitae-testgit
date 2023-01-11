package com.mennomuller;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Tafelmaker: Geef de grootte aan: ");
        int grootte = input.nextInt();
        int maxDigits = (int) Math.floor(Math.log10(grootte * grootte)) + 1;
        int gDigits = (int) Math.floor(Math.log10(grootte));
        System.out.print(" ".repeat(gDigits) + "*|");

        for (int i = 1; i <= grootte; i++) {
            System.out.print((" ".repeat(maxDigits - (int) Math.floor(Math.log10(i)))) + i);

        }
        System.out.println();
        System.out.println("-".repeat(grootte * (maxDigits + 1) + gDigits + 3));
        for (int i = 1; i <= grootte; i++) {
            System.out.print((" ".repeat(gDigits - (int) Math.floor(Math.log10(i)))) + i + "|");
            for (int j = 1; j <= grootte; j++) {
                int ij = i * j;
                System.out.print((" ".repeat(maxDigits - (int) Math.floor(Math.log10(ij)))) + ij);
            }
            System.out.println();
        }

    }

}
