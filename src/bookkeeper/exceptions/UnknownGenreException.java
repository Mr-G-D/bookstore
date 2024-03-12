package bookkeeper.exceptions;

/**
 * @author Sanjay Bharathi
 */

public class UnknownGenreException extends SyntaxErrorException {
    public UnknownGenreException(){
        super("invalid genre");
    }
}
