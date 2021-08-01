package com.ss.utopia.menu.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ss.utopia.console.Color;
import com.ss.utopia.domain.Airport;
import com.ss.utopia.menu.AbstractMenu;
import com.ss.utopia.menu.MenuSelection;
import com.ss.utopia.repository.AirportRepository;
import com.ss.utopia.service.AirportService;

import static com.ss.utopia.util.StringUtils.newLine;
import static java.util.Comparator.comparing;

public class AdminAirportsMenu extends AbstractMenu {

    private final List<Airport> airports;

    public AdminAirportsMenu() {
        super();
        AirportService service = new AirportService(new AirportRepository());
        airports = service.getAllAirports();
        airports.sort(comparing(Airport::getIataId));
    }

    @Override
    protected String getInitialPrompt() {
        StringBuilder sb = new StringBuilder("What airport would you like to manage:")
                .append(newLine())
                .append(newLine());
        sb.append("  1) Create new Airport").append(newLine());

        for (int i = 0; i < airports.size(); i++) {
            Airport airport = airports.get(i);
            String selection = "  " + (i+2) + ") " + airport.getIataId() + " - " + airport.getCity();
            sb.append(selection).append(newLine());
        }
        return sb.toString();
    }

    @Override
    protected Color getColor() {
        return AdminMenu.DEFAULT_COLOR;
    }

    @Override
    protected String getExitingMessage() {
        return "Returning to Admin menu.";
    }

    @Override
    public Map<Integer, MenuSelection> getMenuSelections() {
        Map<Integer, MenuSelection> selections = new HashMap<>();
        selections.put(1, new AirportCreateOperation(airports));

        for (int i = 0; i < airports.size(); i++) {
            Airport airport = airports.get(i);
            selections.put(i + 2, new AirportOptionsMenu(airport, airports));
        }
        return selections;
    }
}
