package com.mennomuller.gamepieces;

import java.util.Random;

public class Dice {
    private int lastRoll;
    public final Random random = new Random();

    public int getLastRoll() {
        return lastRoll;
    }

    int roll() {
        lastRoll = random.nextInt(6) + 1;
        System.out.println("Rolled a " + lastRoll + "!");
        return lastRoll;
    }
}

