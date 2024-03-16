package bookkeeper.exceptions.semantic;

import bookkeeper.exceptions.SemanticErrorException;

/**
 * @author DINESH KUMAR
 */
public class BadIsbn10Exception extends SemanticErrorException {

    public BadIsbn10Exception(){
        super("invalid isbn10");
    }
}
