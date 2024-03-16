package bookkeeper.exceptions.semantic;

import bookkeeper.exceptions.SemanticErrorException;

/**
 * @author DINESH KUMAR
 */
public class BadYearException extends SemanticErrorException {

    public BadYearException(){
        super("invalid year");
    }
}
