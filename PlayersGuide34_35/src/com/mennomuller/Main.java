package com.mennomuller;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Robot robot = new Robot();
        for (int i = 0; i < 3; i++) {
            robot.setCommand(askCommand(), i);
        }
        robot.run();
    }

    enum Commands {
        ON, OFF, NORTH, SOUTH, EAST, WEST
    }

    private static RobotCommand askCommand() {
        //Validated input for commands.
        Scanner input = new Scanner(System.in);
        Commands answer = null;
        do {
            try {
                answer = Commands.valueOf(input.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Caution: invalid command. Please try again.");
            }
        } while (answer == null);
        RobotCommand robotCommand = null;
        switch (answer) {
            case ON -> robotCommand = new OnCommand();
            case OFF -> robotCommand = new OffCommand();
            case NORTH -> robotCommand = new NorthCommand();
            case SOUTH -> robotCommand = new SouthCommand();
            case EAST -> robotCommand = new EastCommand();
            case WEST -> robotCommand = new WestCommand();
        }
        return robotCommand;
    }
}

class Robot {
    private int x;
    private int y;
    private boolean isPowered;
    private final RobotCommand[] commands = new RobotCommand[3];

    public void setCommand(RobotCommand command, int index) {
        commands[index] = command;
    }

    public void run() {
        for (RobotCommand command : commands) {
            command.run(this);
            System.out.printf("[%d %d %b]\n", x, y, isPowered);
        }
    }

    public void changeX(int x) {
        this.x += x;
    }

    public void changeY(int y) {
        this.y += y;
    }

    public boolean isPowered() {
        return isPowered;
    }

    public void setPowered(boolean powered) {
        isPowered = powered;
    }
}

interface RobotCommand {
    void run(Robot robot);
}

class OnCommand implements RobotCommand {
    public void run(Robot robot) {
        robot.setPowered(true);
    }
}

class OffCommand implements RobotCommand {
    public void run(Robot robot) {
        robot.setPowered(false);
    }
}

class NorthCommand implements RobotCommand {
    public void run(Robot robot) {
        if (robot.isPowered()) {
            robot.changeY(1);
        }
    }
}

class SouthCommand implements RobotCommand {
    public void run(Robot robot) {
        if (robot.isPowered()) {
            robot.changeY(-1);
        }
    }
}

class EastCommand implements RobotCommand {
    public void run(Robot robot) {
        if (robot.isPowered()) {
            robot.changeX(1);
        }
    }
}

class WestCommand implements RobotCommand {
    public void run(Robot robot) {
        if (robot.isPowered()) {
            robot.changeX(-1);
        }
    }
}

