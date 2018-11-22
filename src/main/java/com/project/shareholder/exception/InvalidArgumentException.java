package com.project.shareholder.exception;

public class InvalidArgumentException extends ApplicationException {
    public InvalidArgumentException(String message) {
        super(ApplicationException.INVALID_ARGUMENT_EXCEPTION, message);
    }
}