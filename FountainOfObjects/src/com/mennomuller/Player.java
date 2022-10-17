package com.mennomuller;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    Dungeon dungeon;
    Room currentLocation;
    int arrows;

    public Player(Dungeon dungeon) {
        this.dungeon = dungeon;
        this.currentLocation = dungeon.entrance;
        this.arrows = 5;
    }

    public void displayRoom() {
        System.out.println("-".repeat(82));
        ArrayList<String> hints = currentLocation.hints();
        hints.add(1, TextHandler.color("You have " + arrows + (arrows == 1 ? " arrow." : " arrows."), Color.WHITE));
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
            case MOVE_EAST -> moveEast();
            case SHOOT_EAST -> shootEast();
            case MOVE_WEST -> moveWest();
            case SHOOT_WEST -> shootWest();
            case MOVE_NORTH -> moveNorth();
            case SHOOT_NORTH -> shootNorth();
            case MOVE_SOUTH -> moveSouth();
            case SHOOT_SOUTH -> shootSouth();
            case ENABLE_FOUNTAIN -> enableFountain();
        }

        displayRoom();
    }

    private void moveNorth() {
        if (currentLocation.y < dungeon.size - 1) {
            currentLocation = dungeon.grid[currentLocation.x][currentLocation.y + 1];
        } else {
            System.out.println(TextHandler.color("There is nothing to the north. You can't move any further.", Color.RED));
        }
    }

    private void moveEast() {
        if (currentLocation.x < dungeon.size - 1) {
            currentLocation = dungeon.grid[currentLocation.x + 1][currentLocation.y];
        } else {
            System.out.println(TextHandler.color("There is nothing to the east. You can't move any further.", Color.RED));
        }
    }

    private void moveSouth() {
        if (currentLocation.y > 0) {
            currentLocation = dungeon.grid[currentLocation.x][currentLocation.y - 1];
        } else {
            System.out.println(TextHandler.color("There is nothing to the south. You can't move any further.", Color.RED));
        }
    }

    private void moveWest() {
        if (currentLocation.x > 0) {
            currentLocation = dungeon.grid[currentLocation.x - 1][currentLocation.y];
        } else {
            System.out.println(TextHandler.color("There is nothing to the west. You can't move any further.", Color.RED));
        }
    }

    private void enableFountain() {
        if (currentLocation instanceof FountainRoom) {
            ((FountainRoom) currentLocation).activate();
        } else {
            System.out.println(TextHandler.color("The Fountain of Objects is not here. Nothing happens.", Color.RED));
        }
    }

    private void shootNorth() {
        if (arrows > 0) {
            if (currentLocation.y < dungeon.size - 1) {
                dungeon.grid[currentLocation.x][currentLocation.y + 1].shoot();
                arrows--;
            } else {
                System.out.println(TextHandler.color("There is nothing to the north. You can't shoot there.", Color.RED));
            }
        } else {
            System.out.println(TextHandler.color("You're out of arrows.", Color.RED));
        }
    }

    private void shootEast() {
        if (arrows > 0) {
            if (currentLocation.x < dungeon.size - 1) {
                dungeon.grid[currentLocation.x + 1][currentLocation.y].shoot();
                arrows--;
            } else {
                System.out.println(TextHandler.color("There is nothing to the east. You can't shoot there.", Color.RED));
            }
        } else {
            System.out.println(TextHandler.color("You're out of arrows.", Color.RED));
        }
    }

    private void shootSouth() {
        if (arrows > 0) {
            if (currentLocation.y > 0) {
                dungeon.grid[currentLocation.x][currentLocation.y - 1].shoot();
                arrows--;
            } else {
                System.out.println(TextHandler.color("There is nothing to the south. You can't shoot there.", Color.RED));
            }
        } else {
            System.out.println(TextHandler.color("You're out of arrows.", Color.RED));
        }
    }

    private void shootWest() {
        if (arrows > 0) {
            if (currentLocation.x > 0) {
                dungeon.grid[currentLocation.x - 1][currentLocation.y].shoot();
                arrows--;
            } else {
                System.out.println(TextHandler.color("There is nothing to the west. You can't shoot there.", Color.RED));
            }
        } else {
            System.out.println(TextHandler.color("You're out of arrows.", Color.RED));
        }
    }
}

enum Command {
    MOVE_NORTH,
    MOVE_SOUTH,
    MOVE_EAST,
    MOVE_WEST,
    ENABLE_FOUNTAIN,
    SHOOT_NORTH,
    SHOOT_SOUTH,
    SHOOT_EAST,
    SHOOT_WEST
}