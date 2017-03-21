
package com.github.ivanovskij.dao.exception;

/**
 *
 * @author IOAdmin
 */
public class NoSuchEntityException extends DaoBusinessException {
    
    public NoSuchEntityException(String message) {
        super(message);
    }

    public NoSuchEntityException(String message, Throwable cause) {
        super(message, cause);
    }
}
