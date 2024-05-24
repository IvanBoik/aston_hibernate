package com.boiko.aston_hibernate.exception;

public class FailedDeleteException extends RuntimeException {
    public FailedDeleteException() {
    }

    public FailedDeleteException(String message) {
        super(message);
    }

    public FailedDeleteException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedDeleteException(Throwable cause) {
        super(cause);
    }

    public FailedDeleteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
