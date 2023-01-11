public class part36 {
    public static void main(String[] args) {
        tunaOld tunaObject = new tunaOld("Null-chan");
        System.out.println(tunaObject.toMilitary());
        tunaObject.setTime(13,27,6);
        System.out.println(tunaObject.toMilitary());
    }
}
