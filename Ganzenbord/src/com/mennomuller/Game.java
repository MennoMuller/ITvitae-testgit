package com.mennomuller;

import com.mennomuller.board.Board;
import com.mennomuller.gamepieces.Dice;
import com.mennomuller.gamepieces.Goose;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    public Dice dice1 = new Dice();
    public Dice dice2 = new Dice();
    public Goose[] players;
    public Board board;
    Scanner input = new Scanner(System.in);
    public Goose winner = null;
    public ArrayList<Goose> rankOrder = new ArrayList<>();

    public boolean getPlayersBefore(int pos) {

        for (Goose player : players) {
            if (player.getPosition() < pos) {
                return true;
            }
        }
        return false;
    }

    public Game() {
        System.out.print("How many players? ");
        int numOfPlayers = input.nextInt();
        players = new Goose[numOfPlayers];
        for (int i = 0; i < numOfPlayers; i++) {
            System.out.println("Player " + (i + 1) + ", what color is your goose?");
            players[i] = new Goose(input.next());
            players[i].setGame(this);
        }
        input.nextLine();
        this.decidePlayerOrder();
        board = new Board(this);
    }

    public void decidePlayerOrder() {
        ArrayList<Goose> players2 = new ArrayList<>(List.of(players));
        int startingPlayer = dice1.random.nextInt(players.length);
        for (int i = 0; i < startingPlayer; i++) {
            players2.add(players2.get(0));
            players2.remove(0);
        }
        for (int i = 0; i < players.length; i++) {
            players[i] = players2.get(i);
        }
    }

    public void play(boolean singleWinner) {
        do {
            for (Goose player : players) {
                if ((singleWinner && winner == null) || !rankOrder.contains(player)) {
                    System.out.println("It's the " + player.getColor() + " goose's turn.");
                    board.printBoard(player.getPosition(), 13);
                    input.nextLine();
                    player.takeTurn();

                }
            }
        } while (singleWinner && winner == null || rankOrder.size() < players.length);
        System.out.println("The " + winner.getColor() + " goose wins!");
        if (!singleWinner) {
            for (int i = 0; i < rankOrder.size(); i++) {
                System.out.println((i + 1) + ": " + rankOrder.get(i).getColor());
            }
        }
    }
}

