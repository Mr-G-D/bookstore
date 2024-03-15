package bookkeeper.exceptions.syntax;

import bookkeeper.exceptions.SyntaxErrorException;

/**
 * @author Sanjay Bharathi
 */

public class UnknownGenreException extends SyntaxErrorException {
    public UnknownGenreException(){
        super("invalid genre");
    }
}
