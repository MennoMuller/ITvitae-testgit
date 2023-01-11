package com.mennomuller;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        sierpinski(5);
    }

    static void sierpinski(int level) {
        for(int i=0;i<Math.pow(2,level);i++)
            System.out.println(sierpinskiIterate(level,i));
    }

    private static String sierpinskiIterate(int level,int line) {
        if (level == 0) {
            return ((char) 27 + "[4m/\\"+(char)27+"[m");
        } else {
            int halfLines = (int)Math.pow(2,level-1);
            if(line<halfLines){
                return " ".repeat((int) Math.pow(2,level-1))+sierpinskiIterate(level-1,line)+" ".repeat((int) Math.pow(2,level-1));
            } else {
                return sierpinskiIterate(level-1,line-halfLines).repeat(2);
            }

        }
    }
}
