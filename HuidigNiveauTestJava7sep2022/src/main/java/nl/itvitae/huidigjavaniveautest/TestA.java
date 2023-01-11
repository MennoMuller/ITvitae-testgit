package nl.itvitae.huidigjavaniveautest;

import java.util.Objects;

import static nl.itvitae.huidigjavaniveautest.BaseTest.*;

public class TestA {

    public static void testOpgaveA1() {
        OpgavenA.opgave1();
    }

    public static void testOpgaveA2() {
        var firstAnswer = OpgavenA.opgave2(5, 6);
        var secondAnswer = OpgavenA.opgave2(3, 7);
        test(firstAnswer == 11 && secondAnswer == 10, "A2");
    }

    public static void testOpgaveA3() {
        var firstResult = OpgavenA.opgave3(3, 4);
        var secondResult = OpgavenA.opgave3(19, 19);
        var thirdResult = OpgavenA.opgave3(313, 76);
        test(Objects.equals(firstResult, secondResult) && Objects.equals(firstResult,
                "Voor elk kind is er minstens 1 appel!")
                && Objects.equals(thirdResult, "We hebben meer appels nodig!"), "A3");
    }

    public static void testOpgaveA4() {
        test(Objects.equals(OpgavenA.opgave4(4), ("1234")) &&
                Objects.equals(OpgavenA.opgave4(7), "1234567"), "A4");
    }

    public static void testOpgaveA5() {
        var languages = OpgavenA.opgave5();
        test(languages.size() == 3 &&
                Objects.equals(languages.get(0), "C") &&
                Objects.equals(languages.get(1), "C++") && Objects.equals(languages.get(2), "Java"), "A5");
    }

    public static void testOpgaveA6() {
        var car = OpgavenA.opgave6();
        test(Objects.equals(car.getBrand(), "Jaguar"), "A6");
    }
}
