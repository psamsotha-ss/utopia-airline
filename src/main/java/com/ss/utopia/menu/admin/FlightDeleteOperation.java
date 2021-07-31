package com.ss.utopia.menu.admin;

import java.io.IOException;
import java.util.List;

import com.ss.utopia.console.Console;
import com.ss.utopia.domain.Flight;
import com.ss.utopia.menu.MenuOperation;
import com.ss.utopia.repository.FlightRepository;
import com.ss.utopia.service.FlightService;

import static com.ss.utopia.util.Formatters.formatFlight;

class FlightDeleteOperation implements MenuOperation  {

    private final FlightService flightService = new FlightService(new FlightRepository());
    private final Flight flight;
    private final Console console;
    private final List<Flight> flights;

    FlightDeleteOperation(Console console, Flight flight, List<Flight> flights) {
        this.console = console;
        this.flight = flight;
        this.flights = flights;
    }

    @Override
    public void runOperation() throws IOException {
        flightService.deleteFlight(flight);
        flights.removeIf(f -> f.getId() == flight.getId());
        console.print("Flight deleted: " + formatFlight(flight));
    }

    @Override
    public boolean goBack() {
        return true;
    }
}
