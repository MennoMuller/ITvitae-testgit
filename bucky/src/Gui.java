import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Gui extends JFrame {
    private String details;
    private JLabel statusBar;

    public Gui(){
        super("turtle");

        statusBar = new JLabel("this is default");
        add(statusBar, BorderLayout.SOUTH);
        addMouseListener(new MouseClass());
    }

    private class MouseClass extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e) {
            details = String.format("You clicked %dx ", e.getClickCount());

            if(SwingUtilities.isRightMouseButton(e)){
                details += "with right mouse button";
            } else if(SwingUtilities.isMiddleMouseButton(e)){
                details += "with center mouse button";
            } else {
                details += "with left mouse button";
            }
            statusBar.setText(details);
        }
    }
}
