package com.mennomuller;

import java.util.ArrayList;
import java.util.Scanner;

/*

 |----|
 O   \|
/|\   |
/ \   |
_____/|\_
 */

public class Main {
    static ArrayList<Character> guessWord = new ArrayList<>();
    static String secretWord;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Voer het woord in: ");
        secretWord = input.nextLine().toUpperCase();

        for (int i = 0; i < secretWord.length(); i++) {
            guessWord.add('_');
        }
        int guessesLeft = 13;
        ArrayList<Character> wrongGuesses = new ArrayList<>();
        while (guessesLeft > 0) {
            printGuess(wrongGuesses);
            System.out.print("Raad een letter: ");
            char guess = input.nextLine().toUpperCase().charAt(0);
            boolean wrongGuess = true;
            for (int i = 0; i < secretWord.length(); i++) {
                if (guess == secretWord.charAt(i)) {
                    wrongGuess = false;
                    guessWord.set(i, guess);
                }
            }
            if (wrongGuess) {
                guessesLeft--;
                wrongGuesses.add(guess);
            } else if (wordGuessed()) {
                System.out.println("Gefeliciteerd! Je hebt het geraden!");
                break;
            }

        }
        if (guessesLeft == 0) {
            printGuess(wrongGuesses);
            System.out.println("Game over");
        }
        System.out.println("Het woord was: " + secretWord);

    }

    private static boolean wordGuessed() {
        return !guessWord.contains('_');
    }

    private static void printGuess(ArrayList<Character> wrongGuesses) {
        int wrongCount = wrongGuesses.size();
        switch (wrongCount) {
            case 0 -> System.out.println("\n\n\n\n\n");
            case 1 -> System.out.println("\n\n\n\n_________");
            case 2 -> {
                System.out.print("      |\n".repeat(4));
                System.out.println("______|__");
            }
            case 3 -> {
                System.out.print("      |\n".repeat(4));
                System.out.println("_____/|__");
            }
            case 4 -> {
                System.out.print("      |\n".repeat(4));
                System.out.println("_____/|\\_");
            }
            case 5 -> {
                System.out.println(" -----|");
                System.out.print("      |\n".repeat(3));
                System.out.println("_____/|\\_");
            }
            case 6 -> {
                System.out.println(" -----|");
                System.out.println("     \\|");
                System.out.print("      |\n".repeat(2));
                System.out.println("_____/|\\_");
            }
            case 7 -> {
                System.out.println(" |----|");
                System.out.println("     \\|");
                System.out.print("      |\n".repeat(2));
                System.out.println("_____/|\\_");
            }
            case 8 -> {
                System.out.println(" |----|");
                System.out.println(" O   \\|");
                System.out.print("      |\n".repeat(2));
                System.out.println("_____/|\\_");
            }
            case 9 -> {
                System.out.println(" |----|");
                System.out.println(" O   \\|");
                System.out.println(" |    |");
                System.out.println("      |");
                System.out.println("_____/|\\_");
            }
            case 10 -> {
                System.out.println(" |----|");
                System.out.println(" O   \\|");
                System.out.println("/|    |");
                System.out.println("      |");
                System.out.println("_____/|\\_");
            }
            case 11 -> {
                System.out.println(" |----|");
                System.out.println(" O   \\|");
                System.out.println("/|\\   |");
                System.out.println("      |");
                System.out.println("_____/|\\_");
            }
            case 12 -> {
                System.out.println(" |----|");
                System.out.println(" O   \\|");
                System.out.println("/|\\   |");
                System.out.println("/     |");
                System.out.println("_____/|\\_");
            }
            case 13 -> {
                System.out.println(" |----|");
                System.out.println(" O   \\|");
                System.out.println("/|\\   |");
                System.out.println("/ \\   |");
                System.out.println("_____/|\\_");
            }
        }
        for (char c : wrongGuesses) {
            System.out.print(c + " ");
        }
        System.out.println("\n");

        for (char c : guessWord) {
            System.out.print(c);
        }
        System.out.println();
    }
}
