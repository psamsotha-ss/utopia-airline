package com.ss.utopia.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Converters {

    public static LocalDateTime dateFromString(String dateString) {
        return LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
