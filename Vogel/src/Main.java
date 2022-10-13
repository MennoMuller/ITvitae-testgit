public class Main {
    public static void main(String[] args) {
        Kip kip = new Kip("Tokker");
        Papegaai papegaai = new Papegaai("Lorre");
        Kip haan = new Haan("Kukel");
        Pinguin ping = new Pinguin("Pingo",true);
        papegaai.vliegen();
        papegaai.praten();
        papegaai.praten("Dit is een overload");
        kip.vliegen();
        kip.tokken();
        haan.vliegen();
        haan.tokken();
        ping.vliegen();
        ping.takeAwayJetpack();
        ping.vliegen();
        ping.giveJetpack();
        ping.vliegen();
    }
}
