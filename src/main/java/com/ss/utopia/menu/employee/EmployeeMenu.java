package com.ss.utopia.menu.employee;

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

public class EmployeeMenu extends AbstractMenu {

    public static final Color DEFAULT_COLOR = Color.BRIGHT_BLUE;

    private final List<Flight> flights;

    public EmployeeMenu(Console console) {
        super(console);
        FlightService service = new FlightService(new FlightRepository());
        flights = service.getAllFlights();
    }

    @Override
    protected String getInitialPrompt() {
        StringBuilder sb = new StringBuilder();
        sb.append("Which flight would you like to manage:").append(newLine())
                .append(newLine());

        for (int i = 0; i < flights.size(); i++) {
            String line = "  " + (i+1) + ") " + formatFlight(flights.get(i));
            sb.append(line).append(newLine());
        }
        return sb.toString();
    }

    @Override
    protected Color getColor() {
        return DEFAULT_COLOR;
    }

    @Override
    protected String getExitingMessage() {
        return "Returning to main menu.";
    }

    @Override
    public Map<Integer, MenuSelection> getMenuSelections() {
        Map<Integer ,MenuSelection> selections = new HashMap<>();
        for (int i = 0; i < flights.size(); i++) {
            selections.put(i + 1, new FlightOptionsMenu(flights.get(i)));
        }
        return selections;
    }
}
