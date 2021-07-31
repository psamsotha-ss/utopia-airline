package com.ss.utopia.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for doing conversions
 */
public class Converters {

    /**
     * Convert a date string into a {@code LocalDateTime}, using the MySQL DATETIME format
     * yyyy-MM-dd HH:mm:ss
     *
     * @param dateString the date-time string
     * @return the {@code LocalDateTime}
     */
    public static LocalDateTime dateFromString(String dateString) {
        return LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
