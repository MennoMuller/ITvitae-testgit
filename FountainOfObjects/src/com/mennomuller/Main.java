package com.mennomuller;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Player player = new Player(new Dungeon());
        player.displayRoom();
    }
}

class Dungeon {
    private Size dungeonSize;
    int size;
    private long seed = 123456789L;
    private Random random = new Random(seed);
    Room[][] grid;
    FountainRoom fountain;
    EntranceRoom entrance;

    public Dungeon() {
        this(Dungeon.askSize());
    }

    public Dungeon(Size dungeonSize) {
        this.dungeonSize = dungeonSize;
        size = switch (dungeonSize) {
            case LARGE -> 8;
            case MEDIUM -> 6;
            case SMALL -> 4;
        };
        grid = new Room[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = new EmptyRoom(i, j, this);
            }
        }
        addRoom(RoomType.ENTRANCE, 0, 0);
        addRoom(RoomType.FOUNTAIN, size / 2, 0);
        switch (dungeonSize) {
            case SMALL -> {
                addRoom(RoomType.PIT);
                addRoom(RoomType.MAELSTROM);
            }
            case MEDIUM -> {
                addRooms(RoomType.PIT, 2);
                addRoom(RoomType.MAELSTROM);
            }
            case LARGE -> {
                addRooms(RoomType.PIT, 4);
                addRooms(RoomType.MAELSTROM, 2);
            }
        }
    }

    enum Size {
        SMALL, MEDIUM, LARGE
    }

    public static Size askSize() {
        //Validated input for dungeon size.
        Scanner input = new Scanner(System.in);
        Size answer = null;
        do {
            try {
                System.out.print(TextHandler.color("What size dungeon do you want? ", Color.CYAN));
                answer = Size.valueOf(input.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println(TextHandler.color("Invalid size.", Color.RED));
            }
        } while (answer == null);
        return answer;
    }

    public void addRooms(RoomType type, int count) {
        for (int i = 0; i < count; i++) {
            addRoom(type);
        }
    }

    public void addRoom(RoomType type) {
        int x, y;
        do {
            x = random.nextInt(size);
            y = random.nextInt(size);
        } while (!(grid[x][y] instanceof EmptyRoom));
        addRoom(type, x, y);
    }

    public void addRoom(RoomType type, int x, int y) {
        grid[x][y] = switch (type) {
            case ENTRANCE -> new EntranceRoom(x, y, this);
            case FOUNTAIN -> new FountainRoom(x, y, this);
            case PIT -> new PitRoom(x, y, this);
            case MAELSTROM -> new MaelstromRoom(x, y, this);
            default -> new EmptyRoom(x, y, this);
        };
        switch (type) {
            case ENTRANCE -> entrance = (EntranceRoom) grid[x][y];
            case FOUNTAIN -> fountain = (FountainRoom) grid[x][y];
        }
    }
}