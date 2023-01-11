package nl.itvitae.huidigjavaniveautest;

import java.util.Objects;

import static nl.itvitae.huidigjavaniveautest.BaseTest.*;

public class TestB {

    public static void testOpgaveB1() {
        GuessingGame.Guess();
        test(true, "B1");
    }

    static void testOpgaveB2() {
        var seedling = Flower.getSeedling();
        test(!seedling.isBlooming() && seedling.getHeight() == 1, "B2");
    }

    static void testOpgaveB3() {
        var seedling = Flower.getSeedling();
        for (int i = 0; i < 4; i++) seedling.water();
        if (!seedling.isBlooming() && seedling.getHeight() == 5) {
            for (int i = 0; i < 44; i++) seedling.water();
            if (!seedling.isBlooming() && seedling.getHeight() == 49) {
                seedling.water();
                if (seedling.isBlooming() && seedling.getHeight() == 50) {
                    succeed("B3");
                    return;
                }
            }
        }
        fail("B3");
    }

    static void testOpgaveB4() {
        var seedling = Flower.getSeedling();
        for (int i = 0; i < 4; i++) seedling.water();
        if (Objects.equals(seedling.report(), "De plant is 5 centimeter groot, en bloeit niet.")) {
            for (int i = 0; i < 44; i++) seedling.water();
            if (Objects.equals(seedling.report(), "De plant is 49 centimeter groot, en bloeit niet.")) {
                seedling.water();
                if (Objects.equals(seedling.report(), "De plant is 50 centimeter groot, en bloeit.")) {
                    succeed("B4");
                    return;
                }
            }
        }
        fail("B4");
    }

    static void testOpgaveB5() {
        var programmingCoach = new ProgrammingCoach();
        test(programmingCoach.getTotalLines() == 331, "B5");
    }

    static void testOpgaveB6() {
        var programmingCoach = new ProgrammingCoach();
        programmingCoach.addTodaysLines(100);
        test(programmingCoach.getTotalLines() == 431, "B6");
    }

    static void testOpgaveB7() {
        var programmingCoach = new ProgrammingCoach();
        test(programmingCoach.getCountYesterday() == 97, "B7");
    }

    static void testOpgaveB8() {
        var firstProgrammingCoach = new ProgrammingCoach();
        var secondProgrammingCoach = new ProgrammingCoach();
        secondProgrammingCoach.addTodaysLines(200);
        secondProgrammingCoach.addTodaysLines(10);

        test(firstProgrammingCoach.mostSuccessfulDayReport().equals("De meeste regels code schreef ik op dag 3, en dat waren er 104.") &&
                secondProgrammingCoach.mostSuccessfulDayReport().equals("De meeste regels code schreef ik op dag 6, en dat waren er 200."), "B8");
    }
}
