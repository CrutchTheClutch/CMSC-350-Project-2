package project2;

/**
 * Filename:    OperandNode
 * Author:      William Crutchfield
 * Date:        6/16/2017
 * Description: Creates the OperandNodes used in the Expression Tree
 */

public class OperandNode implements Node {

    private String value;

    /**
     * Assigns values to proper variables
     * @param value operand in expression
     */
    OperandNode(String value) {
        this.value = value;
    }

    /**
     * Used for creating the Infix Expression
     * @return value of operand
     */
    public String inOrderWalk() {
        return String.valueOf(value);
    }

    /**
     * Used for creating the Postfix Expression, as well as 3-Address.txt
     * @return value of operand
     */
    public String postOrderWalk() {
        return String.valueOf(value);
    }
}