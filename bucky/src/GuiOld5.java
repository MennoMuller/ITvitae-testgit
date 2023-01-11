import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class GuiOld5 extends JFrame {
    private JList list;
    private static String[] colornames = {"black","blue","red","white"};
    private static Color[] colors = {Color.BLACK,Color.BLUE,Color.RED,Color.WHITE};

    public GuiOld5(){
        super("title");
        setLayout(new FlowLayout());

        list = new JList(colornames);
        list.setVisibleRowCount(4);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(list));

        list.addListSelectionListener(
                new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        getContentPane().setBackground(colors[list.getSelectedIndex()]);
                    }
                }
        );
    }
}
