package project2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.EmptyStackException;

/**
 * Filename:    P2GUI
 * Author:      William Crutchfield
 * Date:        6/15/2017
 * Description: Accepts an arithmetic expression of unsigned integers in postfix notation in which the tokens are separated by spaces. Then
 * builds and processes the arithmetic expression tree that represents that expression.
 */

public class P2GUI extends JFrame {

    // Variables
    private String expression;

    // Creates Instance of ExpressionEval
    private ExpressionEval expressionEval = new ExpressionEval();

    /**
     * Creates the GUI for the program, as well as handles Action Listeners
     */
    private P2GUI() {
        // Set Title
        super("Three Address Generator");

        // Create Panels
        JPanel main = new JPanel();
        JPanel inputPanel = new JPanel();
        JPanel constructPanel = new JPanel();
        JPanel resultPanel = new JPanel();

        // Set Layout
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        // Create Components
        JLabel inputLabel = new JLabel("Enter Postfix Expression");
        JTextField inputTxt = new JTextField(null,20);
        JButton constructBtn = new JButton("Construct Tree");
        JLabel resultLabel = new JLabel("Infix Expression");
        JTextField resultTxt = new JTextField(null,20);

        // Add Input Components
        inputPanel.add(inputLabel);
        inputPanel.add(inputTxt);

        // Add Construct Components
        constructPanel.add(constructBtn);

        // Add Result Components
        resultPanel.add(resultLabel);
        resultPanel.add(resultTxt);
        resultTxt.setEditable(false);

        // Add Panels to main
        main.add(inputPanel);
        main.add(constructPanel);
        main.add(resultPanel);

        // Add main to JFrame
        add(main);

        // JFrame Settings
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(400,145);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        // "Construct Tree" Button Handler
        constructBtn.addActionListener((ActionEvent e) -> {
            expression = inputTxt.getText();

            try {
                if (expression.isEmpty()) {
                    throw new NullPointerException();
                } else {
                    resultTxt.setText(expressionEval.evaluate(expression));
                }
            } catch (InvalidTokenException e1) {
                JOptionPane.showMessageDialog(null, "Invalid Token: " + e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            } catch (NullPointerException e2) {
                JOptionPane.showMessageDialog(null, "A Postfix Expression is required.","Error",JOptionPane.ERROR_MESSAGE);
            } catch (EmptyStackException empty) {
                JOptionPane.showMessageDialog(null, "Invalid Postfix Expression. Check Operators and Operands.","Error",JOptionPane.ERROR_MESSAGE);
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(null, "Error Writing to File.","Error",JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {
        new P2GUI();
    }
}
