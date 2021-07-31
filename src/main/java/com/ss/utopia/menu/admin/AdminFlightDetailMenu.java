package com.ss.utopia.menu.admin;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.ss.utopia.console.Color;
import com.ss.utopia.console.Console;
import com.ss.utopia.domain.Flight;
import com.ss.utopia.menu.AbstractMenu;
import com.ss.utopia.menu.MenuSelection;

import static com.ss.utopia.util.Formatters.formatFlight;
import static com.ss.utopia.util.StringUtils.newLine;

class AdminFlightDetailMenu extends AbstractMenu {

    private final Flight flight;

    AdminFlightDetailMenu(Console console, Flight flight) {
        super(console);
        this.flight = flight;
    }

    @Override
    protected String getInitialPrompt() {
        return "Flight: " + formatFlight(flight) + newLine()
                + newLine()
                + " 1) View Details" + newLine()
                + " 2) Delete" + newLine()
                + " 3) Update" + newLine();
    }

    @Override
    protected Color getColor() {
        return AdministratorMenu.DEFAULT_COLOR;
    }

    @Override
    protected String getExitingMessage() {
        return "Returning to flights.";
    }

    @Override
    public Map<Integer, MenuSelection> getMenuSelections() {
        Map<Integer, MenuSelection> selections = new HashMap<>();
        selections.put(1, new FlightViewOperation(console, flight));
        selections.put(2, new FlightDeleteOperation(console, flight));
        return selections;
    }
}
