package com.ss.utopia.menu.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ss.utopia.console.Color;
import com.ss.utopia.console.Console;
import com.ss.utopia.domain.Flight;
import com.ss.utopia.menu.AbstractMenu;
import com.ss.utopia.menu.MenuSelection;

import static com.ss.utopia.util.Formatters.formatFlight;
import static com.ss.utopia.util.StringUtils.newLine;

class FlightOptionsMenu extends AbstractMenu {

    private final Flight flight;
    private final List<Flight> flights;

    FlightOptionsMenu(Console console, Flight flight, List<Flight> flights) {
        super(console);
        this.flight = flight;
        this.flights = flights;
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
        return AdminMenu.DEFAULT_COLOR;
    }

    @Override
    protected String getExitingMessage() {
        return "Returning to flights.";
    }

    @Override
    public Map<Integer, MenuSelection> getMenuSelections() {
        Map<Integer, MenuSelection> selections = new HashMap<>();
        selections.put(1, new FlightViewOperation(flight));
        selections.put(2, new FlightDeleteOperation(console, flight, flights));
        selections.put(3, new FlightUpdateMenu(console, flight));
        return selections;
    }
}
