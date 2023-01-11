import java.util.Random;

public class Rafting {
    public void stroomversnelling() throws InRiverException {
        Random random = new Random();
        System.out.println("Stroomversnelling");
        if (random.nextInt(100) > 50) {
            System.out.println("plons");
            throw new InRiverException();
        }
        System.out.println("Door de stroomversnelling heen");
    }

    public void roei() throws LostPaddleException {
        Random random = new Random();
        System.out.println("Roeien");
        if (random.nextInt(100) > 80) {
            System.out.println("spaan verloren");
            throw new LostPaddleException();
        }
        System.out.println("Geen exception voor roeien");
    }

    public void betalen() {
        System.out.println("Betalen voor de raft");
    }
}

class InRiverException extends Exception {
}

class LostPaddleException extends Exception {
}