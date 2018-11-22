package com.project.shareholder.exception;

public class NotFoundException extends ApplicationException {
    public NotFoundException(String message) {
        super(ApplicationException.NOT_FOUND_EXCEPTION, message);
    }
}