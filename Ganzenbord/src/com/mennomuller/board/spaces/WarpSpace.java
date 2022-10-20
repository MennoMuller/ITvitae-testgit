package com.mennomuller.board.spaces;

import com.mennomuller.gamepieces.Goose;
import com.mennomuller.util.TextHandler;

public class WarpSpace extends Space {
    private int warpTo;
    private String message;
    private SpaceType type;

    public WarpSpace(int id, int warpTo, SpaceType type) {
        super(id);
        this.warpTo = warpTo;
        this.type = type;
        switch (type) {
            case DEATH -> message = "died!";
            case THORNY_BUSH -> message = "got lost in the thorny bush.";
            case BRIDGE -> message = "crossed the bridge.";
        }
    }

    @Override
    public String toString() {
        String s = "";
        switch (type) {
            case BRIDGE -> s = TextHandler.color("[/--\\]", TextHandler.Color.BLUE);
            case THORNY_BUSH -> s = TextHandler.color("[&&&&]", TextHandler.Color.GREEN);
            case DEATH -> s = TextHandler.color("[(X)>]", TextHandler.Color.GRAY);
        }
        return s;
    }

    @Override
    public void landOn(Goose goose) {
        if (id == 58) {
            passOver(goose, 31);
            passOver(goose, 52);
        }
        System.out.println("The " + goose.getColor() + " goose " + message);
        goose.getGame().board.spaces[warpTo].landOn(goose);
    }
} //brug, doornstruik, dood
