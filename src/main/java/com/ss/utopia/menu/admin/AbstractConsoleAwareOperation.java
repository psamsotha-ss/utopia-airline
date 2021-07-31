package com.ss.utopia.menu.admin;

import com.ss.utopia.console.Console;
import com.ss.utopia.console.Consoles;
import com.ss.utopia.menu.MenuOperation;

public abstract class AbstractConsoleAwareOperation implements MenuOperation {

    private final Console console;

    protected AbstractConsoleAwareOperation() {
        this(Consoles.getDefaultConsole());
    }

    protected AbstractConsoleAwareOperation(Console console) {
        this.console = console;
    }

    protected Console getConsole() {
        return console;
    }
}
