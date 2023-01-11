package com.mennomuller;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Voer het aantal items in: ");
        int NUM_ITEMS = input.nextInt();
        System.out.print("Voer de waarde van alle items in (gescheiden door spatie): ");
        int[] items = new int[NUM_ITEMS];
        for (int i = 0; i < NUM_ITEMS; i++) {
            items[i] = input.nextInt();
        }
        System.out.println("De waarden zijn: "+ Arrays.toString(items));

        for (int item : items) {
            System.out.println("*".repeat(item));
        }
    }
}
