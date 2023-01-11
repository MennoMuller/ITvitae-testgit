public class part41 {
    public static void main(String[] args) {

        tunaOld2 tunaObject = new tunaOld2();
        tunaOld2 tunaObject2 = new tunaOld2(5);
        tunaOld2 tunaObject3 = new tunaOld2(5,13);
        tunaOld2 tunaObject4 = new tunaOld2(5,13,43);

        System.out.printf("%s\n", tunaObject.toMilitary());
        System.out.printf("%s\n", tunaObject2.toMilitary());
        System.out.printf("%s\n", tunaObject3.toMilitary());
        System.out.printf("%s\n", tunaObject4.toMilitary());

    }
}
