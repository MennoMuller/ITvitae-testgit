import javax.swing.*;
import java.awt.*;

public class Calculator extends JFrame {
    private JButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9,buttonTimes,buttonDiv,buttonPlus,buttonMinus,buttonComma,buttonPower,buttonRoot,buttonPercent,buttonEuro,buttonEquals;
    private JTextField prevBox,currBox;
    private GridBagLayout layout;
    private GridBagConstraints c;

    public Calculator(){
        super("Menno's Calculator v0.1");
        layout = new GridBagLayout();
        setLayout(layout);
        c = new GridBagConstraints();

        //previous formula field
        prevBox = new JTextField("previous formula");
        prevBox.setEditable(false);
        c.fill=GridBagConstraints.BOTH;
        c.weightx=0.5;
        c.weighty=0.5;
        c.gridx=0;
        c.gridy=0;
        c.gridwidth=5;
        add(prevBox,c);

        //current formula field
        currBox = new JTextField("current formula/result");
        c.gridy=1;
        add(currBox,c);

        //7 button
        button7 = new JButton("7");
        c.gridwidth=1;
        c.gridy=2;
        add(button7,c);

        //8 button
        button8 = new JButton("8");
        c.gridx=1;
        add(button8,c);

        //9 button
        button9 = new JButton("9");
        c.gridx=2;
        add(button9,c);

        //+ button
        buttonPlus = new JButton("+");
        c.gridx=3;
        add(buttonPlus,c);

        //- button
        buttonMinus = new JButton("-");
        c.gridx=4;
        add(buttonMinus,c);

        //4 button
        button4 = new JButton("4");
        c.gridx=0;
        c.gridy=3;
        add(button4,c);

        //5 button
        button5 = new JButton("5");
        c.gridx=1;
        c.gridy=3;
        add(button5,c);

        //6 button
        button6 = new JButton("6");
        c.gridx=2;
        c.gridy=3;
        add(button6,c);

        //× button
        buttonTimes = new JButton("×");
        c.gridx=3;
        c.gridy=3;
        add(buttonTimes,c);

        //÷ button
        buttonDiv = new JButton("÷");
        c.gridx=4;
        c.gridy=3;
        add(buttonDiv,c);

        //1 button
        button1 = new JButton("1");
        c.gridx=0;
        c.gridy=4;
        add(button1,c);

        //2 button
        button2 = new JButton("2");
        c.gridx=1;
        c.gridy=4;
        add(button2,c);

        //3 button
        button3 = new JButton("3");
        c.gridx=2;
        c.gridy=4;
        add(button3,c);

        //^ button
        buttonPower = new JButton("^");
        c.gridx=3;
        c.gridy=4;
        add(buttonPower,c);

        //√ button
        buttonRoot = new JButton("√");
        c.gridx=4;
        c.gridy=4;
        add(buttonRoot,c);

        //, button
        buttonComma = new JButton(",");
        c.gridx=0;
        c.gridy=5;
        add(buttonComma,c);

        //0 button
        button0 = new JButton("0");
        c.gridx=1;
        c.gridy=5;
        add(button0,c);

        //= button
        buttonEquals = new JButton("=");
        buttonEquals.setBackground(Color.GREEN);
        c.gridx=2;
        c.gridy=5;
        add(buttonEquals,c);

        //% button
        buttonPercent = new JButton("%");
        c.gridx=3;
        c.gridy=5;
        add(buttonPercent,c);

        //€ button
        buttonEuro = new JButton("€");
        c.gridx=4;
        c.gridy=5;
        add(buttonEuro,c);




    }



}
