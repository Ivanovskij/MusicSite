
package com.github.ivanovskij.dao.exception;

/**
 * DaoSystemException – системные проблемы (недостаточно прав, не тот пароль и так далее)
 * @author IOAdmin
 */
public class DaoSystemException extends DaoException {
    
    public DaoSystemException(String message) {
        super(message);
    }

    public DaoSystemException(String message, Throwable cause) {
        super(message, cause);
    }
}
