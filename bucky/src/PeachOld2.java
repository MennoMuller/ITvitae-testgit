import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PeachOld2 extends JFrame {
    private JButton b;
    private Color color = (Color.WHITE);
    private JPanel panel;
    public PeachOld2(){
        super("the title");
        panel = new JPanel();
        panel.setBackground(color);

        b = new JButton("Choose a color");
        b.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        color = JColorChooser.showDialog(null,"Pick your color",color);
                        if(color==null){
                            color=(Color.WHITE);
                        }
                        panel.setBackground(color);
                    }
                }
        );

        add(panel, BorderLayout.CENTER);
        add(b, BorderLayout.SOUTH);
        setSize(425,150);
        setVisible(true);
    }
}
