
package com.github.ivanovskij.dao.exception;

/**
 * DaoBusinessException – проблемы с бизнес логикой (и так далее)
 * @author IOAdmin
 */
public class DaoBusinessException extends DaoException {

    public DaoBusinessException(String message) {
        super(message);
    }
    
    public DaoBusinessException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
