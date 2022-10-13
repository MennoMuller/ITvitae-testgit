package com.mennomuller;

public class Main {

    public static void main(String[] args) {
        Rafting raft = new Rafting(10.0);
        do {

            try {
                for (int i = 0; i < 10; i++) {
                    raft.roei();
                }
                raft.stroomversnelling(50.0);
            } catch (InRivierException e) {
                System.out.println("Je valt overboord!");
                raft.panic(5);
                System.out.println("De instructeur legt de boot stil.");
                raft.manOverboord();
            } catch (RoeispaanVerlorenException e) {
                System.out.println("Je verliest je roeispaan!");
                raft.panic(3);
                if (raft.getPaniek() < 15) {
                    System.out.println("Het lukt je om kalm te blijven en te blijven zitten.");
                } else {
                    System.out.println("Je raakt in paniek en springt erachteraan!");
                    System.out.println("De instructeur legt de boot stil.");
                    raft.manOverboord();
                }
            }
        } while (raft.getAfstand() < 1000);
        System.out.println("Je ziet een aanlegplaats naast de rivier. \nDe instructeur stuurt het vlot ernaartoe en legt aan.");
        System.out.println("Hij vertelt je dat je " + raft.getTijd() * 3 + " euro moet betalen voor "
                + raft.getTijd() + " minuten raften" +
                (raft.heeftRoeispaan() ? "." : ", plus 50 euro voor een nieuwe roeispaan."));
    }
}
