package com.boiko.aston_hibernate.exception;

public class FailedToCreateSessionFactoryException extends RuntimeException {
    public FailedToCreateSessionFactoryException() {
    }

    public FailedToCreateSessionFactoryException(String message) {
        super(message);
    }

    public FailedToCreateSessionFactoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedToCreateSessionFactoryException(Throwable cause) {
        super(cause);
    }

    public FailedToCreateSessionFactoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
