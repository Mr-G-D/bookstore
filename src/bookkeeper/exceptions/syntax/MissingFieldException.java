package bookkeeper.exceptions.syntax;

import bookkeeper.exceptions.SyntaxErrorException;

/**
 * @author Sanjay Bharathi
 * This Exception is thrown whenever a field is missing in the CSV row
 * Assignment 2
 * Written by: Dinesh Kumar Gopinathan - 40273281, Sanjay Bharathi Subramanian - 40248572
 * 24 March 2024
 */

public class MissingFieldException extends SyntaxErrorException {
    /**
     * Constructor
     * @param field missing field value
     */
    public MissingFieldException(String field) {
        super("Missing " + field);
    }
}
