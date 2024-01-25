
package CustomException;

/**
 *
 * @author Tran Quoc Cuong
 */
public class ValidationException extends Exception{
    
    public ValidationException(String errorMessage) {
        super(errorMessage);
    }
    
}
