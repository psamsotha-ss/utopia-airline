package com.ss.utopia.console;

import java.io.IOException;

/**
 * A console which can be used to print to or to prompt for user input.
 */
public interface Console {

    /**
     * Prompt for input from user.
     * @param message the prompt message
     * @return the input from the user
     * @throws IOException if an i/o error occurs
     */
    String prompt(String message) throws IOException;

    /**
     * Prompt (with color) for input from user
     * @param message the prompt message
     * @param color the color of the prompt message
     * @return the input from the user
     * @throws IOException if an i/o error occurs
     */
    String prompt(String message, Color color) throws IOException;

    /**
     * Print a message to the console
     * @param message the message to print
     * @throws IOException if an i/o error occurs
     */
    void print(String message) throws IOException;

    /**
     * Print a message to the console
     * @param message the message to print
     * @param color the color of the message
     * @throws IOException if an i/o error occurs
     */
    void print(String message, Color color) throws IOException;

    default void printNewLine() throws IOException {
        print("");
    }
}
