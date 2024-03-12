package bookkeeper.exceptions;

/**
 * @author Sanjay Bharathi
 */

public class MissingFieldException extends SyntaxErrorException {
    public MissingFieldException(String field) {
        super("Missing " + field);
    }
}
