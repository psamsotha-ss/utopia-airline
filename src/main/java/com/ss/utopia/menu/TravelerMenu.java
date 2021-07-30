package com.ss.utopia.menu;

import java.io.IOException;

import com.ss.utopia.console.Color;
import com.ss.utopia.console.Console;

class TravelerMenu extends AbstractMenu {

    TravelerMenu(Console console) {
        super(console);
    }

    @Override
    protected Color getColor() {
        return Color.BRIGHT_GREEN;
    }

    public void run() throws IOException {
        print("Running Traveler menu...");
    }
}
