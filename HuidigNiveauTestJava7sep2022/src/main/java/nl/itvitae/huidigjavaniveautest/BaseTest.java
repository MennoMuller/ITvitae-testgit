package nl.itvitae.huidigjavaniveautest;

public class BaseTest {

    static void succeed(String testId) {
        System.out.println("Opgave " + testId + " is geslaagd!");
    }

    static void fail(String testId) {
        throw new RuntimeException("Opgave " + testId + " faalde!");
    }

    static void test(boolean isCorrect, String testId) {
        if (isCorrect) succeed(testId);
        else fail(testId);
    }
}
