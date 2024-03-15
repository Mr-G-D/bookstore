package bookkeeper.exceptions.syntax;

import bookkeeper.exceptions.SyntaxErrorException;

/**
 * @author Sanjay Bharathi
 */

public class TooFewFieldsException extends SyntaxErrorException {
    public TooFewFieldsException() {
        super("Too few fields");
    }
}
