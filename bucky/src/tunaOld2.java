public class tunaOld2 {
    private int hour, minute, second;
    public tunaOld2(){
        this(0,0,0);
    }
    public tunaOld2(int h){
        this(h,0,0);
    }
    public tunaOld2(int h, int m){
        this(h,m,0);
    }
    public tunaOld2(int h, int m, int s){
        setTime(h,m,s);
    }
    public void setTime(int h, int m, int s){
        setHour(h);
        setMinute(m);
        setSecond(s);
    }

    public void setHour(int hour) {
        this.hour = ((hour>=0&&hour<24)?hour:0);
    }
    public void setMinute(int minute) {
        this.minute = ((minute>=0&&minute<60)?minute:0);
    }
    public void setSecond(int second) {
        this.second = ((second>=0&&second<60)?second:0);
    }

    public int getHour() {
        return hour;
    }
    public int getMinute() {
        return minute;
    }
    public int getSecond() {
        return second;
    }

    public String toMilitary(){
        return String.format("%02d:%02d:%02d", getHour(),getMinute(),getSecond());
    }
}
