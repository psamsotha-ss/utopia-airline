package com.ss.utopia.util;

import com.ss.utopia.domain.Airport;
import com.ss.utopia.domain.Flight;

/**
 * Utility class for creating formatted strings
 */
public class Formatters {

    private Formatters() {}

    /**
     * Format a {@code Flight} in the format "LAX, Los Angeles ⤑ SFO, San Francisco"
     * @param flight the flight to format
     * @return the formatted flight
     */
    public static String formatFlight(Flight flight) {
        Airport origin = flight.getRoute().getOrigin();
        Airport dest = flight.getRoute().getDestination();
        return String.format("%s, %s ⤑ %s, %s", origin.getIataId(), origin.getCity(), dest.getIataId(), dest.getCity());
    }
}
