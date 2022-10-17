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

    public void startGame() {
        System.out.println(TextHandler.color("\nYou enter the Cavern of Objects, a maze of rooms filled with dangerous pits in search of the Fountain of Objects.", Color.MAGENTA));
        System.out.println(TextHandler.color("Light is visible only in the entrance, and no other light is seen anywhere in the caverns.", Color.MAGENTA));
        System.out.println(TextHandler.color("You must navigate the Caverns with your other senses.", Color.MAGENTA));
        System.out.println(TextHandler.color("\nLook out for pits. You will feel a breeze if a pit is in an adjacent room. If you enter a room with a pit, you will die.", Color.MAGENTA));
        System.out.println(TextHandler.color("\nMaelstroms are violent forces of sentient wind. Entering a room with one could transport you to any other location in the caverns. \nYou will be able to hear their growling and groaning in nearby rooms.", Color.MAGENTA));
        System.out.println(TextHandler.color("\nAmaroks roam the caverns. Encountering one is certain death, but you can smell their rotten stench in nearby rooms.", Color.MAGENTA));
        System.out.println(TextHandler.color("\nYou carry with you a bow and a quiver of arrows. You can use them to shoot monsters in the caverns but be warned: you have a limited supply.", Color.MAGENTA));
        System.out.println(TextHandler.color("\nFind the Fountain of Objects, activate it, and return to the entrance. ", Color.MAGENTA));
        displayRoom();
    }

    public void displayRoom() {
        System.out.println("-".repeat(82));
        ArrayList<String> hints = currentLocation.hints();
        hints.add(1, TextHandler.color("You have " + (arrows == 0 ? "no" : arrows) + (arrows == 1 ? " arrow." : " arrows."), Color.WHITE));
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
                System.out.println(TextHandler.color("Invalid command. Type \"help\" for a list of all available commands.", Color.RED));
            }
        } while (answer == null);
        return answer;
    }

    public void executeCommand(Command command) {
        switch (command) {
            case HELP -> help();
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

    private void help() {
        System.out.println();
        System.out.println(TextHandler.color("Help", Color.CYAN) + ": Displays a list of all available commands.\n");
        System.out.println(TextHandler.color("Move North", Color.CYAN) + ": Move 1 room to the north.");
        System.out.println(TextHandler.color("Move East", Color.CYAN) + ": Move 1 room to the east.");
        System.out.println(TextHandler.color("Move South", Color.CYAN) + ": Move 1 room to the south.");
        System.out.println(TextHandler.color("Move West", Color.CYAN) + ": Move 1 room to the west.\n");
        System.out.println(TextHandler.color("Shoot North", Color.CYAN) + ": Shoot an arrow into the room to the north.");
        System.out.println(TextHandler.color("Shoot East", Color.CYAN) + ": Shoot an arrow into the room to the east.");
        System.out.println(TextHandler.color("Shoot South", Color.CYAN) + ": Shoot an arrow into the room to the south.");
        System.out.println(TextHandler.color("Shoot West", Color.CYAN) + ": Shoot an arrow into the room to the west.\n");
        System.out.println(TextHandler.color("Enable Fountain", Color.CYAN) + ": Activate the Fountain of Objects, if present.\n");
        System.out.println("Commands are not case sensitive.");
    }
}

enum Command {
    HELP,
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