package bookkeeper.exceptions;
/**
 * @author Sanjay Bharathi
 * @author DINESH KUMAR
 * Assignment 2
 * Written by: Dinesh Kumar Gopinathan - 40273281, Sanjay Bharathi Subramanian - 40248572
 * 24 March 2024
 *
 * This class is used to handle the syntax errors
 */

public class SyntaxErrorException extends Exception{

    /**
     * Constructor to initialize the exception
     * @param s - message to be displayed
     */
    public SyntaxErrorException(String s) {
        super(s);
    }
}
