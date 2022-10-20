package com.mennomuller;

import com.mennomuller.rooms.*;
import com.mennomuller.util.TextHandler;

import java.util.Random;
import java.util.Scanner;

public class Dungeon {
    private final Size size;
    private final long seed = 123456789L;
    private final Random random = new Random(seed);
    private final Room[][] grid;
    private FountainRoom fountain;
    private EntranceRoom entrance;

    public Dungeon() {
        this(Dungeon.askSize());
    }

    public Dungeon(Size size) {
        this.size = size;
        grid = new Room[size.width][size.width];
        for (int i = 0; i < size.width; i++) {
            for (int j = 0; j < size.width; j++) {
                grid[i][j] = new EmptyRoom(i, j, this);
            }
        }
        addRoom(Room.RoomType.ENTRANCE, 0, 0);
        addRoom(Room.RoomType.FOUNTAIN, size.width / 2, 0);
        addRooms(Room.RoomType.PIT, size.pits);
        addRooms(Room.RoomType.MAELSTROM, size.maelstroms);
        addRooms(Room.RoomType.AMAROK, size.amaroks);
    }

    public Room[][] getGrid() {
        return grid;
    }

    public boolean fountainActive() {
        return fountain.isActive();
    }

    public EntranceRoom getEntrance() {
        return entrance;
    }

    public enum Size {
        SMALL(4, 1, 1, 1),
        MEDIUM(6, 2, 1, 2),
        LARGE(8, 4, 2, 3);
        public final int width, pits, maelstroms, amaroks;

        Size(int width, int pits, int maelstroms, int amaroks) {
            this.width = width;
            this.pits = pits;
            this.maelstroms = maelstroms;
            this.amaroks = amaroks;
        }
    }

    public static Size askSize() {
        //Validated input for dungeon size.
        Scanner input = new Scanner(System.in);
        Size answer = null;
        do {
            try {
                System.out.print(TextHandler.color("What size dungeon do you want? ", TextHandler.Color.CYAN));
                answer = Size.valueOf(input.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                TextHandler.printlnColor("Invalid size.", TextHandler.Color.RED);
            }
        } while (answer == null);
        return answer;
    }

    public void addRooms(Room.RoomType type, int count) {
        for (int i = 0; i < count; i++) {
            addRoom(type);
        }
    }

    public void addRoom(Room.RoomType type) {
        int x, y;
        do {
            x = random.nextInt(size.width);
            y = random.nextInt(size.width);
        } while (!(grid[x][y] instanceof EmptyRoom));
        addRoom(type, x, y);
    }

    public void addRoom(Room.RoomType type, int x, int y) {
        grid[x][y] = switch (type) {
            case ENTRANCE -> new EntranceRoom(x, y, this);
            case FOUNTAIN -> new FountainRoom(x, y, this);
            case PIT -> new PitRoom(x, y, this);
            case MAELSTROM -> new MaelstromRoom(x, y, this);
            case AMAROK -> new AmarokRoom(x, y, this);
            default -> new EmptyRoom(x, y, this);
        };
        switch (type) {
            case ENTRANCE -> entrance = (EntranceRoom) grid[x][y];
            case FOUNTAIN -> fountain = (FountainRoom) grid[x][y];
        }
    }

    public boolean isValidLocation(int x, int y) {
        return x >= 0 && y >= 0 && x < size.width && y < size.width;
    }

    public Size getSize() {
        return size;
    }
}
