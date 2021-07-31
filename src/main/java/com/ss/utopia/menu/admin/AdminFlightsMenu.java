package com.ss.utopia.menu.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ss.utopia.console.Color;
import com.ss.utopia.console.Console;
import com.ss.utopia.domain.Flight;
import com.ss.utopia.menu.AbstractMenu;
import com.ss.utopia.menu.MenuSelection;
import com.ss.utopia.repository.FlightRepository;
import com.ss.utopia.service.FlightService;

import static com.ss.utopia.util.Formatters.formatFlight;
import static com.ss.utopia.util.StringUtils.newLine;

class AdminFlightsMenu extends AbstractMenu {

    private final List<Flight> flights;

    AdminFlightsMenu(Console console) {
        super(console);

        FlightService flightService = new FlightService(new FlightRepository());
        flights = flightService.getAllFlights();
    }

    @Override
    protected String getInitialPrompt() {
        StringBuilder sb = new StringBuilder();
        sb.append("Which flight would you like to manage:").append(newLine());
        sb.append(newLine());
        for (int i = 0; i < flights.size(); i++) {
            Flight flight = flights.get(i);
            sb.append("  ").append(i + 1).append(") ").append(formatFlight(flight));
            sb.append(newLine());
        }
        return sb.toString();
    }

    @Override
    protected Color getColor() {
        return AdminMenu.DEFAULT_COLOR;
    }

    @Override
    protected String getExitingMessage() {
        return "Returning to admin menu.";
    }

    @Override
    public Map<Integer, MenuSelection> getMenuSelections() {
        Map<Integer, MenuSelection> selections = new HashMap<>();
        for (int i = 0; i < flights.size(); i++) {
            Flight flight = flights.get(i);
            selections.put(i + 1, new FlightOptionsMenu(console, flight, flights));
        }
        return selections;
    }
}
