package bookkeeper.exceptions.syntax;

import bookkeeper.exceptions.SyntaxErrorException;

/**
 * @author Sanjay Bharathi
 * This exception is thrown when the total number of fields is less than 6
 * Assignment 2
 * Written by: Dinesh Kumar Gopinathan - 40273281, Sanjay Bharathi Subramanian - 40248572
 * 24 March 2024
 */

public class TooFewFieldsException extends SyntaxErrorException {
    /**
     * Constructor
     */
    public TooFewFieldsException() {
        super("Too few fields");
    }
}
