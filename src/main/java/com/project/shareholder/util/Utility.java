package com.project.shareholder.util;

import com.project.shareholder.exception.NetworkException;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import java.util.UUID;


public class Utility {

    public static final int UID_LENGTH = 12;

    private static final String ERROR_MESSAGE_DELIMITER = ";";

    private static final String LETTER_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBER_LIST = "1234567890";
    private static final String SYMBOL_LIST = "!@$%^*";
    private static final String WORD_LIST = LETTER_LIST + NUMBER_LIST;
    private static final String CHAR_LIST = WORD_LIST + SYMBOL_LIST;

    /**
     * Validate errors
     *
     * @param errors
     * @throws NetworkException
     */
    public static void validateErrorsRequest(Errors errors) throws NetworkException {
        StringBuilder errorMessage = new StringBuilder();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                errorMessage.append(ERROR_MESSAGE_DELIMITER + error.getDefaultMessage());
            }

            throw new NetworkException(errorMessage.substring(1));
        }
    }

    /**
     * Va
     * */
    public static boolean isEmptyUUID(UUID uuid) {
        return uuid.toString() == "00000000-0000-0000-0000-000000000000";
    }
}
