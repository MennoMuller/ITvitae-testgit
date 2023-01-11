import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiOld6 extends JFrame {

    private JList leftList, rightList;
    private JButton moveButton;
    private static String[] foods = {"bacon","wings","ham","beef","more bacon"};

    public GuiOld6(){
        super("title");
        setLayout(new FlowLayout());

        leftList = new JList(foods);
        leftList.setVisibleRowCount(3);
        leftList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        add(new JScrollPane(leftList));

        moveButton = new JButton("Move -->");
        moveButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        rightList.setListData(leftList.getSelectedValuesList().toArray());
                    }
                }
        );
        add(moveButton);

        rightList = new JList();
        rightList.setVisibleRowCount(3);
        rightList.setFixedCellHeight(15);
        rightList.setFixedCellWidth(100);
        rightList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        add(new JScrollPane(rightList));

    }
}
