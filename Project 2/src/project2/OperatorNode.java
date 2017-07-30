package project2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Filename:    OperatorNode
 * Author:      William Crutchfield
 * Date:        6/16/2017
 * Description: Creates the OperatorNodes used in the ExpressionTree, assigns OperandNodes as the children
 */

public class OperatorNode implements Node {

    // Variables
    private String operator;
    private Node right, left;
    private static int i;   // register count for 3-address code
    private File file = new File("3-Address.txt"); // creates 3-Address text file on users Desktop

    /**
     * Assigns values to proper variables
     * @param operator the operator used in the expression
     * @param right the right operand
     * @param left the left operand
     */
    OperatorNode(String operator, Node right, Node left) {
        this.operator = operator;
        this.right = right;
        this.left = left;

        operatorEval(operator);
    }

    /**
     * Create the Infix Expression
     * @return String containing Infix version of expression input
     */
    public String inOrderWalk() {
        return "(" + left.inOrderWalk() + " " + operator + " " + right.inOrderWalk() + ")";
    }

    /**
     * Helper method for postOrderWalk
     * @throws IOException if postOrderWalk cannot be executed
     */
    public void post() throws IOException {
        i = 0;              // sets count
        postOrderWalk();    // starts the walk
    }

    /**
     * Walks tree in PostOrder, then writes each step in expression to file
     * @throws IOException if leftValue or rightValue cannot be set, or cannot write to file.
     * @return Result of step, meaning a result is stored in the tree as ("R" + i++) rather then as the step itself
     */
    public String postOrderWalk() throws IOException {
        String leftValue = left.postOrderWalk();
        String rightValue = right.postOrderWalk();
        String opValue = operatorEval(this.operator);

        String result = "R" + i++;

        // Creates string with a step in the expression then writes to file
        String step = opValue + " " + result + " " + leftValue + " " + rightValue;
        writeToFile(step);

        return result;
    }

    /**
     * Assigns a word to each type of operator, helper method for postOrderWalk
     * @param operator operator value from expression
     * @return String that is a word representation of an operator
     */
    private String operatorEval(String operator) {
        String op = "";

        switch (operator) {
            case "+":
                op = "Add";
                break;
            case "-":
                op = "Sub";
                break;
            case "*":
                op = "Mul";
                break;
            case "/":
                op = "Div";
                break;
        }

        return op;
    }

    /**
     * Writes a line to the 3-Address.txt file, helper method for postOrderWalk
     * @param step A step in the expression
     */
    private void writeToFile(String step) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));
        bw.write(step);
        bw.newLine();
        bw.close();
    }
}