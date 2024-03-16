package bookkeeper.exceptions.semantic;

import bookkeeper.exceptions.SemanticErrorException;

/**
 * @author DINESH KUMAR
 */
public class BadIsbn13Exception extends SemanticErrorException {

    public BadIsbn13Exception(){
        super("invalid isbn13");
    }
}
