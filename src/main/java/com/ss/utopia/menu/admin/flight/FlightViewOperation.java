package com.ss.utopia.menu.admin.flight;

import com.ss.utopia.domain.Flight;
import com.ss.utopia.menu.AbstractViewOperation;

class FlightViewOperation extends AbstractViewOperation<Flight> {

    FlightViewOperation(Flight flight) {
        super(flight);
    }

    @Override
    protected String formatObject(Flight flight) {
        return flight.toString();
    }
}
