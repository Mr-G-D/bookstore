package bookkeeper.exceptions.syntax;

import bookkeeper.exceptions.SyntaxErrorException;

/**
 * @author Sanjay Bharathi
 */

public class MissingFieldException extends SyntaxErrorException {
    public MissingFieldException(String field) {
        super("Missing " + field);
    }
}
