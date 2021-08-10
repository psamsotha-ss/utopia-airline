package com.ss.utopia.menu.employee;

import java.util.HashMap;
import java.util.Map;

import com.ss.utopia.console.Color;
import com.ss.utopia.domain.Flight;
import com.ss.utopia.menu.AbstractMenu;
import com.ss.utopia.menu.MenuSelection;

import static com.ss.utopia.util.Formatters.formatFlight;
import static com.ss.utopia.util.StringUtils.newLine;

class FlightOptionsMenu extends AbstractMenu {

    private final Flight flight;

    FlightOptionsMenu(Flight flight) {
        this.flight = flight;
    }

    @Override
    protected String getInitialPrompt() {
        return "What would you like to do with flight " + formatFlight(flight) + ":" + newLine()
                + newLine()
                + "  1) View details of flight" + newLine()
                + "  2) Update details of flight" + newLine()
                + "  3) Add seats to flight" + newLine();
    }

    @Override
    protected Color getColor() {
        return EmployeeMenu.DEFAULT_COLOR;
    }

    @Override
    protected String getExitingMessage() {
        return "Returning flight list.";
    }

    @Override
    public Map<Integer, MenuSelection> getMenuSelections() {
        Map<Integer, MenuSelection> selections = new HashMap<>();
        selections.put(1, new FlightViewDetailOperation(flight));
        selections.put(2, new FlightUpdateMenu(flight));
        selections.put(3, new FlightSeatsOperation(flight));
        return selections;
    }
}
