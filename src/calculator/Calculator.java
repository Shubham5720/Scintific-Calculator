package calculator;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame {
    private JTextField display;
    private CalculatorEngine engine;

    public Calculator() {
        engine = new CalculatorEngine();
        display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);

        createUI();
    }

    private void createUI() {
        setTitle("Scientific Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        panel.add(display, BorderLayout.NORTH);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(5, 4));

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "sqrt", "log", "sin", "cos"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.addActionListener(new ButtonClickListener());
            buttonsPanel.add(button);
        }

        panel.add(buttonsPanel, BorderLayout.CENTER);
        add(panel);
    }

    private class ButtonClickListener implements ActionListener {
        private StringBuilder currentInput = new StringBuilder();

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            String command = source.getText();

            if (command.equals("=")) {
                try {
                    String result = String.valueOf(engine.evaluate(currentInput.toString()));
                    display.setText(result);
                    currentInput.setLength(0);
                    currentInput.append(result);
                } catch (Exception ex) {
                    display.setText("Error");
                    currentInput.setLength(0);
                }
            } else if (command.equals("sqrt")) {
                currentInput.append("Math.sqrt(");
                display.setText(currentInput.toString());
            } else if (command.equals("log")) {
                currentInput.append("Math.log10(");
                display.setText(currentInput.toString());
            } else if (command.equals("sin")) {
                currentInput.append("Math.sin(Math.toRadians(");
                display.setText(currentInput.toString());
            } else if (command.equals("cos")) {
                currentInput.append("Math.cos(Math.toRadians(");
                display.setText(currentInput.toString());
            } else {
                currentInput.append(command);
                display.setText(currentInput.toString());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculator calc = new Calculator();
            calc.setVisible(true);
        });
    }
}
