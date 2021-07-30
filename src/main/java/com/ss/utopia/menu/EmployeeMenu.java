package com.ss.utopia.menu;

import java.io.IOException;
import java.util.List;

import com.ss.utopia.console.Color;
import com.ss.utopia.console.Console;
import com.ss.utopia.domain.Airport;
import com.ss.utopia.domain.Flight;
import com.ss.utopia.service.FlightService;

import static com.ss.utopia.util.StringUtils.newLine;

class EmployeeMenu extends AbstractMenu {

    private final FlightService service;

    EmployeeMenu(Console console, FlightService service) {
        super(console);
        this.service = service;
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
        printNewLine();
        print("Which flight would you like to manage:");
        printNewLine();
        List<Flight> flights = service.getFlights();
        for (int i = 0; i < flights.size(); i++) {
            Flight flight = flights.get(i);
            print("  " + i + ") " + formatFlight(flight));
        }
        printNewLine();
        String input = prompt("Enter your selection: ");
        printNoColor("You selected: " + input);
    }

    private String formatFlight(Flight flight) {
        Airport origin = flight.getRoute().getOrigin();
        Airport dest = flight.getRoute().getDestination();
        return String.format("%s, %s ⤑ %s, %s", origin.getIataId(), origin.getCity(), dest.getIataId(), dest.getCity());
    }

}
