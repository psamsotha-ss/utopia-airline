package com.ss.utopia.menu.admin.airport;

import java.io.IOException;
import java.util.List;

import com.ss.utopia.db.PersistenceException;
import com.ss.utopia.domain.Airport;
import com.ss.utopia.menu.AbstractInputOperation;
import com.ss.utopia.repository.AirportRepository;
import com.ss.utopia.service.AirportService;

import static java.util.Comparator.comparing;

class AirportCreateOperation extends AbstractInputOperation {

    private final List<Airport> airports;

    AirportCreateOperation(List<Airport> airports) {
        this.airports = airports;
    }

    @Override
    public void runOperation() throws IOException {
        AirportService service = new AirportService(new AirportRepository());;
        String iataId = getInput("Enter new IATA code (3 chars): ", (code) -> code.matches("[a-zA-Z]{3}"));
        String city = getInput("Enter the city, state: ", (cityState) -> cityState.matches("[a-zA-Z ]+,\\s+[a-zA-Z]{2}"));
        Airport airport = new Airport(iataId.toUpperCase(), city);
        try {
            service.createAirport(airport);
            airports.add(airport);
            airports.sort(comparing(Airport::getIataId));
            getConsole().print("Airport created successfully.");
        } catch (PersistenceException ex) {
            getConsole().print("Could not create airport.");
        }
        getConsole().printNewLine();
    }

    @Override
    public boolean goBack() {
        return false;
    }
}
