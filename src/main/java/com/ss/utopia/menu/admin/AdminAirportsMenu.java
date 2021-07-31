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

public class AdminAirportsMenu extends AbstractMenu {

    private final List<Airport> airports;

    public AdminAirportsMenu() {
        super();
        AirportService service = new AirportService(new AirportRepository());
        airports = service.getAllAirports();
    }

    @Override
    protected String getInitialPrompt() {
        StringBuilder sb = new StringBuilder("What airport would you like to manage:")
                .append(newLine())
                .append(newLine());
        for (int i = 0; i < airports.size(); i++) {
            Airport airport = airports.get(i);
            String selection = "  " + (i+1) + ") " + airport.getIataId() + " - " + airport.getCity();
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
        for (int i = 0; i < airports.size(); i++) {
            Airport airport = airports.get(i);
            selections.put(i + 1, new AirportOptionsMenu(airport, airports));
        }
        return selections;
    }
}
