package com.mennomuller.board.spaces;

import com.mennomuller.Game;
import com.mennomuller.TextHandler;
import com.mennomuller.gamepieces.Goose;

import java.util.ArrayList;

public class EndSpace extends StartSpace {

    public EndSpace(int id, Game game) {
        super(id, game);
        occupants = new ArrayList<>();
    }

    @Override
    public String toString() {
        return TextHandler.color("[!!63!!]", TextHandler.Color.MAGENTA);
    }

    @Override
    public void landOn(Goose goose) {
        goose.setPosition(id);
        System.out.println("The " + goose.getColor() + " goose made it to the finish!");
        occupants.add(goose);
        if (goose.getGame().winner == null) {
            goose.getGame().winner = goose;
        }
        goose.getGame().rankOrder.add(goose);
    }
}
