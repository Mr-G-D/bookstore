package bookkeeper.exceptions.semantic;

import bookkeeper.exceptions.SemanticErrorException;

/**
 * This exception is thrown when the ISBN 10 is invalid
 * @author DINESH KUMAR
 * Assignment 2
 * Written by: Dinesh Kumar Gopinathan - 40273281, Sanjay Bharathi Subramanian - 40248572
 * 24 March 2024
 *
 */
public class BadIsbn10Exception extends SemanticErrorException {
    /**
     * Constructor
     */
    public BadIsbn10Exception(){
        super("invalid isbn10");
    }
}
