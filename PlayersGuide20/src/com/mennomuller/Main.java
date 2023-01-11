package com.mennomuller;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Type answer1 = null;
        do {
            try {
                System.out.print("What do you want to make? ");
                answer1 = Type.valueOf(input.next().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("I don't have a recipe for that.");
            }
        } while (answer1 == null);
        MainIngredient answer2 = null;
        do {
            try {
                System.out.print("What ingredient do you want to use? ");
                answer2 = MainIngredient.valueOf(input.next().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("I don't have that in stock.");
            }
        } while (answer2 == null);
        Seasoning answer3 = null;
        do {
            try {
                System.out.print("What seasoning do you want to use? ");
                answer3 = Seasoning.valueOf(input.next().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("I don't have that in stock.");
            }
        } while (answer3 == null);
        Soup mySoup = new Soup(answer1,answer2,answer3);
        System.out.println("Have yourself a nice bowl of "+mySoup+".");

    }
}
