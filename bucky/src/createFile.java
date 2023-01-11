import java.util.Formatter;

public class createFile {
    private Formatter x;

    public void openFile(){
        try{
            x = new Formatter("blorb.txt");
        }
        catch (Exception e){
            System.out.println("You have an Air");
        }
    }

    public void addRecords(){
        x.format("%s%s%s", "20 ", "bucky ", "roberts");
    }

    public void closeFile(){
        x.close();
    }
}
