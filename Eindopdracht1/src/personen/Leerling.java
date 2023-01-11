package personen;

public class Leerling {
    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    private int age;
    private String name;

    public Leerling(int age, String name) {
        this.age = age;
        this.name = name;
    }
}
