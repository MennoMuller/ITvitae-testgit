package com.mennomuller.rooms;

import com.mennomuller.Dungeon;
import com.mennomuller.util.TextHandler;

import java.util.ArrayList;

public class PitRoom extends Room {
    public PitRoom(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
    }

    @Override
    public String getWarning() {
        return "You feel a draft. There is a pit in a nearby room.";
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
