import java.awt.*;
import java.awt.event.*;

public class MinimalCalculator extends Frame implements ActionListener {

    TextField tf;

    Button b0, b1, b2, b3, b4, b5, b6, b7, b8, b9;
    Button bAdd, bSub, bMul, bDiv, bEq, bClr;

    double num1, num2, result;
    String operator;

    MinimalCalculator() {

        setTitle("Calculator");
        setSize(320, 420);
        setLayout(null);

        // Very light pink background
        setBackground(new Color(255, 240, 245));

        // TextField
        tf = new TextField();
        tf.setBounds(30, 50, 250, 50);
        tf.setFont(new Font("Arial", Font.BOLD, 22));
        tf.setBackground(Color.white);
        tf.setForeground(new Color(120, 70, 90));
        add(tf);

        // Row 1
        b7 = createButton("7", 30, 130);
        b8 = createButton("8", 95, 130);
        b9 = createButton("9", 160, 130);
        bDiv = createButton("/", 225, 130);

        // Row 2
        b4 = createButton("4", 30, 190);
        b5 = createButton("5", 95, 190);
        b6 = createButton("6", 160, 190);
        bMul = createButton("*", 225, 190);

        // Row 3
        b1 = createButton("1", 30, 250);
        b2 = createButton("2", 95, 250);
        b3 = createButton("3", 160, 250);
        bSub = createButton("-", 225, 250);

        // Row 4
        b0 = createButton("0", 30, 310);
        bClr = createButton("C", 95, 310);
        bEq = createButton("=", 160, 310);
        bAdd = createButton("+", 225, 310);

        // Window close
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    // Button Design
    Button createButton(String text, int x, int y) {

        Button b = new Button(text);

        b.setBounds(x, y, 55, 45);

        b.setFont(new Font("Arial", Font.BOLD, 18));

        // Soft pink buttons
        b.setBackground(new Color(255, 228, 235));

        b.setForeground(new Color(120, 70, 90));

        b.addActionListener(this);

        add(b);

        return b;
    }

    // Calculator Logic
    public void actionPerformed(ActionEvent e) {

        String s = e.getActionCommand();

        // Numbers
        if(s.charAt(0) >= '0' && s.charAt(0) <= '9') {

            tf.setText(tf.getText() + s);
        }

        // Clear
        else if(s.equals("C")) {

            tf.setText("");
        }

        // Operators
        else if(s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {

            if(tf.getText().equals(""))
                return;

            num1 = Double.parseDouble(tf.getText());

            operator = s;

            // Show first number + operator
            tf.setText(tf.getText() + " " + s + " ");
        }

        // Equal
        else if(s.equals("=")) {

            String text = tf.getText();

            // Split using operator
            String parts[] = text.split("\\" + operator);

            if(parts.length < 2)
                return;

            num1 = Double.parseDouble(parts[0].trim());
            num2 = Double.parseDouble(parts[1].trim());

            switch(operator) {

                case "+":
                    result = num1 + num2;
                    break;

                case "-":
                    result = num1 - num2;
                    break;

                case "*":
                    result = num1 * num2;
                    break;

                case "/":
                    result = num1 / num2;
                    break;
            }

            tf.setText(String.valueOf(result));
        }
    }

    public static void main(String[] args) {

        new MinimalCalculator();
    }
}