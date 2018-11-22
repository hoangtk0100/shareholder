package com.project.shareholder.util;

import java.util.HashMap;
public final class Constants {

    //DECLARE CONSTANT_CODE here
    //constant_default_error
    public static final String CONSTANT_DEFAULT_ERROR = "constant_default_error";
    public static final String PLACE_NAME_CONSTANT_CODE = "place_name_unique_constant";
    public static final String PLACE_USER_CONSTANT_CODE = "place_user_unique_constant";
    public static final String USER_CONSTANT_CODE = "user_unique_constant";
    public static final String ROLE_CONSTANT_CODE = "role_unique_constant";

    // DECLARE CONSTANT_MESSAGES below
    private final static HashMap<String, String> constantMap = new HashMap<>();

    static {
        constantMap.put(PLACE_NAME_CONSTANT_CODE, "place_name_unique_constant_duplicated");
        constantMap.put(PLACE_USER_CONSTANT_CODE, "place_user_unique_constant_duplicated");
        constantMap.put(USER_CONSTANT_CODE, "user_unique_constant_duplicated");
        constantMap.put(ROLE_CONSTANT_CODE, "role_unique_constant_duplicated");
    }
    public static String getConstantErrorMessage(String constantName){
        String result = constantMap.get(constantName);
        if(result == null){
            result = CONSTANT_DEFAULT_ERROR;
        }

        return result;
    }
}
