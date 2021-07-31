package com.ss.utopia.console;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Factory for creating {@code Console}s.
 */
public class Consoles {

    private Consoles() {}

    /**
     * Get a new {@code BufferedReader} {@code Console}.
     * @return the console
     */
    public static Console newReaderConsole() {
        return new BufferedReaderConsole(new BufferedReader(new InputStreamReader(System.in)));
    }

    /**
     * Get the default console
     * @return the console
     */
    public static Console getDefaultConsole() {
        return newReaderConsole();
    }
}
