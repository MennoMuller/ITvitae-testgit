package com.mennomuller.rooms;

import com.mennomuller.Dungeon;
import com.mennomuller.util.TextHandler;

import java.util.ArrayList;

public class FountainRoom extends Room {

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
