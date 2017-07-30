package project2;

import java.io.IOException;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Filename:    ExpressionEval
 * Author:      William Crutchfield
 * Date:        6/16/2017
 * Description: Handles all logic and calculations related to the arithmetic expression.
 */

class ExpressionEval {

    // Variables
    private Stack<Node> expressionStack = new Stack<>();
    private Node operator;

    /**
     * Takes an expression, splits it into tokens, then pushes tokens to a stack. Used in the construction of the Expression Tree
     * @param expression String the user inputs
     * @return Infix Expression version of input
     * @throws InvalidTokenException is thrown when an incorrect character is in the expression
     * @throws EmptyStackException is thrown when there is no input
     */
    String evaluate(String expression) throws InvalidTokenException, EmptyStackException, IOException {
        String[] tokens = expression.split(" ");

        for (String token : tokens) {
            // Determines patterns used to determine tokens
            String operandPat = "[\\d.?]+";
            String operatorPat = "[*/+\\-]";

            // Ensures no illegal characters are used
            if (!token.matches(operandPat) && !token.matches(operatorPat)) {
                throw new InvalidTokenException(token);
            }

            // Pushes Operands onto Stack. Assigns children to Operators, then pushes Operators onto Stack
            if (token.matches(operandPat)) {
                expressionStack.push(new OperandNode(token));
            } else if (token.matches(operatorPat)) {
                operator = new OperatorNode(token, expressionStack.pop(), expressionStack.pop());
                expressionStack.push(operator);
            }
        }

        // Creates the Assembly Code written to File
        operator.post();

        return expressionStack.pop().inOrderWalk();
    }
}
