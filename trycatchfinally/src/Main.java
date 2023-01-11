public class Main {
    public static void main(String[] args) {
        Rafting rafting = new Rafting();
        try {
            rafting.roei();
            rafting.stroomversnelling();
        } catch (InRiverException e) {
            System.out.println("Klim terug aan boord met touw of roeispaan");
        } catch (LostPaddleException e) {
            System.out.println("Raak niet in paniek en blijf zitten");
        } finally {
            rafting.betalen();
        }
    }
}
