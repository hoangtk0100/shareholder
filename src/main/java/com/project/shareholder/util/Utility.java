package com.project.shareholder.util;

import com.project.shareholder.exception.NetworkException;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;


public class Utility {

    public static final int UID_LENGTH = 12;

    private static final String ERROR_MESSAGE_DELIMITER = ";";

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
     * UUID Validator
     * */
    public static boolean isEmptyUUID(UUID uuid) {
        return uuid.toString() == "00000000-0000-0000-0000-000000000000";
    }

    /**
     * convert String to YearMonth
     * */
    public static YearMonth convertPeriod(String period) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constant.DATE_FORMAT_YYYYMM);
        return YearMonth.parse(period, formatter);
    }

    /**
     * convert String to Date
     * */
    public static Date convertDate(String date) {
        return (Date) DateTimeFormatter.ofPattern(Constant.DATE_FORMAT_YYYYMMDD).parse(date);
    }
}
