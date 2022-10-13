package com.mennomuller;

import java.util.ArrayList;

public abstract class Room {
    protected int x, y;
    protected Dungeon dungeon;

    public Room(int x, int y, Dungeon dungeon) {
        this.x = x;
        this.y = y;
        this.dungeon = dungeon;
    }

    public String location() {
        return TextHandler.color("You are in the room at (Row=" + y + ", Column=" + x + ")", Color.WHITE);
    }

    public ArrayList<String> hints() {
        ArrayList<String> hints = new ArrayList<>();
        hints.add(location());
        boolean pitNear = false;
        boolean maelstromNear = false;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                try {
                    if (dungeon.grid[i][j] instanceof PitRoom) {
                        pitNear = true;
                    } else if (dungeon.grid[i][j] instanceof MaelstromRoom){
                        maelstromNear = true;
                    }
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
            }
        }
        if (pitNear) {
            hints.add(TextHandler.color("You feel a draft. There is a pit in a nearby room.", Color.RED));
        }
        if (maelstromNear) {
            hints.add(TextHandler.color("You hear the growling and groaning of a maelstrom nearby.", Color.RED));
        }
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
            hints.add(1, TextHandler.color("You hear the rushing waters from the Fountain of Objects. It has been reactivated!", Color.BLUE));
        } else {
            hints.add(1, TextHandler.color("You hear water dripping in this room. The Fountain of Objects is here!", Color.BLUE));
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
        if (dungeon.fountain.isActive()) {
            hints.add(1, TextHandler.color("The Fountain of Objects has been reactivated, and you have escaped with your life!", Color.MAGENTA));
            hints.add(2, TextHandler.color("You win!", Color.MAGENTA));
            hints.add(3, "Stop");
        } else {
            hints.add(1, TextHandler.color("You see light coming from the cavern entrance.", Color.YELLOW));
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
        hints.add(1, TextHandler.color("There's a pit in this room! You fall into the pit and die.", Color.MAGENTA));
        hints.add(2, TextHandler.color("Game over.", Color.MAGENTA));
        hints.add(3, "Stop");
        return hints;
    }
}

class MaelstromRoom extends Room {

    public MaelstromRoom(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
    }

    @Override
    public ArrayList<String> hints() {
        ArrayList<String> hints = super.hints();
        hints.add(1, TextHandler.color("There's a maelstrom in this room! You are blown away to a different room.", Color.MAGENTA));
        hints.add(2, "Blow");
        return hints;
    }

    public void blowAway(Player player) {
        int px = (x + 2) % dungeon.size;
        int py = (y + 1) % dungeon.size;
        player.currentLocation = dungeon.grid[px][py];
        int mx = (x - 2 + dungeon.size) % dungeon.size;
        int my = (y - 1 + dungeon.size) % dungeon.size;
        search:
        while (!(dungeon.grid[mx][my] instanceof EmptyRoom)) {
            for (int i = 0; i < dungeon.size; i++) {
                mx = (mx + 1) % dungeon.size;
                if (dungeon.grid[mx][my] instanceof EmptyRoom) {
                    break search;
                }
            }
            my = (my + 1) % dungeon.size;
        }
        dungeon.addRoom(RoomType.MAELSTROM, mx, my);
        dungeon.addRoom(RoomType.EMPTY, x, y);
        player.displayRoom();


    }
}


enum RoomType {
    EMPTY, ENTRANCE, FOUNTAIN, PIT, MAELSTROM
}