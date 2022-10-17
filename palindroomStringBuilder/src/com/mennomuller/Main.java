package com.mennomuller;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        StringBuilder woord = new StringBuilder(input.nextLine());
        boolean isPalindroom = woord.toString().equalsIgnoreCase(woord.reverse().toString());
        System.out.println(woord.reverse() + " is " + (isPalindroom ? "" : "g") + "een palindroom!");

    }
}
