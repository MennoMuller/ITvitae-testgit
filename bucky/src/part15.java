import java.util.Scanner;

public class part15 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        tunaOld tunaObject = new tunaOld("Null-chan");

        System.out.println("Enter your name here: ");
        String name = input.nextLine();

        tunaObject.simpleMessage(name);
    }
}
