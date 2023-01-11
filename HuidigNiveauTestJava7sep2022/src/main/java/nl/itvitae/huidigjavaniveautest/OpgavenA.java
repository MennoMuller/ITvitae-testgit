package nl.itvitae.huidigjavaniveautest;

import nl.itvitae.huidigjavaniveautest.utils.NotYetImplementedException;

import java.util.ArrayList;
import java.util.List;

public class OpgavenA {
    public static void opgave1() {
        // maak een variabele 'character' en ken er de waarde 'b' aan toe

        // [vul hier de code in om de variabele te maken]
        char character = 'b';
        // Als je de code wilt testen, 'oncommentaar' de regels hieronder,
        // verwijder de '//' (bijvoorbeeld door de regels te selecteren en
        // op Ctrl + / te drukken

        if (character != 'b') throw new RuntimeException("Opgave A1 faalt!");
        System.out.println("Opgave A1 is geslaagd!");
    }

    public static int opgave2(int eersteGetal, int tweedeGetal) {
        // geef de som terug van het eerste getal en het tweede getal
        // (maak de code hierbeneden kloppend)
        // Als je klaar bent, verwijder de regel met throw new NotYetImplementedException.
        return eersteGetal + tweedeGetal;

    }

    public static String opgave3(int kinderen, int appels) {
        // Als er meer appels dan kinderen zijn (of evenveel appels als kinderen), geef de tekst
        // "Voor elk kind is er minstens 1 appel!" terug
        // Geef anders "We hebben meer appels nodig!" terug
        // Hint: gebruik "if", "else" en "return" om de boodschap terug te geven
        // Als je klaar bent, verwijder de regel met throw new NotYetImplementedException.
        if (appels >= kinderen) {
            return "Voor elk kind is er minstens 1 appel!";
        } else {
            return "We hebben meer appels nodig!";
        }
    }

    public static String opgave4(int n) {
        // Maak een string die de getallen 1 tot en met n teruggeeft in een string. Als n 4 is,
        // moet de methode dus "1234" teruggeven.
        // Hint: een for-loop is hier handig, net zoals weten dat je iets aan een string-variabele
        // vast kan plakken door iets als "tekst += extraTekst" te gebruiken
        // Als je klaar bent, verwijder de regel met throw new NotYetImplementedException.
        String opg4 = "";
        int i;
        for (i = 1; i <= n; i++) {
            opg4 += i;
        }
        return opg4;
    }

    public static List<String> opgave5() {
        // maak een lijst van strings die de namen van programmeertalen bevat, en geef die terug
        // met een return.
        // in de lijst moet "C", "C++" en "Java" zitten (in die volgorde)
        // Als je klaar bent, verwijder de regel met throw new NotYetImplementedException.
        List<String> languages = new ArrayList<>();
        languages.add("C");
        languages.add("C++");
        languages.add("Java");
        return languages;

    }

    public static class Car {
        private final String brand;

        public Car(String brand) {
            this.brand = brand;
        }

        public String getBrand() {
            return brand;
        }
    }

    public static Car opgave6() {
        // Zorg dat je een auto teruggeeft met als merk "Jaguar"
        // Als je klaar bent, verwijder de regel met throw new NotYetImplementedException.

        return new Car("Jaguar");
    }
}
