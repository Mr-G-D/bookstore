package bookkeeper.exceptions;

/**
 * @author Sanjay Bharathi
 */

public class TooFewFieldsException extends SyntaxErrorException {
    public TooFewFieldsException() {
        super("Too few fields");
    }
}
