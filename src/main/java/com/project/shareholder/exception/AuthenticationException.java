package com.project.shareholder.exception;

public class AuthenticationException extends ApplicationException {
    public AuthenticationException(String message) {
        super(ApplicationException.AUTHENTICATION_EXCEPTION, message);
    }
}