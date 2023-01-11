package nl.itvitae.huidigjavaniveautest;

public class Main {
    /*
     * Huidig-Niveau-Test
     *
     * Als je door de rood/groene opdrachten heen bent gekomen, ben je bijna zeker in staat om
     * betaald programmeur te worden.
     *
     * Deze test/assessment is dus niet om te bepalen of je programmeur kan worden. Dit is om je te laten zien welke
     * onderdelen je op dit moment nog moet oefenen, en hoe goed het staat met je 'vingervlugheid'.
     *
     * Programmeren is immers niet alleen begrijpen, maar ook 'automatiseren'. Het gaat er meestal niet
     * om dat je iets uiteindelijk kan programmeren, maar dat het snel genoeg kan. Dat je niet 2 werkweken
     * nodig hebt waar andere programmeurs 2 uur over doen. Dit automatiseren en 'zetten in het
     * lange-termijn werkgeheugen' kost veel tijd en oefenen.
     *
     * Maar natuurlijk moeten de oefeningen van het goede niveau zijn; te makkelijk en je verspilt tijd,
     * te moeilijk en je verspilt ook tijd omdat je exponentieel langzaam programmeert - als je 1 regel per 2 uur
     * schrijft helpt dat niet veel een snelle(re) programmeur te worden!
     *
     * Deze huidig-niveautest is om te zien welke oefeningen in deze fase van je kennis het meest efficiënt
     * zijn. Er zijn drie onderdelen (A, B, en C) in oplopende moeilijkheidsgraad.
     *
     * Ik raad aan eerst de opgaven van A te doen, dan die van B, en tenslotte de C-opdracht.
     *
     * !Als je wil/kan: noteer hoeveel tijd het afmaken van A kostte. Doe dat ook voor B en C.
     *
     * Als je klaar bent (of als de twee uur om zijn), lever je code in, zodat we advies op maat kunnen geven.
     *
     */

    public static void main(String[] args) {
        System.out.println("Start de tests");
        // ADVIES: haal van de volgende regels 1 voor 1 de // weg, en zorg dat het gaat werken.
        try {
            TestA.testOpgaveA1();
            TestA.testOpgaveA2();
            TestA.testOpgaveA3();
            TestA.testOpgaveA4();
            TestA.testOpgaveA5();
            TestA.testOpgaveA6();

//            TestB.testOpgaveB1();
            TestB.testOpgaveB2();
            TestB.testOpgaveB3();
            TestB.testOpgaveB4();
            TestB.testOpgaveB5();
            TestB.testOpgaveB6();
            TestB.testOpgaveB7();
            TestB.testOpgaveB8();

            TestC.testOpgaveC();

            System.out.println("Tests zijn klaar");
        } catch(Exception e ) {
            System.out.println("Er is een fout opgetreden: " + e.getMessage());
        }
    }
}
