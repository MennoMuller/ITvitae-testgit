import javax.swing.*;

public class part84 {
    public static void main(String[] args) {
        JFrame f = new JFrame("Title");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        PeachOld p = new PeachOld();
        f.add(p);
        f.setSize(400,250);
        f.setVisible(true);
    }
}
