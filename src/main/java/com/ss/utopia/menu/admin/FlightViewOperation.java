package com.ss.utopia.menu.admin;

import com.ss.utopia.domain.Flight;

class FlightViewOperation extends AbstractViewOperation<Flight> {

    FlightViewOperation(Flight flight) {
        super(flight);
    }

    @Override
    protected String formatObject(Flight flight) {
        return flight.toString();
    }
}
