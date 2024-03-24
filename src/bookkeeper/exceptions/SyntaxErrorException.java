package bookkeeper.exceptions;

/**
 * @author Sanjay Bharathi
 * Parent exception that represents all Syntax Exceptions
 *
 * Assignment 2
 * Written by: Dinesh Kumar Gopinathan - 40273281, Sanjay Bharathi Subramanian - 40248572
 * 24 March 2024
 */

public class SyntaxErrorException extends Exception{

    /**
     * Constructor
     * @param s Exception message
     */
    public SyntaxErrorException(String s) {
        super(s);
    }
}
