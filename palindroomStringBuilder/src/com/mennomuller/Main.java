package com.mennomuller;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        StringBuilder woord = new StringBuilder(str.toLowerCase());
        System.out.println(str.equals(woord.reverse().toString()));
    }
}
