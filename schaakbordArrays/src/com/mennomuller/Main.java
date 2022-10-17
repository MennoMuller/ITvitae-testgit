package com.mennomuller;

public class Main {

    public static void main(String[] args) {
        int afmeting = 8;
        String[][] schaakbord = new String[afmeting][afmeting];
        for (int i = 0; i < afmeting; i++) {
            for (int j = 0; j < afmeting; j++) {
                schaakbord[i][j] = Character.toString('A' + i) + (j + 1);
                System.out.println(schaakbord[i][j]);
            }
        }
    }
}
