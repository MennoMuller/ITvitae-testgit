package com.mennomuller.rooms;

import com.mennomuller.Dungeon;
import com.mennomuller.util.TextHandler;

import java.util.ArrayList;

public class EntranceRoom extends Room {

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
