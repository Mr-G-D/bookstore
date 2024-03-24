package bookkeeper.exceptions.syntax;

import bookkeeper.exceptions.SyntaxErrorException;

/**
 * @author Sanjay Bharathi
 * This exception is thrown when the Genre is invalid in a CSV row
 *
 * Assignment 2
 * Written by: Dinesh Kumar Gopinathan - 40273281, Sanjay Bharathi Subramanian - 40248572
 * 24 March 2024
 */

public class UnknownGenreException extends SyntaxErrorException {
    /**
     * Constructor
     */
    public UnknownGenreException(){
        super("invalid genre");
    }
}
