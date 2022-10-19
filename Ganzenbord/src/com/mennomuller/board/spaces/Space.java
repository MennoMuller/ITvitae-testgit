package com.mennomuller.board.spaces;

import com.mennomuller.TextHandler;
import com.mennomuller.gamepieces.Goose;

public class Space {
    int id;

    private Goose occupant = null;

    @Override
    public String toString() {
        return TextHandler.color("[ ", TextHandler.Color.YELLOW) + (id < 10 ? "0" + id : id) + TextHandler.color(" ]", TextHandler.Color.YELLOW);
    }

    public Goose getOccupant() {
        return occupant;
    }

    public void clearSpace(Goose goose) {
        occupant = null;
    }

    public Space(int id) {
        this.id = id;
    }

    public void passOver(Goose goose, int wellpos) {
        Goose prisoner = goose.getGame().board.spaces[wellpos].getOccupant();
        if (prisoner == null || !prisoner.stuck) {
            return;
        }
        if (id > wellpos && goose.getPosition() < wellpos) {
            prisoner.stuck = false;
            System.out.println("The " + prisoner.getColor() + " goose has been freed!");
        }
    }

    public void landOn(Goose goose) {
        if (occupant == null || occupant.equals(goose)) {
            passOver(goose, 31);
            passOver(goose, 52);
            goose.setPosition(id);
            System.out.println("The " + goose.getColor() + " goose landed on space " + id + ".");
            this.occupant = goose;
        } else {
            System.out.println("The " + goose.getColor() + " goose tried to go to space " + id +
                    ", but " + occupant.getColor() + " was already there!");
            goose.getGame().board.spaces[goose.getPosition()].landOn(goose);
        }
    }

    public enum SpaceType {
        BRIDGE, THORNY_BUSH, DEATH, WELL, JAIL
    }
}
