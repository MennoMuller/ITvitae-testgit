package com.mennomuller;

public class TextHandler {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_MAGENTA = "\u001B[95m";
    public static final String ANSI_WHITE = "\u001B[97m";
    public static final String ANSI_CYAN = "\u001B[96m";
    public static final String ANSI_YELLOW = "\u001B[93m";
    public static final String ANSI_BLUE = "\u001B[94m";
    public static final String ANSI_RED = "\u001B[91m";
    public static final String ANSI_GREEN = "\u001B[92m";
    public static final String ANSI_GRAY = "\u001B[90m";

    public static String color(String text, Color color) {
        return switch (color) {
            case BLUE -> ANSI_BLUE;
            case CYAN -> ANSI_CYAN;
            case WHITE -> ANSI_WHITE;
            case YELLOW -> ANSI_YELLOW;
            case MAGENTA -> ANSI_MAGENTA;
            case RED -> ANSI_RED;
            case GREEN -> ANSI_GREEN;
            case GRAY -> ANSI_GRAY;
        } + text + ANSI_RESET;
    }

    public static void printlnColor(String text, Color color) {
        System.out.println(color(text, color));
    }

    public enum Color {
        MAGENTA,
        WHITE,
        CYAN,
        YELLOW,
        BLUE,
        RED,
        GREEN,
        GRAY
    }
}

