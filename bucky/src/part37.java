public class part37 {
    public static void main(String[] args) {
        tunaOld tunaObject = new tunaOld("Null-chan");
        System.out.println(tunaObject.toMilitary());
        System.out.println(tunaObject.toAMPM());
        tunaObject.setTime(13,27,6);
        System.out.println(tunaObject.toMilitary());
        System.out.println(tunaObject.toAMPM());
    }
}
