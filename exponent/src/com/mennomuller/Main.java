package com.mennomuller;

public class Main {

    public static void main(String[] args) {
        System.out.println(exponent(3, -3));
        System.out.println(OddEvenTest.isOdd(4));
        System.out.println(OddEvenTest.isOdd(7));
        System.out.println(OddEvenTest.isOdd(-4));
        System.out.println(OddEvenTest.isOdd(-7));
    }

    public static int exponent(int base, int exp) {
        if (exp >= 0) {
            int result = 1;
            for (int i = 0; i < exp; i++) {
                result *= base;
            }
            return result;
        } else {
            return 0;//int can't handle fractions so there's no use in writing something to handle negative exponents.
        }
    }
}

class OddEvenTest {
    public static boolean isOdd(int number) {
        return number % 2 != 0;
    }
}
