package com.project.shareholder.exception;

public class ValidationException extends ApplicationException {
    public ValidationException(String message) {
        super(ApplicationException.VALIDATION_EXCEPTION, message);
    }
}