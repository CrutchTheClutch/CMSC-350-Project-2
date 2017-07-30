package project2;

/**
 * Filename:    InvalidTokenException
 * Author:      William Crutchfield
 * Date:        6/15/2017
 * Description: Handles the InvalidTokenException, is thrown when an incorrect token is used.
 */
public class InvalidTokenException extends Exception {
    public InvalidTokenException(String token) {
        super(token);
    }
}
