package com.ss.utopia.util;

/**
 * Utility class for working with strings
 */
public class StringUtils {

    private StringUtils() {}

    /**
     * Get a system-dependent line separator
     * @return the line separator
     */
    public static String newLine() {
        return System.lineSeparator();
    }
}
