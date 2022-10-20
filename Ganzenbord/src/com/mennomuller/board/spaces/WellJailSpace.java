package com.mennomuller.board.spaces;

import com.mennomuller.gamepieces.Goose;
import com.mennomuller.util.TextHandler;

public class WellJailSpace extends Space {
    String message;
    SpaceType type;

    public WellJailSpace(int id, SpaceType type) {
        super(id);
        this.type = type;
        if (type == SpaceType.JAIL) {
            message = "jail..";
        } else {
            message = "the well..";
        }
    }

    @Override
    public String toString() {
        String s = "";
        switch (type) {
            case JAIL -> s = TextHandler.color("[[##]]", TextHandler.Color.GRAY);
            case WELL -> s = TextHandler.color("[(", TextHandler.Color.RED) + TextHandler.color("()", TextHandler.Color.BLUE) + TextHandler.color(")]", TextHandler.Color.RED);
        }
        return s;
    }

    @Override
    public void landOn(Goose goose) {
        super.landOn(goose);
        if (getOccupant().equals(goose)) {
            System.out.println("The " + goose.getColor() + " goose got stuck in " + message + ".");
            goose.stuck = true;
        }
    }
} //put, gevangenis
//