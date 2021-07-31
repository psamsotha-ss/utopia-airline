package com.ss.utopia.menu.admin;

import java.io.IOException;

import com.ss.utopia.console.Console;
import com.ss.utopia.domain.Flight;
import com.ss.utopia.menu.MenuOperation;
import com.ss.utopia.repository.FlightRepository;
import com.ss.utopia.service.FlightService;

public class FlightDeleteOperation implements MenuOperation  {

    private final FlightService flightService = new FlightService(new FlightRepository());
    private final Flight flight;
    private final Console console;

    FlightDeleteOperation(Console console, Flight flight) {
        this.console = console;
        this.flight = flight;
    }

    @Override
    public void runOperation() throws IOException {
        flightService.deleteFlight(flight);
        console.print("Flight deleted");
    }

    @Override
    public boolean goBack() {
        return true;
    }
}
