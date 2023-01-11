import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class GuiOld7 extends JFrame {
    private JPanel mousePanel;
    private JLabel statusBar;

    public GuiOld7(){
        super("title");

        mousePanel = new JPanel();
        mousePanel.setBackground(Color.WHITE);
        add(mousePanel, BorderLayout.CENTER);

        statusBar = new JLabel("default");
        add(statusBar, BorderLayout.SOUTH);

        HandlerClass handler = new HandlerClass();
        mousePanel.addMouseListener(handler);
        mousePanel.addMouseMotionListener(handler);
    }

    private class HandlerClass implements MouseListener, MouseMotionListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            statusBar.setText(String.format("Clicked at %d,%d",e.getX(),e.getY()));
        }

        @Override
        public void mousePressed(MouseEvent e) {
            statusBar.setText("You pressed down the mouse!");
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            statusBar.setText("You released the button!");
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            statusBar.setText("You entered the area!");
            mousePanel.setBackground(Color.RED);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            statusBar.setText("The mouse has left the window!");
            mousePanel.setBackground(Color.WHITE);
        }
        // these are MouseMotionListener methods
        @Override
        public void mouseDragged(MouseEvent e) {
            statusBar.setText("You are dragging the mouse!");
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            statusBar.setText("You moved the mouse!");
        }
    }
}
