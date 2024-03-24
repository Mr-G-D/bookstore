package bookkeeper.exceptions.semantic;

import bookkeeper.exceptions.SemanticErrorException;
/**
 * @author Sanjay Bharathi
 * @author DINESH KUMAR
 * Assignment 2
 * Written by: Dinesh Kumar Gopinathan - 40273281, Sanjay Bharathi Subramanian - 40248572
 * 24 March 2024
 *
 * This class is used to handle the semantic errors
 */
public class BadIsbn10Exception extends SemanticErrorException {

    /**
     * Constructor to initialize the exception
     */
    public BadIsbn10Exception(){
        super("invalid isbn10");
    }
}
