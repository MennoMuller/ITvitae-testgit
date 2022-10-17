package com.mennomuller;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        StringBuilder woord = new StringBuilder(input.nextLine().toLowerCase());
        System.out.println(woord.toString().equals(woord.reverse().toString()));
    }
}
