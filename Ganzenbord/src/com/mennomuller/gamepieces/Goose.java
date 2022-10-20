package com.mennomuller.gamepieces;

import com.mennomuller.Game;
import com.mennomuller.util.TextHandler;

public class Goose {
    private TextHandler.Color enumColor;
    private int position = 0;
    public boolean skipTurn = false;
    public boolean goingForward = true;
    public boolean startedMoving = false;
    public boolean stuck = false;
    private final String color;

    @Override
    public String toString() {
        return TextHandler.color("<(" + (color.length() >= 3 ? color.substring(0, 3) : color + " ".repeat(3 - color.length())) + ")", enumColor);
    }

    public String getHead() {
        return TextHandler.color("  (O)>", enumColor);
    }

    //              (O)>  (O)>
//            <(red)<(blu)
//Start>[ 01 ][ 02 ][ 03 ][ 04 ][(O)>][/--\][ 07 ][ 08 ][(O)>][ 10 ][ 11 ][ 12 ]
    public String getColor() {
        return color;
    }

    public Goose(String color) {
        this.color = color;
        try {
            enumColor = TextHandler.Color.valueOf(color.toUpperCase());
        } catch (IllegalArgumentException e) {
            enumColor = TextHandler.Color.WHITE;
        }
    }

    private Game game;

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }


    public void setPosition(int position) {
        game.board.spaces[this.position].clearSpace(this);
        this.position = position;
        this.goingForward = true;
        if (position != 0) {
            this.startedMoving = true;
        }
    }

    public int getPosition() {
        return position;
    }

    public void takeTurn() {
        if (!skipTurn && !stuck) {
            System.out.println("The " + color + " goose is rolling the dice.");
            int target = position + game.dice1.roll() + game.dice2.roll();
            try {
                game.board.spaces[target].landOn(this);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Too far, going backwards.");
                goingForward = false;
                target = 63 - (target - 63);
                game.board.spaces[target].landOn(this);
            }
        } else if (skipTurn) {
            System.out.println("The " + color + " goose skipped a turn.");
            skipTurn = false;
        } else {
            System.out.println("The " + color + " goose is still stuck in " + (position == 31 ? "the well." : "jail."));
            if (!game.getPlayersBefore(position)) {
                System.out.println("No players behind detected, next turn " + color + " will move again.");
                stuck = false;
            }
        }
    }
}

