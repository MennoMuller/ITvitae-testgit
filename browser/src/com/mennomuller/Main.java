package com.mennomuller;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String browser = input.nextLine();
        switch (browser){
            case "Edge":
                System.out.println("Je gebruikt Edge.");
                break;
            case "chrome":
            case "firefox":
            case "opera":
                System.out.println("Je gebruikt een moderne browser.");
                break;
            case "IE":
                System.out.println("Waarom gebruik je Internet Explorer?");
                break;
            default:
                System.out.println("Je gebruikt een niet-ondersteunde browser.");
        }
    }
}
