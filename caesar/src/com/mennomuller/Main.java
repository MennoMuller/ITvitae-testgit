package com.mennomuller;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int n = 13;
        Scanner input = new Scanner(System.in);
        String secret;
        System.out.println("What do you want to encode?");
        while (true) {
            try {
                secret = input.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input detected. Please enter a valid text.");
                input.next();
            }
        }
        secret = secret.toUpperCase();
        String result = "";
        for (int i = 0; i < secret.length(); i++) {
            char c = secret.charAt(i);
            if (Character.isLetter(c)) {
                if (c <= 'Z' - n) {
                    c += n;
                } else {
                    c += n - 26;
                }
            }
            result = result + c;
        }
        System.out.println(result);
    }
}
