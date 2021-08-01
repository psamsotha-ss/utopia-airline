package com.ss.utopia.menu.admin.airport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ss.utopia.console.Color;
import com.ss.utopia.domain.Airport;
import com.ss.utopia.menu.AbstractMenu;
import com.ss.utopia.menu.MenuSelection;
import com.ss.utopia.menu.admin.AdminMenu;

import static com.ss.utopia.util.StringUtils.newLine;

class AirportOptionsMenu extends AbstractMenu {

    private final List<Airport> airports;
    private final Airport airport;

    AirportOptionsMenu(Airport airport, List<Airport> airports) {
        this.airport = airport;
        this.airports = airports;
    }

    @Override
    protected String getInitialPrompt() {
        return "Airport: " + airport.getIataId() + " - " + airport.getCity() + newLine()
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
        return "Returning to Airports list.";
    }

    @Override
    public Map<Integer, MenuSelection> getMenuSelections() {
        Map<Integer, MenuSelection> selections = new HashMap<>();
        selections.put(1, new AirportViewOperation(airport));
        selections.put(2, new AirportDeleteOperation(airport, airports));
        selections.put(3, new AirportUpdateOperation());
        return selections;
    }
}
