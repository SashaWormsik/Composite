package org.chervyakovsky.compositetask.exception;

public class CustomException extends Exception {

    public CustomException() {
        super();
    }

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Exception exception) {
        super(message, exception);
    }

    public CustomException(Exception exception) {
        super(exception);
    }
}