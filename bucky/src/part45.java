import java.util.EnumSet;

public class part45 {
    public static void main(String[] args) {
        for(tunaOld4 people: tunaOld4.values()){
            System.out.printf("%s\t%s\t%s\n",people,people.getDesc(),people.getYear());
        }
        System.out.println("\nAnd now for the range of constants!!!\n");

        for(tunaOld4 people: EnumSet.range(tunaOld4.kelsey, tunaOld4.candy)){
            System.out.printf("%s\t%s\t%s\n",people,people.getDesc(),people.getYear());
        }
    }
}
