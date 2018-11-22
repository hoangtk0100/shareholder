package com.project.shareholder.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Converter
public class StringListConverter implements AttributeConverter<List<String>, String> {

    @Override
    public String convertToDatabaseColumn(List<String> list) {
        if (list.isEmpty()) {
            return null;
        }
        // Java 8
        return String.join(Constant.COMMA_DELIMITER, list);
    }

    @Override
    public List<String> convertToEntityAttribute(String joined) {
        if (!joined.isEmpty()) {
            return new ArrayList<>(Arrays.asList(joined.split(Constant.COMMA_DELIMITER)));
        } else {
            return null;
        }
    }
}