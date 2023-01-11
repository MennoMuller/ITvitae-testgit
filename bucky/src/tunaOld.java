public class tunaOld {
    public void simpleMessage(String name){
        System.out.println("Hello " + name);
    }
    private String girlName;
    private int hour, minute, second;

    public tunaOld(String name){
        girlName = name;
    }

    public void setGirlName(String name){
        girlName = name;
    }
    public String getGirlName(){
        return girlName;
    }

    public void saying(){
        System.out.printf("Your first girlfriend was %s\n", getGirlName());
    }


    public void setTime(int h, int m, int s){
        hour = ((h>=0 && h<24) ? h : 0);
        minute = ((m>=0 && m<60) ? m : 0);
        second = ((s>=0 && s<60) ? s : 0);
    }

    public String toMilitary(){
        return String.format("%02d:%02d:%02d",hour,minute,second);
    }

    public String toAMPM(){
        return String.format("%d:%02d:%02d %s", ((hour==0||hour==12)?12:hour%12),minute,second,(hour<12?"AM":"PM"));
    }
}
