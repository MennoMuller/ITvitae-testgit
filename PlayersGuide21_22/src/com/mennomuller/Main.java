package com.mennomuller;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Arrow myArrow = Arrow.askArrow();
        System.out.println(myArrow);
        System.out.println(myArrow.getCost());
    }
}

class Arrow {
    private final Arrowhead head;
    private final Fletching fletching;
    private final int length;
    private final ArrowType type;

    private static Arrowhead askArrowhead() {
        //Validated input for arrowhead
        Scanner input = new Scanner(System.in);
        Arrowhead answer = null;
        System.out.println("We have wood, steel and obsidian arrowheads.");
        do {
            try {
                System.out.print("What kind of arrowhead? ");
                answer = Arrowhead.valueOf(input.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("I don't have that in stock.");
            }
        } while (answer == null);
        return answer;
    }

    private static Fletching askFletching() {
        //Validated input for fletching.
        Scanner input = new Scanner(System.in);
        Fletching answer = null;
        System.out.println("We have plastic, turkey feather and goose feather fletching.");
        do {
            try {
                System.out.print("What kind of fletching? ");
                answer = Fletching.valueOf(input.nextLine().toUpperCase().replace(' ', '_'));
            } catch (IllegalArgumentException e) {
                System.out.println("I don't have that in stock.");
            }
        } while (answer == null);
        return answer;
    }

    public static Arrow askArrow() {
        //Asks the user to choose one of the premade arrow types, or a custom one.
        //Invalid input is assumed to be custom.
        Scanner input = new Scanner(System.in);
        ArrowType answer;
        try {
            System.out.println("We have Beginner, Elite, Marksman and Custom arrows.");
            System.out.print("What kind of arrow do you want? ");
            answer = ArrowType.valueOf(input.next().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("I don't know what that is. I guess you want a custom one then.");
            answer = ArrowType.CUSTOM;
        }
        Arrow arrow = null;
        switch (answer) {
            case ELITE -> arrow = createEliteArrow();
            case BEGINNER -> arrow = createBeginnerArrow();
            case MARKSMAN -> arrow = createMarksmanArrow();
            case CUSTOM -> arrow = createCustomArrow();
        }
        return arrow;
    }

    private static int askLength() {
        //Validated input for arrow length
        Scanner input = new Scanner(System.in);
        int answer = 0;
        System.out.println("Shafts can be 60-100 cm.");
        do {
            try {
                System.out.print("What shaft length? (in cm) ");
                answer = input.nextInt();
                if (answer > 100) {
                    System.out.println("Too long!");
                } else if (answer < 60) {
                    System.out.println("Too short!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please give a number.");
            }
        } while (answer < 60 || answer > 100);
        return answer;
    }

    public static Arrow createCustomArrow() {
        return new Arrow(askArrowhead(), askFletching(), askLength());
    }

    public static Arrow createEliteArrow() {
        return new Arrow(Arrowhead.STEEL, Fletching.PLASTIC, 95, ArrowType.ELITE);
    }

    public static Arrow createBeginnerArrow() {
        return new Arrow(Arrowhead.WOOD, Fletching.GOOSE_FEATHER, 75, ArrowType.BEGINNER);
    }

    public static Arrow createMarksmanArrow() {
        return new Arrow(Arrowhead.STEEL, Fletching.GOOSE_FEATHER, 65, ArrowType.MARKSMAN);
    }

    public double getCost() {
        //Calculates the cost of the arrow.
        return 0.05 * length +
                switch (head) {
                    case WOOD -> 3;
                    case STEEL -> 10;
                    case OBSIDIAN -> 5;
                }
                + switch (fletching) {
            case PLASTIC -> 10;
            case TURKEY_FEATHER -> 5;
            case GOOSE_FEATHER -> 3;
        };
    }

    public Arrow(Arrowhead head, Fletching fletching, int length, ArrowType type) {
        this.head = head;
        this.fletching = fletching;
        this.length = length;
        this.type = type;
    }

    public Arrow(Arrowhead head, Fletching fletching, int length) {
        this(head, fletching, length, ArrowType.CUSTOM);
    }

    @Override
    public String toString() {
        String typeString = type.toString().charAt(0) + type.toString().toLowerCase().substring(1);
        return typeString + " Arrow with "
                + head.toString().toLowerCase() + " head, "
                + fletching.toString().toLowerCase().replace('_', ' ') + " fletching and "
                + length + " cm shaft";
    }
}

enum Arrowhead {
    STEEL, WOOD, OBSIDIAN
}

enum Fletching {
    PLASTIC, TURKEY_FEATHER, GOOSE_FEATHER
}

enum ArrowType {
    ELITE, BEGINNER, MARKSMAN, CUSTOM
}