package com.mennomuller.board.spaces;

import com.mennomuller.TextHandler;
import com.mennomuller.gamepieces.Goose;

public class InnSpace extends Space {
    public InnSpace(int id) {
        super(id);
    }

    @Override
    public String toString() {
        return TextHandler.color("[", TextHandler.Color.YELLOW) + TextHandler.color("|^^|", TextHandler.Color.RED) + TextHandler.color("]", TextHandler.Color.YELLOW);
    }

    @Override
    public void landOn(Goose goose) {
        super.landOn(goose);
        if (getOccupant().equals(goose)) {
            System.out.println("The " + goose.getColor() + " goose is staying at the inn.");
            goose.skipTurn = true;
        }
    }
} //herberg
