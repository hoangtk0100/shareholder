package com.project.shareholder.exception;

public class NetworkException extends ApplicationException {
    public NetworkException(String message) {
        super(ApplicationException.NETWORK_EXCEPTION, message);
    }
}