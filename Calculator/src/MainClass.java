import javax.swing.*;

public class MainClass {
    public static void main(String[] args) {
        Calculator go = new Calculator();
        go.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        go.setSize(300,200);
        go.setVisible(true);
    }
}
