public class tunaOld6 {
    private int sum;
    private final int NUMBER;

    public tunaOld6(int x){
        NUMBER = x;
    }
    public void add(){
        sum+=NUMBER;
    }
    public String toString(){
        return String.format("sum = %d\n", sum);
    }
}
