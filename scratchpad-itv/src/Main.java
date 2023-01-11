import mennomuller.Hello;


public class Main {
    static boolean isLampAan = false;

    public static void main(String[] args) {
        lichtSchakelaar();
        new Hello().Hallo();
//        /* This is a Javadoc comment
//         */
//        //This is a comment
////        System.out.print("Hello,\n World!");
////        System.out.println("Hello, \tWorld!");
////        Vogel v = new Vogel();
////        System.out.println(v);
////        int a;
////        a = 3;
////        a = a + 1;
////        String hello = "HelloWorld";
////        System.out.println(hello);
////        char l = 65;
////        System.out.println(l);
////        int b = 20, c;
////        //a en b verwisselen
////        c = a;
////        a = b;
////        b = c;
////        System.out.println(a);
////        System.out.println(b);
////        //lichtIsAan if statement
////        boolean lichtIsAan = true;
////
////        if (lichtIsAan) {
////            System.out.println("het licht is aan");
////        } else if (3 == 5) {
////            System.out.println("blah blah blah");
////        } else {
////            System.out.println("het licht is uit");
////        }
////        //dagnummer if/else statement
////        int dagNummer = 47;
////        if (dagNummer == 0) {
////            System.out.println("Zondag");
////        } else if (dagNummer == 1) {
////            System.out.println("Maandag");
////        } else if (dagNummer == 2) {
////            System.out.println("Dinsdag");
////        } else if (dagNummer == 3) {
////            System.out.println("Woensdag");
////        } else if (dagNummer == 4) {
////            System.out.println("Donderdag");
////        } else if (dagNummer == 5) {
////            System.out.println("Vrijdag");
////        } else if (dagNummer == 6) {
////            System.out.println("Zaterdag");
////        } else {
////            System.out.println("Geen geldige dag");
////        }
////        //enhanced switch statement
////        switch (dagNummer) {
////            case 0 -> System.out.println("Zondag");
////            case 1 -> System.out.println("Maandag");
////            case 2 -> System.out.println("Dinsdag");
////            case 3 -> System.out.println("Woensdag");
////            case 4 -> System.out.println("Donderdag");
////            case 5 -> System.out.println("Vrijdag");
////            case 6 -> System.out.println("Zaterdag");
////            default -> System.out.println("Geen geldige dag");
////        }
////        //for loop demonstratie
////        for (int i = 0; i < 10; i++) {
////
////            if (i == 5) {
////                System.out.println("BLAH BLAH BLAH");
////                continue;
////            }
////            System.out.println("i is nu " + i);
////        }
////        //opdracht for loops
////        for (int i = 1; i < 8; i++) {
////            System.out.println(i + " " + (i + 1) + " " + (i + 2));
////        }
////        //schaakbord
////        for (int row = 8; row > 0; row--) {
////            for (int col = 1; col < 9; col++) {
////                System.out.print("(" + row + "," + col + ")");
////            }
////            System.out.print("\n");
////        }
//        //scanner
//        //Vraag gebruiker om naam, groet naam.
//        Scanner input = new Scanner(System.in);
////        System.out.println("What is your name? ");
////        String name = input.nextLine();
////        if (name.isBlank()) {
////            name = "World";
////        }
////        System.out.println("Hello, " + name + "!");
//
//        //opdracht 2
//        //Nog een opdracht
//        int getal = -999;
//
//        while (getal <= 0) {
//            System.out.print("Voer een geheel positief getal in: ");
//            getal = input.nextInt();
//            if (getal < 0) {
//                System.out.println("Negatieve getallen zijn niet toegestaan.");
//            } else if (getal == 0) {
//                System.out.println("Nul is niet toegestaan.");
//            }
//        }
//
//        int sumOdd = 0;
//
//        for (int i = 1; i <= getal; i += 2) {
//            sumOdd += i;
//        }
//        System.out.println("som van oneven getallen tot en met " + getal + " is " + sumOdd);
//
//        int sumEven = 0;
//
//        for (int i = 2; i <= getal; i += 2) {
//            sumEven += i;
//        }
//        System.out.println("som van even getallen tot en met " + getal + " is " + sumEven);
//        System.out.println("verschil tussen twee sommen is " + Math.abs(sumOdd - sumEven));
    }

    static void lampAan() {
        if (!isLampAan) {
            isLampAan = true;
            System.out.println("De lamp is aan");
        } else {
            System.out.println("De lamp staat al aan");
        }
    }

    static void lampUit() {
        if (isLampAan) {
            isLampAan = false;
            System.out.println("De lamp is uit");
        } else {
            System.out.println("De lamp staat al uit");
        }
    }

    static void lichtSchakelaar() {
        System.out.println("Klik! De schakelaar is ingedrukt");
        if (isLampAan) {
            lampUit();
        } else {
            lampAan();
        }
    }
}


class ElectricDevice {
    boolean isOn; // whether or not the device is currently running
    final int serialNumber; //an unique number assigned to each individual device when it is manufactured
    static double globalPowerUsage; //total amount of power used by all devices worldwide
    static final int VOLTAGE = 230;

    ElectricDevice(int serialNumber) {
        this.serialNumber = serialNumber;
    }
}

class Notebook {
    String label;
    final String color;
    static int amountOfBlankNotebooksIOwn;
    static final int PAGES_PER_BOOK = 500;

    Notebook(String color) {
        this.color = color;
    }
}
