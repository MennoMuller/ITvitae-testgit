package com.mennomuller;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        game.play(false);
        // write your code here
    }
}

class Game {
    Dice dice1 = new Dice();
    Dice dice2 = new Dice();
    Goose[] players;
    Board board;
    Scanner input = new Scanner(System.in);
    Goose winner = null;
    ArrayList<Goose> rankOrder = new ArrayList<>();

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

class Dice {
    private int lastRoll;
    Random random = new Random();

    public int getLastRoll() {
        return lastRoll;
    }

    int roll() {
        lastRoll = random.nextInt(6) + 1;
        System.out.println("Rolled a " + lastRoll + "!");
        return lastRoll;
    }
}

class Goose {
    private Color enumColor;
    private int position = 0;
    boolean skipTurn = false;
    boolean goingForward = true;
    boolean startedMoving = false;
    boolean stuck = false;
    private String color;

    @Override
    public String toString() {
        return TextHandler.color("<(" + (color.length() >= 3 ? color.substring(0, 3) : color + " ".repeat(3 - color.length())) + ")",enumColor);
    }

    public String getHead() {
        return TextHandler.color("  (O)>",enumColor);
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
            enumColor = Color.valueOf(color.toUpperCase());
        } catch (IllegalArgumentException e) {
            enumColor = Color.WHITE;
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

class Space {
    int id;

    private Goose occupant = null;

    @Override
    public String toString() {
        return TextHandler.color("[ ",Color.YELLOW) + (id < 10 ? "0" + id : id) + TextHandler.color(" ]",Color.YELLOW);
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

    void landOn(Goose goose) {
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

    enum SpaceType {
        BRIDGE, THORNY_BUSH, DEATH, WELL, JAIL
    }
}

class Board {
    Space[] spaces = new Space[64];
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
                line2.append("  " + (j < 10 ? " " + j : j) + "  ");
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

class GooseSpace extends Space {
    public GooseSpace(int id) {
        super(id);
    }

    @Override
    public String toString() {
        return "[(O)>]";
    }

    @Override
    void landOn(Goose goose) {
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

class InnSpace extends Space {
    public InnSpace(int id) {
        super(id);
    }

    @Override
    public String toString() {
        return TextHandler.color("[",Color.YELLOW)+TextHandler.color("|^^|",Color.RED)+TextHandler.color("]",Color.YELLOW);
    }

    @Override
    void landOn(Goose goose) {
        super.landOn(goose);
        if (getOccupant().equals(goose)) {
            System.out.println("The " + goose.getColor() + " goose is staying at the inn.");
            goose.skipTurn = true;
        }
    }
} //herberg

class WellJailSpace extends Space {
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
            case JAIL -> s = TextHandler.color("[[##]]",Color.GRAY);
            case WELL -> s = TextHandler.color("[(())]",Color.RED);
        }
        return s;
    }

    @Override
    void landOn(Goose goose) {
        super.landOn(goose);
        if (getOccupant().equals(goose)) {
            System.out.println("The " + goose.getColor() + " goose got stuck in " + message + ".");
            goose.stuck = true;
        }
    }
} //put, gevangenis

class WarpSpace extends Space {
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
            case BRIDGE -> s = TextHandler.color("[/--\\]",Color.BLUE);
            case THORNY_BUSH -> s = TextHandler.color("[&&&&]",Color.GREEN);
            case DEATH -> s = TextHandler.color("[(X)>]",Color.GRAY);
        }
        return s;
    }

    @Override
    void landOn(Goose goose) {
        if (id == 58) {
            passOver(goose, 31);
            passOver(goose, 52);
        }
        System.out.println("The " + goose.getColor() + " goose " + message);
        goose.getGame().board.spaces[warpTo].landOn(goose);
    }
} //brug, doornstruik, dood

class EndSpace extends StartSpace {

    public EndSpace(int id, Game game) {
        super(id, game);
        occupants = new ArrayList<>();
    }

    @Override
    public String toString() {
        return TextHandler.color("[!!63!!]",Color.MAGENTA);
    }

    @Override
    void landOn(Goose goose) {
        goose.setPosition(id);
        System.out.println("The " + goose.getColor() + " goose made it to the finish!");
        occupants.add(goose);
        if (goose.getGame().winner == null) {
            goose.getGame().winner = goose;
        }
        goose.getGame().rankOrder.add(goose);
    }
}

class StartSpace extends Space {
    ArrayList<Goose> occupants;

    @Override
    public String toString() {
        return TextHandler.color("Start>",Color.GREEN);
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
    void landOn(Goose goose) {
        goose.setPosition(id);
        System.out.println("The " + goose.getColor() + " goose went back to the beginning.");
        occupants.add(goose);
    }
}
