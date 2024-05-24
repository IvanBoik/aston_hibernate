package com.boiko.aston_hibernate.exception;

public class FailedInsertException extends RuntimeException {
    public FailedInsertException() {
    }

    public FailedInsertException(String message) {
        super(message);
    }

    public FailedInsertException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedInsertException(Throwable cause) {
        super(cause);
    }

    public FailedInsertException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
