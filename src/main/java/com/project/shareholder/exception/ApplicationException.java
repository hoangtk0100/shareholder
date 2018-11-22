package com.project.shareholder.exception;

public abstract class ApplicationException extends Exception {

    public static int NO_EXCEPTION               = 0;
    public static int UNCAUGHT_EXCEPTION         = 1001;
    public static int NOT_FOUND_EXCEPTION        = 1002;
    public static int VALIDATION_EXCEPTION       = 1003;
    public static int INVALID_ARGUMENT_EXCEPTION = 1004;
    public static int AUTHENTICATION_EXCEPTION   = 1005;
    public static int DATABASE_EXCEPTION 		 = 1006;
    public static int NETWORK_EXCEPTION       	 = 1007;

    private int code;

    public ApplicationException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public ApplicationException setCode(int code) {
        this.code = code;
        return this;
    }
}