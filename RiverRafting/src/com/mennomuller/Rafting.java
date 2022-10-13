package com.mennomuller;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

class InRivierException extends Exception {
}

class RoeispaanVerlorenException extends Exception {
}

public class Rafting {
    private double snelheid;
    private int paniek = 1;
    private int tijd = 0;
    private double afstand = 0.0;
    private Random random = new Random();
    private boolean heeftRoeispaan = true;

    public int getTijd() {
        return tijd;
    }

    public int getPaniek() {
        return paniek;
    }

    public double getAfstand() {
        return afstand;
    }

    public boolean heeftRoeispaan() {
        return heeftRoeispaan;
    }

    public Rafting(double snelheid) {
        this.snelheid = snelheid;
    }

    public void stroomversnelling(double stroomSnelheid) throws InRivierException, RoeispaanVerlorenException {
        System.out.println("Je ziet een stroomversnelling...");
        System.out.println("Je gaat de stroomversnelling in!");
        snelheid = (snelheid + stroomSnelheid) / 2;
        if (random.nextInt(80) <= snelheid + paniek) {
            throw new InRivierException();
        }
        System.out.println("Je bent door de stroomversnelling heen!");
        tijd += 3;
        afstand += 10.0;
        roei();
    }

    public void panic(int p) {
        if (paniek + p >= 1) {
            paniek += p;
        }
    }

    public void manOverboord() {
        manOverboord(false);
    }

    public void manOverboord(boolean roeispaan) {
        System.out.println("Je ligt in het water!");
        ArrayList<String> opties = new ArrayList<String>();
        if (!heeftRoeispaan && (random.nextInt(20) == 1 || roeispaan)) {
            System.out.println("Je ziet je roeispaan naast je drijven.");
            opties.add("Probeer je roeispaan te pakken");
        }
        opties.add("Probeer terug aan boord te klimmen");
        opties.add("Probeer te kalmeren");
        String keuze = kiesOpties(opties.toArray());
        switch (keuze) {
            case "Probeer terug aan boord te klimmen":
                if (heeftRoeispaan) {
                    System.out.println("Je probeert om met je roeispaan terug op het vlot te klimmen...");
                    if (random.nextInt(50) > paniek) {
                        System.out.println("Gelukt! Je bent weer terug op het vlot, en roeit verder.");
                        return;
                    } else {
                        System.out.println("...maar je valt weer terug in het water!");
                        System.out.println("Je raakt in paniek! (Paniek: " + ++paniek + ")");
                    }
                } else {
                    System.out.println("De instructeur gooit een touw naar je toe.");
                    System.out.println("Je probeert om via het touw weer aan boord te klimmen...");
                    if (random.nextInt(25) > paniek) {
                        System.out.println("Gelukt! Je bent weer terug op het vlot, maar nog steeds zonder roeispaan.");
                        return;
                    } else {
                        System.out.println("...maar je valt weer terug in het water!");
                        System.out.println("Je raakt in paniek! (Paniek: " + ++paniek + ")");
                    }
                }
                tijd += 5;
                break;
            case "Probeer te kalmeren":
                System.out.println("Je haalt even diep adem. Adem in, adem uit...");
                if (paniek > 2) {
                    paniek -= 2;
                } else if (paniek > 1) {
                    paniek = 1;
                }
                System.out.println("Je bent nu iets kalmer. (Paniek: " + paniek + ")");
                tijd += 5;
                break;
            case "Probeer je roeispaan te pakken":
                System.out.println("Je zwemt naar de roeispaan toe.");
                System.out.println("Je grijpt naar de roeispaan...");
                if (random.nextInt(30) > paniek) {
                    System.out.println("Je pakt de roeispaan vast!");
                    heeftRoeispaan = true;
                } else {
                    System.out.println("Mis! De roeispaan drijft weg.");
                    System.out.println("Je raakt in paniek! (Paniek: " + ++paniek + ")");
                }
                break;
            default:
                System.out.println("Je weet niet wat je moet doen!");
                System.out.println("Je raakt in paniek! (Paniek: " + ++paniek + ")");
        }
        manOverboord();

    }


    public void roei() throws RoeispaanVerlorenException {
        if (heeftRoeispaan) {
            if (random.nextInt(150) <= paniek) {
                heeftRoeispaan = false;
                throw new RoeispaanVerlorenException();
            } else {
                System.out.println("Roei, roei...");
                snelheid += 4;
                afstand += snelheid;
                tijd++;
            }
        } else {
            if (random.nextInt(10) == 1) {
                System.out.println("Je ziet je roeispaan drijven!");
                String keuze = kiesOpties("Spring in het water", "Probeer de spaan te pakken", "Blijf doorvaren");
                switch (keuze) {
                    case "Spring in het water":
                        System.out.println("Je springt in het water, achter de roeispaan aan!");
                        manOverboord(true);
                        break;
                    case "Probeer de spaan te pakken":
                        System.out.println("Je reikt naar de spaan...");
                        int roll = random.nextInt(100);
                        if (roll > 8 * paniek) {
                            System.out.println("...en trekt hem terug aan boord!");
                            heeftRoeispaan = true;
                        } else if (roll < paniek) {
                            System.out.println("...en valt in het water! PLONS!");
                            paniek += 5;
                            manOverboord(true);
                        } else {
                            System.out.println("...maar de spaan drijft weg.");
                        }
                        break;
                    default:
                        System.out.println("Je besluit om de roeispaan te laten gaan.");
                        System.out.println("Je drijft verder...");
                        snelheid += 1;
                        snelheid *= 0.95;
                        afstand += snelheid;
                        tijd++;
                        panic(-5);


                }
            } else {
                System.out.println("Drijf...");
                snelheid *= 0.8;
                afstand += snelheid;
                tijd++;
            }
        }
    }
// TOCHECKOUT: Optional
    public String kiesOpties(Object... opties) {
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < opties.length; i++) {
            System.out.println((i + 1) + ": " + opties[i]);
        }
        try {
            int answer = input.nextInt();
            if (answer > opties.length || answer <= 0) {
                return "null";
            } else {
                return opties[answer - 1].toString();
            }
        } catch (InputMismatchException e) {
            return "null";
        }
    }
}
