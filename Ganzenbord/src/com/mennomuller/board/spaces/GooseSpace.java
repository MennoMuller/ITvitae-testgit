package com.mennomuller.board.spaces;

import com.mennomuller.Game;
import com.mennomuller.gamepieces.Goose;

public class GooseSpace extends Space {
    public GooseSpace(int id) {
        super(id);
    }

    @Override
    public String toString() {
        return "[(O)>]";
    }

    @Override
    public void landOn(Goose goose) {
        Game game = goose.getGame();
        if (id == 9 && !goose.startedMoving) {
            int roll = game.dice1.getLastRoll();
            if (roll == 4 || roll == 5) {
                System.out.println("Rolled a 4 and 5! Going to 53.");
                goose.getGame().board.spaces[53].landOn(goose);
            } else {
                System.out.println("Rolled a 3 and 6! Going to 26.");
                goose.getGame().board.spaces[26].landOn(goose);
            }
        } else {

            int target;
            if (goose.goingForward) {
                target = id + game.dice1.getLastRoll() + game.dice2.getLastRoll();
                System.out.println("Goose space! Going ahead to " + target + ".");
                if (target > 63) {
                    target = 63 - (target - 63);
                    goose.goingForward = false;
                    System.out.println("Too far, going backwards to " + target + ".");
                }
            } else {
                target = id - game.dice1.getLastRoll() - game.dice2.getLastRoll();
                System.out.println("Goose space! Going back to " + target + ".");
            }
            goose.getGame().board.spaces[target].landOn(goose);
        }
    }
} // ganzenvakjes

