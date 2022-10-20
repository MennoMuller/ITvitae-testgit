package com.mennomuller.rooms;

import com.mennomuller.Dungeon;
import com.mennomuller.util.TextHandler;

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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String location() {
        return TextHandler.color("You are in the room at (x: " + x + ", y: " + y + ").", TextHandler.Color.WHITE);
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
                    String s = dungeon.getGrid()[i][j].getWarning();
                    if (!s.equals("")) {
                        warnings.add(TextHandler.color(s, TextHandler.Color.RED));
                    }
                }
            }
        }
        hints.addAll(warnings);
        return hints;
    }

    public String getWarning() {
        return "";
    }

    public enum RoomType {
        EMPTY, ENTRANCE, FOUNTAIN, PIT, MAELSTROM, AMAROK
    }
}













