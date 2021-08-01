package com.ss.utopia.menu.admin;

import java.io.IOException;
import java.util.List;

import com.ss.utopia.db.PersistenceException;
import com.ss.utopia.domain.Airport;
import com.ss.utopia.repository.AirportRepository;
import com.ss.utopia.service.AirportService;

public class AirportDeleteOperation extends AbstractConsoleAwareOperation {

    private final AirportService airportService = new AirportService(new AirportRepository());
    private final List<Airport> airports;
    private final Airport airport;
    private boolean doGoBack = true;

    AirportDeleteOperation(Airport airport, List<Airport> airports) {
        this.airport = airport;
        this.airports = airports;
    }

    @Override
    public void runOperation() throws IOException {
        try {
            airportService.deleteAirport(airport);
            airports.removeIf(a -> a.getIataId().equals(airport.getIataId()));
            getConsole().print("Airport delete successfully.");
            // TODO: Show user how many flights will be deleted; ask to confirm.
        } catch (PersistenceException ex) {
            getConsole().print("Could not delete airport.");
            doGoBack = false;
        }
    }

    @Override
    public boolean goBack() {
        return doGoBack;
    }
}
