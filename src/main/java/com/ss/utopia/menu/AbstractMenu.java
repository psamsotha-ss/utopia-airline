package com.ss.utopia.menu;

import java.io.IOException;

import com.ss.utopia.console.Color;
import com.ss.utopia.console.Console;

abstract class AbstractMenu implements Menu {

    protected final Console console;

    AbstractMenu(Console console) {
        this.console = console;
    }

    abstract protected Color getColor();

    String prompt(String message) throws IOException {
        return console.prompt(message, getColor());
    }

    void print(String message) throws IOException {
        console.print(message, getColor());
    }

    void printNoColor(String message) throws IOException {
        console.print(message);
    }

    void printNewLine() throws IOException {
        console.printNewLine();
    }
}
