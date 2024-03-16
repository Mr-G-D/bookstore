package bookkeeper.exceptions.semantic;

import bookkeeper.exceptions.SemanticErrorException;

/**
 * @author DINESH KUMAR
 */
public class BadPriceException extends SemanticErrorException {

    public BadPriceException(){
        super("invalid price");
    }
}
