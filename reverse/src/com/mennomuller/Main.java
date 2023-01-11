package com.mennomuller;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Voer een woord of zin in.");
        String original = input.nextLine();
        String reverse = "";
        for (int i = original.length() - 1; i >= 0; i--) {
            reverse += original.charAt(i);
        }
        System.out.println(reverse);
        if (reverse.equalsIgnoreCase(original)) {
            System.out.println("Palindroom!");
        }
    }
}
