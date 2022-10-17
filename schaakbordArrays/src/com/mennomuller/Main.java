package com.mennomuller;

public class Main {

    public static void main(String[] args) {
        String[][] schaakbord = new String[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                schaakbord[i][j] = Character.toString('A' + i) + (j + 1);
                System.out.println(schaakbord[i][j]);
            }
        }
    }
}
