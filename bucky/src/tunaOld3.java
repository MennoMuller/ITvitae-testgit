public class tunaOld3 {
    private String name;
    private potpieOld birthday;

    public tunaOld3(String theName, potpieOld theDate){
        name = theName;
        birthday = theDate;
    }

    public String toString(){
        return String.format("My name is %s, and my birthday is %s",name,birthday);
    }
}
