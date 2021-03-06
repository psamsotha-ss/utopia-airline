package com.ss.utopia.console;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * A console which uses the standard output stream for output and
 * a {@code BufferedReader} wrapped around the standard input stream to get
 * input from a user.
 */
class BufferedReaderConsole implements Console {

    private final BufferedReader reader;

    BufferedReaderConsole(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public String prompt(String message) throws IOException {
        System.out.print(message);
        String input = reader.readLine().trim();
        return input.trim();
    }

    @Override
    public String prompt(String message, Color color) throws IOException {
        return prompt(ColorUtil.getColoredMessage(message, color));
    }

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public void print(String message, Color color) throws IOException {
        print(ColorUtil.getColoredMessage(message, color));
    }
}
