package nl.itvitae.huidigjavaniveautest;

public class OpgaveC {

    /* Turing 0.1
     *
     * Omdat volgens Turing computers simpele dingen zijn, die werken zolang je kan optellen en springen,
     * besluit iemand bij ITvitae een Turingmachine te bouwen.
     *
     * De code voor de machine bestaat uit brokken van vier getallen.
     *
     * Het eerste getal geeft het type instructie aan: stoppen (0), optellen (1) of springen (2)
     *
     * Zowel optel-instructies als spring-instructies worden gevolgd door drie getallen, die de positie
     * geven van het eerste argument, de positie van het tweede argument, en de positie van het derde argument.
     *
     * Dus:
     *
     * operatie positie_eerste_argument positie_tweede_argument positie_derde_argument
     *
     * Optellen is simpel: op de positie aangegeven door het derde argument wordt de som van de getallen
     * die op de posities van het eerste en tweede argument staan aangegeven neergezet. Als het optellen is
     * afgerond, gaat het programma verder naar het volgende instructiebrok (dus 4 posities verder dan het
     * begin van de optelinstructie)
     *
     * "1 10 11 15" betekent dus: tel het getal dat op positie 10 staat op bij het getal op positie 11, en
     * schrijf het resultaat naar positie 15
     *
     * Springen is iets ingewikkelder: als het getal op de locatie aangegeven door het eerste argument
     * kleiner is dan het getal op de locatie aangegeven door het tweede argument, springt het programma
     * naar de locatie aangegeven door het derde argument; anders gaat het programma gewoon verder naar de
     * volgende instructie.
     *
     * "2 5 7 20" betekent dus: als het getal dat op positie 5 staat kleiner is dan het getal dat op positie 7 staat,
     * spring naar positie 20. Als het getal op positie 5 groter of gelijk is aan dat op positie 7, ga dan gewoon
     * door naar het volgende brok van 4 instructies.
     *
     * Als voorbeeld een programma om het getal 2 tot een bepaalde macht te verheffen:
     *
     * 1, 0, 13, 13, 1, 14, 14, 14, 2, 13, 15, 0, 0, 0, 1, 4
     *
     * Als ik hierbij de posities weergeef (als goede programmeurs beginnen we bij 0 te tellen)
     *
     *  !0: 1       1: 0        2: 13       3: 13   // dit is de eerste instructie: 1, 0, 13, 13
     *  4: 1        5: 14       6: 14       7: 14   // dit is de tweede instructie: 1, 14, 14, 14
     *  8: 2        9: 13       10: 15      11: 0   // dit is de derde instructie: 2, 13, 15, 0
     *  12:0        13: 0       14: 1       15: 4   // dit is de vierde instructie: 0, 0, 1, 4
     *
     * We beginnen bij positie 0 (aangegeven door de !): 1 betekent "optellen". We tellen het getal
     * op locatie 0, wat 1 is [we gebruiken de 1 dus zowel als instructietype voor optellen als als waarde!]
     * op bij het getal op locatie 13, wat 0 is, en slaan de som daarvan, 1, op op locatie 13.
     * Dan verschuiven we de programmateller gewoon naar de volgende positie, 4.
     * De reeks ziet er nu uit als
     *
     *  0: 1        1: 0        2: 13       3: 13   // dit is de eerste instructie: 1, 0, 13, 13
     *  !4: 1       5: 14       6: 14       7: 14   // dit is de tweede instructie: 1, 14, 14, 14
     *  8: 2        9: 13       10: 15      11: 0   // dit is de derde instructie: 2, 13, 15, 0
     *  12:0        13: 1       14: 1       15: 4   // dit is de vierde instructie: 0, 0, 1, 4
     *
     * We gaan door met de volgende instructie, op plaats 4. Dit is ook optellen, het getal op locatie 14,
     * wat 1 is, wordt opgeteld bij zichzelf. De som is dus 2, en die som wordt teruggezet op locatie 14.
     * Dan wordt de programmapointer opgeschoven naar het begin van de volgende instructie, positie 8.
     * De reeks komt er dan uit te zien als
     *
     *  0: 1        1: 0        2: 13       3: 13   // dit is de eerste instructie: 1, 0, 13, 13
     *  4: 1        5: 14       6: 14       7: 14   // dit is de tweede instructie: 1, 14, 14, 14
     *  !8: 2       9: 13       10: 15      11: 0   // dit is de derde instructie: 2, 13, 15, 0
     *  12:0        13: 1       14: 2       15: 4   // dit is de vierde instructie: 0, 0, 1, 4
     *
     * De volgende instructie (op psitie 8) is een 2, dus een sprong. Het getal op locatie 13, wat 1 is,
     * wordt vergeleken met het getal op locatie 15, wat 4 is. 1 is kleiner dan 4, dus de programmawijzer
     * wordt teruggezet op naar de positie aangegeven door het derde argument (positie 11, die 0 bevat).
     * De programmawijzer gaat dus terug naar het 0 / het begin!
     *
     *  !0: 1       1: 0        2: 13       3: 13   // dit is de eerste instructie: 1, 0, 13, 13
     *  4: 1        5: 14       6: 14       7: 14   // dit is de tweede instructie: 1, 14, 14, 14
     *  8: 2        9: 13       10: 15      11: 0   // dit is de derde instructie: 2, 13, 15, 0
     *  12:0        13: 1       14: 2       15: 4   // dit is de vierde instructie: 0, 0, 1, 4
     *
     *  Weer wordt 1 bij het getal op 13 opgeteld (tot 2) het getal op 14 bij zichzelf (tot 4), en 2
     *  wordt met 4 vergeleken.
     *  De programmawijzer wordt daarom teruggezet op 0. 1 wordt opgeteld bij het getal op 13 (tot 3),
     *  14 bij zichzelf (tot 8), en 3 wordt met 4 vergeleken. Tenslotte wordt 1 opgeteld bij het getal op 13
     *  (tot 4), het getal op 14 wordt bij zichzelf opgeteld (tot 16). Dan wordt het getal op 13 vergeleken
     *  met het getal op 15. De getallen zijn nu allebei 4, dus er wordt niet meer gesprongen, maar gewoon
     *  doorgegaan met de instructie op 12. Dit is een 0, dus het programma stopt, met als eindtoestand
     *
     *  0: 1        1: 0        2: 13       3: 13   // dit is de eerste instructie: 1, 0, 13, 13
     *  4: 1        5: 14       6: 14       7: 14   // dit is de tweede instructie: 1, 14, 14, 14
     *  8: 2        9: 13       10: 15      11: 0   // dit is de derde instructie: 2, 13, 15, 0
     *  !12:0       13: 4       14: 16      15: 4   // dit is de vierde instructie: 0, 0, 1, 4
     *
     *  Hierbij zijn 13 operaties uitgevoerd (4x 0, 4 en 8, en 1x 12, wat het programma stopt)
     *
     *  Natuurlijk zijn ook grotere programma's mogelijk. Van het programma in OpgaveCInput, wat is
     *  het produkt van het aantal stappen dat het programma neemt voordat het ophoudt, en het nieuwe
     *  getal op de vijfde positie (index 5)?
     *
     *  Bij het bovenstaande programma zou de methode dus het element op index 5 (14) maal het aantal
     *  uitgevoerde stappen (13), dus (14 * 13 =) 182 terug moeten geven.
     */
    public static int get5CodeTimesNumSteps() {
        int finalNumberAtPosition5 = 0;
        int numberOfStepsTaken = 1;
        int[] program = {2, 209, 133, 92, 2, 339, 364, 132, 1, 94, 5, 217, 2, 290, 291, 124, 1, 41, 393, 266, 1, 344, 112, 345, 1, 116, 29, 237, 2, 331, 163, 224, 1, 93, 34, 325, 1, 280, 215, 149, 2, 372, 53, 208, 2, 349, 321, 172, 1, 216, 156, 213, 1, 20, 197, 247, 2, 104, 30, 340, 1, 155, 148, 130, 2, 95, 24, 152, 2, 85, 122, 284, 2, 274, 376, 336, 1, 133, 352, 322, 2, 142, 138, 72, 2, 82, 229, 120, 2, 97, 327, 232, 1, 184, 150, 150, 2, 338, 65, 340, 2, 279, 353, 296, 1, 2, 68, 151, 1, 213, 237, 29, 2, 218, 38, 388, 1, 130, 163, 325, 1, 326, 241, 11, 2, 15, 279, 288, 2, 338, 229, 60, 1, 52, 339, 90, 2, 140, 125, 104, 2, 100, 41, 160, 1, 24, 121, 374, 1, 186, 177, 395, 1, 47, 57, 269, 1, 320, 278, 265, 2, 266, 341, 368, 1, 91, 397, 63, 2, 61, 221, 96, 1, 388, 235, 113, 2, 240, 26, 256, 1, 25, 95, 353, 1, 9, 150, 57, 2, 118, 107, 80, 1, 365, 40, 253, 1, 177, 119, 333, 1, 354, 192, 17, 2, 9, 37, 216, 1, 32, 300, 69, 2, 321, 256, 64, 2, 150, 279, 320, 0, 236, 253, 201, 2, 264, 286, 120, 1, 398, 248, 123, 2, 342, 336, 120, 1, 15, 368, 155, 2, 270, 366, 80, 1, 241, 71, 178, 1, 167, 211, 289, 2, 36, 135, 288, 1, 156, 329, 273, 1, 44, 101, 235, 1, 106, 27, 366, 1, 163, 70, 90, 1, 261, 59, 377, 1, 142, 275, 305, 1, 49, 123, 393, 1, 215, 265, 187, 2, 136, 280, 248, 2, 377, 20, 108, 1, 274, 3, 5, 1, 286, 189, 397, 2, 82, 162, 28, 2, 219, 175, 164, 2, 54, 274, 120, 2, 180, 72, 28, 2, 57, 1, 0, 2, 101, 206, 384, 1, 89, 21, 397, 2, 21, 329, 260, 2, 384, 396, 380, 1, 369, 271, 373, 2, 190, 97, 236, 1, 228, 294, 201, 2, 320, 302, 332, 2, 49, 27, 200, 2, 12, 17, 280, 2, 332, 282, 52, 1, 248, 59, 269, 2, 334, 282, 100, 1, 59, 38, 285, 1, 320, 65, 117, 2, 235, 11, 128, 1, 154, 305, 301, 2, 96, 245, 204, 2, 248, 27, 332};
        int pointer = 0;
        while (program[pointer] != 0) {
            if (program[pointer] == 1) {
                //optellen
                program[program[pointer + 3]] = program[program[pointer + 1]] + program[program[pointer + 2]];
                pointer += 4; //move on to next instruction

            } else if (program[pointer] == 2) {
                //springen
                if (program[program[pointer + 1]] < program[program[pointer + 2]]) {
                    pointer = program[pointer + 3];
                } else {
                    pointer += 4; //move on to next instruction
                }
            }
            numberOfStepsTaken++;
        }
        finalNumberAtPosition5 = program[5];

        return finalNumberAtPosition5 * numberOfStepsTaken;
    }
}

