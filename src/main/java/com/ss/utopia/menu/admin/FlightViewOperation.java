package com.ss.utopia.menu.admin;

import java.io.IOException;

import com.ss.utopia.console.Console;
import com.ss.utopia.domain.Flight;
import com.ss.utopia.menu.MenuOperation;

class FlightViewOperation implements MenuOperation  {

    private final Console console;
    private final Flight flight;

    FlightViewOperation(Console console, Flight flight) {
        this.console = console;
        this.flight = flight;
    }

    @Override
    public void runOperation() throws IOException {
        console.print(formatFlightDetails(flight));
    }

    @Override
    public boolean goBack() {
        return false;
    }

    private static String formatFlightDetails(Flight flight) {
        return flight.toString();
    }
}
