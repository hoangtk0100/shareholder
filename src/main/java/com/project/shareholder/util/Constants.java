package com.project.shareholder.util;

import java.util.HashMap;
public final class Constants {

    // Declare constant exception
    public static final String DEFAULT_ERROR = "default_constant";
    public static final String AUTHENTICATION_MESSAGE = "authentication_constant";
    public static final String DATABASE_MESSAGE = "database_constant";
    public static final String INVALID_ARGUMENT_MESSAGE = "invalid_argument_constant";
    public static final String NETWORK_MESSAGE = "network_constant";
    public static final String NOT_FOUND_MESSAGE = "not_found_constant";
    public static final String VALIDATION_MESSAGE = "validation_constant";

    // Declare constant exception message
    private final static HashMap<String, String> constantMap = new HashMap<>();

    static {
        constantMap.put(AUTHENTICATION_MESSAGE, "authentication_message");
        constantMap.put(DATABASE_MESSAGE, "database_message");
        constantMap.put(INVALID_ARGUMENT_MESSAGE, "invalid_argument_message");
        constantMap.put(NETWORK_MESSAGE, "network_message");
        constantMap.put(NOT_FOUND_MESSAGE, "not_found_message");
        constantMap.put(VALIDATION_MESSAGE, "validation_message");
    }

    public static String getConstantErrorMessage(String constantName){
        String result = constantMap.get(constantName);
        if(result == null){
            result = DEFAULT_ERROR;
        }

        return result;
    }
}
