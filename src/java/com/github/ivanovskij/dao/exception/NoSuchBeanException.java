
package com.github.ivanovskij.dao.exception;

/**
 *
 * @author IOAdmin
 */
public class NoSuchBeanException extends DaoBusinessException {
    
    public NoSuchBeanException(String message) {
        super(message);
    }

    public NoSuchBeanException(String message, Throwable cause) {
        super(message, cause);
    }
}
