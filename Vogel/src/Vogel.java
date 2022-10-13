public abstract class Vogel {
    int aantalVleugels;
    String naam;

    Vogel(String naam) {
        this.naam = naam;
    }

    void vliegen() {
        System.out.println("Ik ben aan het vliegen");
    }
}

class Kip extends Vogel {
    Kip(String naam) {
        super(naam);
    }

    @Override
    void vliegen() {
        System.out.println("Ik ben een kip, ik kan niet vliegen");
    }

    void tokken() {
        System.out.println("Tok tok");
    }
}

class Papegaai extends Vogel {
    Papegaai(String naam) {
        super(naam);
    }

    void praten() {
        System.out.println("Lorre lorre");
    }

    void praten(String text) {
        System.out.println(text + ", lorre lorre");
    }
}

class Pinguin extends Vogel {
    boolean hasJetpack;

    Pinguin(String naam, boolean jetpack) {
        super(naam);
        this.hasJetpack = jetpack;
    }

    @Override
    void vliegen() {
        if (hasJetpack) {
            System.out.println("Vroem vroem, een pinguin met een jetpack");
        } else {
            System.out.println("Vraag je nu serieus een pinguin om te vliegen?");
        }
    }

    void giveJetpack(){
        System.out.println("Hier heb je een jetpack, "+naam);
        this.hasJetpack = true;
    }

    void takeAwayJetpack(){
        System.out.println("Da's pech, jetpack weg");
        this.hasJetpack = false;
    }
}

class Haan extends Kip {

    Haan(String naam) {
        super(naam);
    }

    @Override
    void tokken() {
        System.out.println("Kukeleku!");
    }
}