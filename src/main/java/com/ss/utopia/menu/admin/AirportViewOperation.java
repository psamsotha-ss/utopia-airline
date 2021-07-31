package com.ss.utopia.menu.admin;

import com.ss.utopia.domain.Airport;

class AirportViewOperation extends AbstractViewOperation<Airport> {

    AirportViewOperation(Airport airport) {
        super(airport);
    }

    @Override
    protected String formatObject(Airport airport) {
        return airport.toString();
    }
}
