package bookkeeper.exceptions;

/**
 * Parent exception for all Semantic Errors
 * @author DINESH KUMAR
 *
 * Assignment 2
 * Written by: Dinesh Kumar Gopinathan - 40273281, Sanjay Bharathi Subramanian - 40248572
 * 24 March 2024
 */
public class SemanticErrorException extends Exception{

    public SemanticErrorException(String s) {
        super(s);
    }
}
