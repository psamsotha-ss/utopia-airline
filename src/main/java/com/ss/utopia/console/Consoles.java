package com.ss.utopia.console;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Consoles {

    private Consoles() {}

    public static Console newReaderConsole() {
        return new BufferedReaderConsole(new BufferedReader(new InputStreamReader(System.in)));
    }
}
