package com.ss.utopia.menu;

import java.io.IOException;

import com.ss.utopia.console.Color;
import com.ss.utopia.console.Console;

class AdministratorMenu extends AbstractMenu {

    AdministratorMenu(Console console) {
        super(console);
    }

    @Override
    protected Color getColor() {
        return Color.BRIGHT_RED;
    }

    @Override
    public void run() throws IOException {
        print("Administrator access not allowed!");
    }
}
