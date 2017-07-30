package project2;

import java.io.IOException;

/**
 * Filename:    Node
 * Author:      William Crutchfield
 * Date:        6/16/2017
 * Description: Creates Node Interface, allows OperandNodes and OperatorNodes to be stored in the Expression Tree.
 */

public interface Node {
    String inOrderWalk();
    String postOrderWalk() throws IOException;
    void post() throws IOException;
}
