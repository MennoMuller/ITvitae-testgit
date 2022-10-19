package com.mennomuller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    final int separatorWidth = 82;
    Dungeon dungeon;
    Room currentLocation;
    int arrows;
    LocalDateTime startingTime;

    public Player(Dungeon dungeon) {
        this.dungeon = dungeon;
        this.currentLocation = dungeon.getEntrance();
        this.arrows = 5;
    }

    public void startGame() {
        startingTime = LocalDateTime.now();
        System.out.print(TextHandler.ANSI_MAGENTA);
        System.out.println("\nYou enter the Cavern of Objects, a maze of rooms filled with dangerous pits in search of the Fountain of Objects.");
        System.out.println("Light is visible only in the entrance, and no other light is seen anywhere in the caverns.");
        System.out.println("You must navigate the Caverns with your other senses.");
        System.out.println("\nLook out for pits. You will feel a breeze if a pit is in an adjacent room. If you enter a room with a pit, you will die.");
        System.out.println("\nMaelstroms are violent forces of sentient wind. Entering a room with one could transport you to any other location in the caverns. \nYou will be able to hear their growling and groaning in nearby rooms.");
        System.out.println("\nAmaroks roam the caverns. Encountering one is certain death, but you can smell their rotten stench in nearby rooms.");
        System.out.println("\nYou carry with you a bow and a quiver of arrows. You can use them to shoot monsters in the caverns but be warned: you have a limited supply.");
        System.out.println("\nFind the Fountain of Objects, activate it, and return to the entrance. ");
        System.out.print(TextHandler.ANSI_RESET);
        displayRoom();
        endGame();
    }

    private void endGame() {
        LocalDateTime endingTime = LocalDateTime.now();
        Duration playTime = Duration.between(startingTime, endingTime);
        long seconds = playTime.getSeconds() % 60;
        System.out.println("Elapsed time: " + playTime.toMinutes() + ":" + (seconds >= 10 ? seconds : "0" + seconds));

    }

    public void displayRoom() {
        System.out.println("-".repeat(separatorWidth));
        ArrayList<String> hints = currentLocation.hints();
        hints.add(1, TextHandler.color("You have " + (arrows == 0 ? "no" : arrows) + (arrows == 1 ? " arrow." : " arrows."), TextHandler.Color.WHITE));
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
                System.out.print(TextHandler.color("What do you want to do? ", TextHandler.Color.CYAN));
                answer = Command.valueOf(input.nextLine().toUpperCase().replace(' ', '_'));
            } catch (IllegalArgumentException e) {
                TextHandler.printlnColor("Invalid command. Type \"help\" for a list of all available commands.", TextHandler.Color.RED);
            }
        } while (answer == null);
        return answer;
    }

    public void executeCommand(Command command) {
        switch (command) {
            case HELP -> help();
            case MOVE_EAST -> move(Direction.EAST);
            case SHOOT_EAST -> shoot(Direction.EAST);
            case MOVE_WEST -> move(Direction.WEST);
            case SHOOT_WEST -> shoot(Direction.WEST);
            case MOVE_NORTH -> move(Direction.NORTH);
            case SHOOT_NORTH -> shoot(Direction.NORTH);
            case MOVE_SOUTH -> move(Direction.SOUTH);
            case SHOOT_SOUTH -> shoot(Direction.SOUTH);
            case ENABLE_FOUNTAIN -> enableFountain();
        }
        displayRoom();
    }

    private void move(Direction d) {
        int newX = currentLocation.x + d.dx;
        int newY = currentLocation.y + d.dy;
        if (dungeon.isValidLocation(newX, newY)) {
            currentLocation = dungeon.getGrid()[newX][newY];
        } else {
            TextHandler.printlnColor("There is nothing to the " +
                    d.toString().toLowerCase() + ". You can't move any further.", TextHandler.Color.RED);
        }
    }

    private void enableFountain() {
        if (currentLocation instanceof FountainRoom) {
            ((FountainRoom) currentLocation).activate();
        } else {
            TextHandler.printlnColor("The Fountain of Objects is not here. Nothing happens.", TextHandler.Color.RED);
        }
    }

    private void shoot(Direction d) {
        if (arrows > 0) {
            int targetX = currentLocation.x + d.dx;
            int targetY = currentLocation.y + d.dy;
            if (dungeon.isValidLocation(targetX, targetY)) {
                dungeon.getGrid()[targetX][targetY].shoot();
                arrows--;
            } else {
                TextHandler.printlnColor("There is nothing to the " +
                        d.toString().toLowerCase() + ". You can't shoot there.", TextHandler.Color.RED);
            }
        } else {
            TextHandler.printlnColor("You're out of arrows.", TextHandler.Color.RED);
        }
    }

    private void help() {
        System.out.println();
        System.out.println(TextHandler.color("Help", TextHandler.Color.CYAN) + ": Displays a list of all available commands.\n");
        System.out.println(TextHandler.color("Move North", TextHandler.Color.CYAN) + ": Move 1 room to the north.");
        System.out.println(TextHandler.color("Move East", TextHandler.Color.CYAN) + ": Move 1 room to the east.");
        System.out.println(TextHandler.color("Move South", TextHandler.Color.CYAN) + ": Move 1 room to the south.");
        System.out.println(TextHandler.color("Move West", TextHandler.Color.CYAN) + ": Move 1 room to the west.\n");
        System.out.println(TextHandler.color("Shoot North", TextHandler.Color.CYAN) + ": Shoot an arrow into the room to the north.");
        System.out.println(TextHandler.color("Shoot East", TextHandler.Color.CYAN) + ": Shoot an arrow into the room to the east.");
        System.out.println(TextHandler.color("Shoot South", TextHandler.Color.CYAN) + ": Shoot an arrow into the room to the south.");
        System.out.println(TextHandler.color("Shoot West", TextHandler.Color.CYAN) + ": Shoot an arrow into the room to the west.\n");
        System.out.println(TextHandler.color("Enable Fountain", TextHandler.Color.CYAN) + ": Activate the Fountain of Objects, if present.\n");
        System.out.println("Commands are not case sensitive.");
    }

    enum Direction {
        NORTH(0, 1),
        SOUTH(0, -1),
        EAST(1, 0),
        WEST(-1, 0);
        final int dx, dy;

        Direction(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }
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