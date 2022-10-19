package com.mennomuller.board.spaces;

import com.mennomuller.Game;
import com.mennomuller.TextHandler;
import com.mennomuller.gamepieces.Goose;

import java.util.ArrayList;
import java.util.List;

public class StartSpace extends Space {
    ArrayList<Goose> occupants;

    @Override
    public String toString() {
        return TextHandler.color("Start>", TextHandler.Color.GREEN);
    }

    @Override
    public Goose getOccupant() {
        if (occupants.size() == 0) {
            return null;
        } else if (occupants.size() > 1) {
            return new Goose(Integer.toString(occupants.size()));
        } else {
            return occupants.get(0);
        }
    }

    public StartSpace(int id, Game game) {
        super(id);
        occupants = new ArrayList<>(List.of(game.players));


    }

    @Override
    public void clearSpace(Goose goose) {
        occupants.remove(goose);
    }

    @Override
    public void landOn(Goose goose) {
        goose.setPosition(id);
        System.out.println("The " + goose.getColor() + " goose went back to the beginning.");
        occupants.add(goose);
    }
}