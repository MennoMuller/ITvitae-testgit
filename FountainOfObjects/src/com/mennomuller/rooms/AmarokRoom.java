package com.mennomuller.rooms;

import com.mennomuller.Dungeon;
import com.mennomuller.util.TextHandler;

import java.util.ArrayList;

public class AmarokRoom extends Room {

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

    @Override
    public String getWarning() {
        return "You can smell the rotten stench of an amarok in a nearby room.";
    }
}
