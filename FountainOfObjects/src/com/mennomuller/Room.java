package com.mennomuller;

import java.util.ArrayList;
import java.util.HashSet;

public abstract class Room {
    protected int x, y;
    protected Dungeon dungeon;

    public Room(int x, int y, Dungeon dungeon) {
        this.x = x;
        this.y = y;
        this.dungeon = dungeon;
    }

    public String location() {
        return TextHandler.color("You are in the room at (Row=" + y + ", Column=" + x + ").", TextHandler.Color.WHITE);
    }

    public void shoot() {
        TextHandler.printlnColor("You hear the arrow clatter onto the stone floor.", TextHandler.Color.MAGENTA);
    }

    public ArrayList<String> hints() {
        ArrayList<String> hints = new ArrayList<>();
        hints.add(location());
        HashSet<String> warnings = new HashSet<>();

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (dungeon.isValidLocation(i, j)) {
                    if (dungeon.getGrid()[i][j] instanceof PitRoom) {
                        warnings.add(TextHandler.color("You feel a draft. There is a pit in a nearby room.", TextHandler.Color.RED));
                    } else if (dungeon.getGrid()[i][j] instanceof MaelstromRoom) {
                        warnings.add(TextHandler.color("You hear the growling and groaning of a maelstrom nearby.", TextHandler.Color.RED));
                    } else if (dungeon.getGrid()[i][j] instanceof AmarokRoom) {
                        warnings.add(TextHandler.color("You can smell the rotten stench of an amarok in a nearby room.", TextHandler.Color.RED));
                    }
                }
            }
        }
        hints.addAll(warnings);
        return hints;
    }
}

class FountainRoom extends Room {

    private boolean isActive = false;

    public boolean isActive() {
        return isActive;
    }

    public void activate() {
        isActive = true;
    }

    public FountainRoom(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
    }

    @Override
    public ArrayList<String> hints() {
        ArrayList<String> hints = super.hints();
        if (isActive) {
            hints.add(1, TextHandler.color("You hear the rushing waters from the Fountain of Objects. It has been reactivated!", TextHandler.Color.BLUE));
        } else {
            hints.add(1, TextHandler.color("You hear water dripping in this room. The Fountain of Objects is here!", TextHandler.Color.BLUE));
        }
        return hints;
    }
}

class EntranceRoom extends Room {

    public EntranceRoom(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
    }

    @Override
    public ArrayList<String> hints() {
        ArrayList<String> hints = super.hints();
        if (dungeon.fountainActive()) {
            hints.add(1, TextHandler.color("The Fountain of Objects has been reactivated, and you have escaped with your life!", TextHandler.Color.MAGENTA));
            hints.add(2, TextHandler.color("You win!", TextHandler.Color.MAGENTA));
            hints.add(3, "Stop");
        } else {
            hints.add(1, TextHandler.color("You see light coming from the cavern entrance.", TextHandler.Color.YELLOW));
        }
        return hints;
    }
}

class EmptyRoom extends Room {

    public EmptyRoom(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
    }

    @Override
    public ArrayList<String> hints() {
        return super.hints();
    }
}

class PitRoom extends Room {

    public PitRoom(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
    }

    @Override
    public ArrayList<String> hints() {
        ArrayList<String> hints = super.hints();
        hints.add(1, TextHandler.color("There's a pit in this room! You fall into the pit and die.", TextHandler.Color.MAGENTA));
        hints.add(2, TextHandler.color("Game over.", TextHandler.Color.MAGENTA));
        hints.add(3, "Stop");
        return hints;
    }
}

class MaelstromRoom extends Room {

    public MaelstromRoom(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
    }

    @Override
    public void shoot() {
        TextHandler.printlnColor("Hit! The arrow pierces the core of the maelstrom, and you hear it dissipate.", TextHandler.Color.MAGENTA);
        dungeon.addRoom(RoomType.EMPTY, x, y);
    }

    @Override
    public ArrayList<String> hints() {
        ArrayList<String> hints = super.hints();
        hints.add(1, TextHandler.color("There's a maelstrom in this room! You are blown away to a different room.", TextHandler.Color.MAGENTA));
        hints.add(2, "Blow");
        return hints;
    }

    public void blowAway(Player player) {
        int px = (x + 2) % dungeon.getSize().width;
        int py = (y + 1) % dungeon.getSize().width;
        player.currentLocation = dungeon.getGrid()[px][py];
        int mx = (x - 2 + dungeon.getSize().width) % dungeon.getSize().width;
        int my = (y - 1 + dungeon.getSize().width) % dungeon.getSize().width;
        search:
        while (!(dungeon.getGrid()[mx][my] instanceof EmptyRoom)) {
            for (int i = 0; i < dungeon.getSize().width; i++) {
                mx = (mx + 1) % dungeon.getSize().width;
                if (dungeon.getGrid()[mx][my] instanceof EmptyRoom) {
                    break search;
                }
            }
            my = (my + 1) % dungeon.getSize().width;
        }
        dungeon.addRoom(RoomType.MAELSTROM, mx, my);
        dungeon.addRoom(RoomType.EMPTY, x, y);
        player.displayRoom();
    }
}

class AmarokRoom extends Room {

    public AmarokRoom(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
    }

    @Override
    public void shoot() {
        TextHandler.printlnColor("Hit! You hear the amarok howl in pain as it dies.", TextHandler.Color.MAGENTA);
        dungeon.addRoom(RoomType.EMPTY, x, y);
    }

    @Override
    public ArrayList<String> hints() {
        ArrayList<String> hints = super.hints();
        hints.add(1, TextHandler.color("There's an amarok in this room! It tears you to shreds with its claws.", TextHandler.Color.MAGENTA));
        hints.add(2, TextHandler.color("Game over.", TextHandler.Color.MAGENTA));
        hints.add(3, "Stop");
        return hints;
    }
}

enum RoomType {
    EMPTY, ENTRANCE, FOUNTAIN, PIT, MAELSTROM, AMAROK
}