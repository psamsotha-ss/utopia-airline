package com.ss.utopia.menu.employee;

import java.time.format.DateTimeFormatter;

import com.ss.utopia.domain.Airport;
import com.ss.utopia.domain.Flight;
import com.ss.utopia.menu.admin.AbstractViewOperation;

import static com.ss.utopia.util.Converters.getLocalTimeFromUtc;
import static com.ss.utopia.util.StringUtils.newLine;

class FlightViewDetailOperation extends AbstractViewOperation<Flight> {

    FlightViewDetailOperation(Flight flight) {
        super(flight);
    }

    @Override
    protected String formatObject(Flight flight) {
        return "  Origin:\t\t" + formatAirport(flight.getRoute().getOrigin()) + newLine()
                + "  Destination:\t" + formatAirport(flight.getRoute().getDestination()) + newLine()
                + "  Depart time:\t" + formatDepartureTime(flight) + newLine()
                + "  Price:\t\t" + flight.getSeatPrice() + newLine()
                + "  Reserved:\t\t" + flight.getReservedSeats() + newLine()
                + "  Available:\t" + flight.getSeatsAvailable() + newLine();
    }

    private String formatAirport(Airport airport) {
        return airport.getIataId() + " - " + airport.getCity();
    }

    private String formatDepartureTime(Flight flight) {
        return getLocalTimeFromUtc(flight.getDepartureTime(), flight.getRoute().getOrigin().getCity())
                .format(DateTimeFormatter.ofPattern("EEE, MMM dd yyyy HH:mm"));
    }
}
