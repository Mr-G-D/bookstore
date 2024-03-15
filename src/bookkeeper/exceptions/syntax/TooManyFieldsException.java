package bookkeeper.exceptions.syntax;

import bookkeeper.exceptions.SyntaxErrorException;

/**
 * @author Sanjay Bharathi
 */

public class TooManyFieldsException extends SyntaxErrorException {

    public TooManyFieldsException() {
        super("Too many fields");
    }
}
