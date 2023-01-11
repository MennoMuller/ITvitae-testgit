package com.mennomuller;

import java.util.Scanner;

public class Main {
    private enum State {
        OPEN,
        CLOSED,
        LOCKED
    }

    public static void main(String[] args) {
        State chestStatus = State.LOCKED;
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.print("The chest is " + chestStatus.toString().toLowerCase() + ". What do you want to do? ");
            String command = input.next();
            switch (command){
                case "unlock":
                    if(chestStatus == State.LOCKED){
                        chestStatus = State.CLOSED;
                    }
                    break;
                case "lock":
                    if(chestStatus == State.CLOSED){
                        chestStatus = State.LOCKED;
                    }
                    break;
                case "open":
                    if(chestStatus == State.CLOSED){
                        chestStatus = State.OPEN;
                    }
                    break;
                case "close":
                    if(chestStatus == State.OPEN){
                        chestStatus = State.CLOSED;
                    }
                    break;
                case "exit":
                    return;
            }
        }
    }
}
