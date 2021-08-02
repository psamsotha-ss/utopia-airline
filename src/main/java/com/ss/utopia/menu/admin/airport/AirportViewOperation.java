package com.ss.utopia.menu.admin.airport;

import com.ss.utopia.domain.Airport;
import com.ss.utopia.menu.AbstractViewOperation;

class AirportViewOperation extends AbstractViewOperation<Airport> {

    AirportViewOperation(Airport airport) {
        super(airport);
    }

    @Override
    protected String formatObject(Airport airport) {
        return airport.toString();
    }
}
