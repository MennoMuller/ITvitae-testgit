package nl.itvitae.huidigjavaniveautest;

import nl.itvitae.huidigjavaniveautest.utils.NotYetImplementedException;

import java.util.*;

class GuessingGame {
    // Opgave B1
    // Implementeer een raad-spel.
    // De computer bedenkt een geheim nummer van 1 tot en met 4 (daarvoor is al code geschreven)
    // De gebruiker moet een getal raden. Als het getal fout is, krijgt de gebruiker de feedback
    // "Dat was niet het getal dat ik bedacht had!", en moet hij of zij opnieuw raden tot hij het
    // goed heeft. Anders ziet de gebruiker "Goed gedaan!" en stopt de methode.
    // Als je klaar bent, verwijder de regel met throw new NotYetImplementedException.
    // Hint: invoer van de gebruiker kan je inlezen met Scanner in = new Scanner(System.in); int guess = in.nextInt();
    public static void Guess() {
        int secretNumber = new Random().nextInt(4) + 1;
        System.out.println("Raad het getal! (tussen 1 en 5)");
        Scanner input = new Scanner(System.in);
        int guessNumber = input.nextInt();
        while (guessNumber != secretNumber) {
            System.out.println("Dat was niet het getal dat ik bedacht had!");
            guessNumber = input.nextInt();
        }
        System.out.println("Goed gedaan!");

    }
}

class Flower {
    // houd deze code (de height en isBlooming-velden en de Flower-constructor hetzelfde)
    private int height;

    private boolean isBlooming;

    public int getHeight() {
        return height;
    }

    public boolean isBlooming() {
        return isBlooming;
    }

    private Flower(int height, boolean isBlooming) {
        this.height = height;
        this.isBlooming = isBlooming;
    }

    // opgave B2: implementeer de getSeedling() methode zodat Flower.getSeedling() een
    // bloem oplevert van 1 centimeter die (nog) niet bloeit.
    // Als je klaar bent, verwijder de regel met throw new NotYetImplementedException.
    public static Flower getSeedling() {
        return new Flower(1, false);
    }

    // opgave B3: implementeer de water-methode die de plant 1 centimeter laat groeien.
    // Als de plant 50 centimeter of hoger is gaat hij bloeien.
    // Als je klaar bent, verwijder de regel met throw new NotYetImplementedException.
    public void water() {
        height++;
        isBlooming = height >= 50;
    }

    // opgave B4: implementeer de report() methode die laat zien hoe hoog de plant is,
    // en of hij bloeit. De methode geeft een string terug,
    // zoals "De plant is 5 centimeter groot, en bloeit niet."
    // (als hij wel bloeit, wordt dat uiteraard "De plant is 5 centimeter groot, en bloeit.")
    // Als je klaar bent, verwijder de regel met throw new NotYetImplementedException.
    public String report() {
        // vul hier de code in
        return "De plant is " + height + " centimeter groot, en bloeit" + (isBlooming ? "." : " niet.");

    }
}

// Frits is zijn programmeren aan het oefenen, en houdt elke dag het aantal regels bij dat hij heeft
// geschreven
class ProgrammingCoach {
    private List<Integer> linesOfCode = new ArrayList<>(Arrays.asList(15, 25, 70, 104, 20, 97));

    // Opgava B5: Frits wil weten hoeveel regels code hij in het totaal geschreven heeft
    // wijzig de getTotalLines-methode zo dat hij alle regels code die tot nog toe geschreven
    // zijn bij elkaar optelt en teruggeeft.
    // Als je klaar bent, verwijder de regel met throw new NotYetImplementedException.
    public int getTotalLines() {
        // vul hier de code in
        int total = 0;
        for (int i = 0; i < linesOfCode.size(); i++) {
            total += linesOfCode.get(i);
        }
        return total;
    }

    // Opgave B6: implementeer de methode "addTodaysLines" die de hoeveelheid regels die
    // vandaag geschreven zijn aan de lijst toevoegt.
    // Als je klaar bent, verwijder de regel met throw new NotYetImplementedException.
    public void addTodaysLines(int todaysLines) {
        linesOfCode.add(todaysLines);
    }

    // Opgave B7: implementeer getCountYesterday, welke teruggeeft hoeveel regels code je op de meest recente
    // (laatste) dag hebt geschreven.
    // Als je klaar bent, verwijder de regel met throw new NotYetImplementedException.
    public int getCountYesterday() {
        // vul hier de code in
        return linesOfCode.get(linesOfCode.size() - 1);

    }

    // Opgave B8: implementeer mostSuccessfulDayReport, wat afbeeldt op welke dag je de meeste code schreef zag, en
    // hoeveel regels code dat waren. Geef een string terug als "De meeste regels code schreef ik op dag 3, en dat waren er 104."
    // (uiteraard moet dat veranderen als je met addTodaysLines nieuwe records zou toevoegen.
    // LET OP! Als goede programmeurs beginnen we met 0 te tellen, dus dag 0, dag 1, dag 2, enzovoorts!
    // Als je klaar bent, verwijder de regel met throw new NotYetImplementedException.
    public String mostSuccessfulDayReport() {
        // vul hier de code in
        int best = -1;
        int bestDay = -1;
        for (int i = 0; i < linesOfCode.size(); i++) {
            if (linesOfCode.get(i) > best) {
                best = linesOfCode.get(i);
                bestDay = i;
            }
        }
        return "De meeste regels code schreef ik op dag " + bestDay + ", en dat waren er " + best + ".";
    }
}
