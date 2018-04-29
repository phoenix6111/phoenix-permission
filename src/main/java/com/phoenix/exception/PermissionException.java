package com.phoenix.exception;

/**
 * User: sheng
 * Date: 2018-04-26 10:33
 * Description:自定义的权限认证错误Exception
 */
public class PermissionException extends RuntimeException {

    public PermissionException() {
    }

    public PermissionException(String message) {
        super(message);
    }

    public PermissionException(String message, Throwable cause) {
        super(message, cause);
    }

    public PermissionException(Throwable cause) {
        super(cause);
    }

    public PermissionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
