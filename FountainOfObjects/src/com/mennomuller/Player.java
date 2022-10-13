package com.mennomuller;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    Dungeon dungeon;
    Room currentLocation;

    public Player(Dungeon dungeon) {
        this.dungeon = dungeon;
        this.currentLocation = dungeon.entrance;
    }

    public void displayRoom() {
        System.out.println("-".repeat(82));
        ArrayList<String> hints = currentLocation.hints();
        boolean gameStillGoing = true;
        for (String line : hints) {

            if (line.equals("Stop")) {
                gameStillGoing = false;
                break;
            } else if (line.equals("Blow") && currentLocation instanceof MaelstromRoom) {
                gameStillGoing = false;
                ((MaelstromRoom) currentLocation).blowAway(this);
                break;

            }
            System.out.println(line);
        }
        if (gameStillGoing) {
            executeCommand(askCommand());
        }
    }

    public Command askCommand() {
        //Validated input for commands.
        Scanner input = new Scanner(System.in);
        Command answer = null;
        do {
            try {
                System.out.print(TextHandler.color("What do you want to do? ", Color.CYAN));
                answer = Command.valueOf(input.nextLine().toUpperCase().replace(' ', '_'));
            } catch (IllegalArgumentException e) {
                System.out.println(TextHandler.color("Invalid command.", Color.RED));
            }
        } while (answer == null);
        return answer;
    }

    public void executeCommand(Command command) {
        switch (command) {
            case MOVE_EAST -> {
                if (currentLocation.x < dungeon.size - 1) {
                    currentLocation = dungeon.grid[currentLocation.x + 1][currentLocation.y];
                } else {
                    System.out.println(TextHandler.color("There is nothing to the east. You can't move any further.", Color.RED));
                }
            }
            case MOVE_WEST -> {
                if (currentLocation.x > 0) {
                    currentLocation = dungeon.grid[currentLocation.x - 1][currentLocation.y];
                } else {
                    System.out.println(TextHandler.color("There is nothing to the west. You can't move any further.", Color.RED));
                }
            }
            case MOVE_NORTH -> {
                if (currentLocation.y < dungeon.size - 1) {
                    currentLocation = dungeon.grid[currentLocation.x][currentLocation.y + 1];
                } else {
                    System.out.println(TextHandler.color("There is nothing to the north. You can't move any further.", Color.RED));
                }
            }
            case MOVE_SOUTH -> {
                if (currentLocation.y > 0) {
                    currentLocation = dungeon.grid[currentLocation.x][currentLocation.y - 1];
                } else {
                    System.out.println(TextHandler.color("There is nothing to the south. You can't move any further.", Color.RED));
                }
            }
            case ENABLE_FOUNTAIN -> {
                if (currentLocation instanceof FountainRoom) {
                    ((FountainRoom) currentLocation).activate();
                } else {
                    System.out.println(TextHandler.color("The Fountain of Objects is not here. Nothing happens.", Color.RED));
                }
            }
        }

        displayRoom();
    }
}

enum Command {
    MOVE_NORTH,
    MOVE_SOUTH,
    MOVE_EAST,
    MOVE_WEST,
    ENABLE_FOUNTAIN
}