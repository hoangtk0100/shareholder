package com.project.shareholder.exception;

public class DatabaseException extends ApplicationException {
    public DatabaseException(String message) {
        super(ApplicationException.DATABASE_EXCEPTION, message);
    }
}