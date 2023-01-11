public class potpieOld {
    private int month,day,year;

    public potpieOld(int d, int m, int y){
        month=m;
        day = d;
        year = y;

        System.out.printf("The constructor for this is %s\n", this);
    }

    public String toString(){
        return String.format("%d/%d/%d",day,month,year);
    }
}
