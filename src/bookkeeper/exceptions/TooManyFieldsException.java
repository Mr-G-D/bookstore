package bookkeeper.exceptions;

/**
 * @author Sanjay Bharathi
 */

public class TooManyFieldsException extends SyntaxErrorException {

    public TooManyFieldsException() {
        super("Too many fields");
    }
}
