package com.mennomuller;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final String CHARS = "abcdefghijklmnopqrstuvwxyz0123456789";
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Voer een zin in: ");
        String inputString = input.nextLine().toLowerCase().replaceAll("[^a-z0-9 ]", "").trim();
        while(inputString.contains("  ")){
            inputString = inputString.replaceAll("\s{2}"," ");
        }
        System.out.println(inputString);
        System.out.println();
        int[] letterCounts = countLetters(inputString);
        printStats(inputString, letterCounts[0] + letterCounts[4] + letterCounts[8] + letterCounts[14] + letterCounts[20]);
        System.out.println();
        printGraph(letterCounts);

    }

    static void printStats(String string, int vowels) {
        System.out.println("Aantal karakters: " + string.length());

        System.out.println("Aantal woorden: " + string.split(" ").length);
        System.out.println("Aantal klinkers: " + vowels);
        string = string.replaceAll(" ","");
        StringBuilder sb = new StringBuilder(string);
        sb.reverse();
        System.out.println("Palindroom?\t"+string.equals(sb.toString()));
    }

    static int[] countLetters(String string) {
        int[] letterCounts = new int[CHARS.length()];
        for (int i = 0; i < string.length(); i++) {
            String c = Character.toString(string.charAt(i));
            if (CHARS.contains(c)) {
                letterCounts[CHARS.indexOf(c)]++;
            }
        }
        return letterCounts;
    }

    static int max(int[] array){
        int maxi = 0;
        for(int i:array){
            if(i>maxi){
                maxi=i;
            }
        }
        return maxi;
    }

    static void printGraph(int[] freqs){
        for(int i=max(freqs);i>0;i--){
            for(int f:freqs){
                System.out.print((f>=i?"#":" ")+" ");
            }
            System.out.println();
        }
        for(char c:CHARS.toCharArray()){
            System.out.print(c + " ");
        }
        System.out.println();
    }
}
