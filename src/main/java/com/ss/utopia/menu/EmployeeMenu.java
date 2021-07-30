package com.ss.utopia.menu;

import java.io.IOException;

import com.ss.utopia.console.Color;
import com.ss.utopia.console.Console;

class EmployeeMenu extends AbstractMenu {

    EmployeeMenu(Console console) {
        super(console);
    }

    @Override
    protected Color getColor() {
        return Color.BRIGHT_BLUE;
    }

    public void run() throws IOException {
        print("Running Employee menu...");
    }
}
