package com.ss.utopia.menu.employee;

import java.io.IOException;

import com.ss.utopia.db.PersistenceException;
import com.ss.utopia.domain.Flight;
import com.ss.utopia.menu.AbstractInputOperation;
import com.ss.utopia.repository.FlightRepository;
import com.ss.utopia.service.FlightService;

class FlightSeatsOperation extends AbstractInputOperation {

    private final Flight flight;

    FlightSeatsOperation(Flight flight) {
        this.flight = flight;
    }

    @Override
    public void runOperation() throws IOException {
        String seatsInput = getInput("How many seats to add: ", (input) -> {
            int seats;
            try {
                seats = Integer.parseInt(input);
            } catch (NumberFormatException ex) {
                return false;
            }
            return flight.catAddSeats(seats);
        }, "Must be an integer and less than seats available (" + flight.getSeatsAvailable() + ")");

        int seatsToAdd = Integer.parseInt(seatsInput);
        try {
            FlightService service = new FlightService(new FlightRepository());
            service.updateFlightField(flight, "reserved_seats", seatsToAdd);
            flight.incrementReservedSeats(seatsToAdd);
            getConsole().print("Seats added to flight successfully.");
        } catch (PersistenceException ex) {
            getConsole().print("Could not add seats to flight.");
        }
        getConsole().printNewLine();
    }

    @Override
    public boolean goBack() {
        return false;
    }
}
