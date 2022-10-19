package com.mennomuller.board;

import com.mennomuller.Game;
import com.mennomuller.board.spaces.*;
import com.mennomuller.gamepieces.Goose;

public class Board {
    public Space[] spaces = new Space[64];
    Game game;

    public void printBoard(int start, int length) {
        StringBuilder line1 = new StringBuilder();
        StringBuilder line2 = new StringBuilder();
        StringBuilder line3 = new StringBuilder();
        for (int i = start; i < start + length; i++) {
            if (i >= spaces.length) {
                break;
            }
            Goose g = spaces[i].getOccupant();
            if (g == null) {
                int j = i - start;
                line1.append("      ");
                line2.append("  ").append(j < 10 ? " " + j : j).append("  ");
            } else {
                line1.append(g.getHead());
                line2.append(g);
            }
            line3.append(spaces[i]);
        }
        System.out.println(line1);
        System.out.println(line2);
        System.out.println(line3);
    }

    public Board(Game game) {
        this.game = game;
        for (int i = 0; i < 64; i++) {
            switch (i) {
                case 63 -> spaces[i] = new EndSpace(i, game);
                case 0 -> spaces[i] = new StartSpace(i, game);
                case 6 -> spaces[i] = new WarpSpace(i, 12, Space.SpaceType.BRIDGE);
                case 42 -> spaces[i] = new WarpSpace(i, 37, Space.SpaceType.THORNY_BUSH);
                case 58 -> spaces[i] = new WarpSpace(i, 0, Space.SpaceType.DEATH);
                case 19 -> spaces[i] = new InnSpace(i);
                case 31 -> spaces[i] = new WellJailSpace(i, Space.SpaceType.WELL);
                case 52 -> spaces[i] = new WellJailSpace(i, Space.SpaceType.JAIL);
                case 5, 9, 14, 18, 23, 27, 32, 36, 41, 45, 50, 54, 59 -> spaces[i] = new GooseSpace(i);
                default -> spaces[i] = new Space(i);
            }
        }
    }
}
