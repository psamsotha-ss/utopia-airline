package com.ss.utopia.menu;

import java.io.IOException;

import com.ss.utopia.console.Color;
import com.ss.utopia.console.Console;

import static com.ss.utopia.util.StringUtils.newLine;

class EmployeeMenu extends AbstractMenu {

    EmployeeMenu(Console console) {
        super(console);
    }

    @Override
    protected Color getColor() {
        return Color.BRIGHT_BLUE;
    }

    public void run() throws IOException {
        final String message = "Welcome Employee" + newLine()
                + newLine()
                + "  1) Enter Flights You Manage" + newLine()
                + "  2) Quit to previous" + newLine()
                + newLine()
                + "Enter your option: ";

        while (true) {
            String input = prompt(message);
            int option;
            try {
                option = Integer.parseInt(input);
            } catch (NumberFormatException ex) {
                printNoColor(input + " is not a valid option.");
                continue;
            }

            switch (option) {
                case 1: runFlightManager(); break;
                case 2: {
                    printNoColor("Returning to main menu.");
                    return;
                }
                default: {
                    printNoColor(option + " is not a valid option.");
                }
            }
        }
    }

    private void runFlightManager() throws IOException {
        print("Running flight manager...");
    }
}
