package com.ss.utopia.menu.traveler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ss.utopia.console.Color;
import com.ss.utopia.domain.Flight;
import com.ss.utopia.domain.User;
import com.ss.utopia.menu.AbstractMenu;
import com.ss.utopia.menu.MenuSelection;
import com.ss.utopia.repository.FlightRepository;
import com.ss.utopia.service.FlightService;

import static com.ss.utopia.util.Formatters.formatFlight;
import static com.ss.utopia.util.StringUtils.newLine;

class FlightSelectionMenu extends AbstractMenu {

    private final List<Flight> flights;
    private final User traveler;

    FlightSelectionMenu(User traveler) {
        FlightService service = new FlightService(new FlightRepository());
        flights = service.getAllFlights();
        this.traveler = traveler;
    }

    @Override
    protected String getInitialPrompt() {
        StringBuilder sb = new StringBuilder();
        sb.append("Select a flight to book:").append(newLine())
                .append(newLine());
        for (int i = 0; i < flights.size(); i++) {
            String line = "  " + (i+1) + ") " + formatFlight(flights.get(i));
            sb.append(line).append(newLine());
        }
        return sb.toString();
    }

    @Override
    protected Color getColor() {
        return TravelerMenu.DEFAULT_COLOR;
    }

    @Override
    protected String getExitingMessage() {
        return "Returning to Traveler menu.";
    }

    @Override
    public Map<Integer, MenuSelection> getMenuSelections() {
        Map<Integer, MenuSelection> selections = new HashMap<>();
        for (int i = 0; i < flights.size(); i++) {
            selections.put(i + 1, new FlightBookingOperation(flights.get(i), traveler));
        }
        return selections;
    }
}
