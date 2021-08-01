package com.ss.utopia.menu.admin;

import java.util.HashMap;
import java.util.Map;

import com.ss.utopia.console.Color;
import com.ss.utopia.console.Console;
import com.ss.utopia.menu.AbstractMenu;
import com.ss.utopia.menu.MenuSelection;

import static com.ss.utopia.util.StringUtils.newLine;

public class AdminMenu extends AbstractMenu {

    static final Color DEFAULT_COLOR = Color.BRIGHT_RED;

    public AdminMenu(Console console) {
        super(console);
    }

    @Override
    protected String getInitialPrompt() {
          return "Welcome Admin. What would you like to see:" + newLine()
                + newLine()
                + "  1) Flights" + newLine()
                + "  2) Seats" + newLine()
                + "  3) Passengers" + newLine()
                + "  4) Airports" + newLine()
                + "  5) Travelers" + newLine()
                + "  6) Employees" + newLine()
                + "  7) Override Trip Cancellation" + newLine();
    }

    @Override
    public Map<Integer, MenuSelection> getMenuSelections() {
        Map<Integer, MenuSelection> selections = new HashMap<>();
        selections.put(1, new AdminFlightsMenu(console));
        selections.put(2, new AdminSeatsMenu());
        selections.put(4, new AdminAirportsMenu());
        selections.put(6, new AdminEmployeesMenu());
        return selections;
    }

    @Override
    protected Color getColor() {
        return Color.BRIGHT_RED;
    }

    @Override
    protected String getExitingMessage() {
        return "Returning to main menu.";
    }
}
