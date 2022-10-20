package com.mennomuller.rooms;

import com.mennomuller.Dungeon;
import com.mennomuller.Player;
import com.mennomuller.util.TextHandler;

import java.util.ArrayList;

public class MaelstromRoom extends Room {

    public MaelstromRoom(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
    }

    @Override
    public void shoot() {
        TextHandler.printlnColor("Hit! The arrow pierces the core of the maelstrom, and you hear it dissipate.", TextHandler.Color.MAGENTA);
        dungeon.addRoom(Room.RoomType.EMPTY, x, y);
    }

    @Override
    public ArrayList<String> hints() {
        ArrayList<String> hints = super.hints();
        hints.add(1, TextHandler.color("There's a maelstrom in this room! You are blown away to a different room.", TextHandler.Color.MAGENTA));
        hints.add(2, "Blow");
        return hints;
    }

    @Override
    public String getWarning() {
        return "You hear the growling and groaning of a maelstrom nearby.";
    }

    public void blowAway(Player player) {
        int px = (x + 2) % dungeon.getSize().width;
        int py = (y + 1) % dungeon.getSize().width;
        player.setCurrentLocation(dungeon.getGrid()[px][py]);
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
        dungeon.addRoom(Room.RoomType.MAELSTROM, mx, my);
        dungeon.addRoom(Room.RoomType.EMPTY, x, y);
        player.displayRoom();
    }
}
