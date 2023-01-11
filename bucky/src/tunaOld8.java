import javax.swing.*;
import java.awt.*;

public class tunaOld8 extends JFrame {

    private JLabel item1;

    public tunaOld8(){
        super("The title bar");
        setLayout(new FlowLayout());

        item1 = new JLabel("this is a sentence");
        item1.setToolTipText("This is gonna show up on hover");
        add(item1);
    }

}
