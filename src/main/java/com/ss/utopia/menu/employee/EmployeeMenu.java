package com.ss.utopia.menu.employee;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.ss.utopia.console.Color;
import com.ss.utopia.console.Console;
import com.ss.utopia.domain.Airport;
import com.ss.utopia.domain.Flight;
import com.ss.utopia.menu.AbstractMenu;
import com.ss.utopia.menu.MenuSelection;
import com.ss.utopia.repository.FlightRepository;
import com.ss.utopia.service.FlightService;

import static com.ss.utopia.util.Formatters.formatFlight;
import static com.ss.utopia.util.StringUtils.newLine;

public class EmployeeMenu extends AbstractMenu {

    private final FlightService service;

    public EmployeeMenu(Console console) {
        super(console);
        this.service = new FlightService(new FlightRepository());
    }

    @Override
    protected String getInitialPrompt() {
        return "Welcome Employee" + newLine()
                + newLine()
                + "  1) Enter Flights You Manage" + newLine();
    }

    @Override
    protected Color getColor() {
        return Color.BRIGHT_BLUE;
    }

    @Override
    protected String getExitingMessage() {
        return "Returning to main menu.";
    }

    private void runFlightManager() throws IOException {
        printNewLine();
        print("Which flight would you like to manage:");
        printNewLine();
        List<Flight> flights = service.getAllFlights();
        for (int i = 0; i < flights.size(); i++) {
            Flight flight = flights.get(i);
            print("  " + (i + 1) + ") " + formatFlight(flight));
        }
        printNewLine();
        String input = prompt("Enter your selection: ");
        printNoColor("You selected: " + input);
    }

    @Override
    public Map<Integer, MenuSelection> getMenuSelections() {
        return Collections.emptyMap();
    }
}
