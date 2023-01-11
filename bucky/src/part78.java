import java.io.File;

public class part78 {
    public static void main(String[] args) {
        File x = new File("C:\\test\\greg.txt");

        if(x.exists()){
            System.out.println(x.getName()+" exists!");
        }else{
            System.out.println("This thing doesn't exist!");
        }
    }
}
