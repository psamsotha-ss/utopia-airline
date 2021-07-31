package com.ss.utopia.menu.admin;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

import com.ss.utopia.console.Color;
import com.ss.utopia.console.Console;
import com.ss.utopia.domain.Flight;
import com.ss.utopia.menu.AbstractMenu;
import com.ss.utopia.menu.MenuOperation;
import com.ss.utopia.menu.MenuSelection;
import com.ss.utopia.repository.FlightRepository;
import com.ss.utopia.service.FlightService;

import static com.ss.utopia.util.Converters.DB_DATE_TIME_FORMAT;
import static com.ss.utopia.util.Converters.dateFromString;
import static com.ss.utopia.util.Converters.formatDateTimeForDb;
import static com.ss.utopia.util.Converters.getLocalTimeFromUtc;
import static com.ss.utopia.util.Converters.getUtcTimeFromLocal;
import static com.ss.utopia.util.StringUtils.newLine;

class FlightUpdateMenu extends AbstractMenu {

    private final Flight flight;
    private final FlightService flightService = new FlightService(new FlightRepository());

    FlightUpdateMenu(Console console, Flight flight) {
        super(console);
        this.flight = flight;
    }

    @Override
    protected String getInitialPrompt() {
        return "What would you like to update:" + newLine()
                + newLine()
                + "  1) Departure time" + newLine()
                + "  2) Price" + newLine();
    }

    @Override
    protected Color getColor() {
        return AdminMenu.DEFAULT_COLOR;
    }

    @Override
    protected String getExitingMessage() {
        return "Returning to Flight options";
    }

    @Override
    public Map<Integer, MenuSelection> getMenuSelections() {
        Map<Integer, MenuSelection> selections = new HashMap<>();
        selections.put(1, new FlightTimUpdateOperation());
        selections.put(2, new FlightPriceUpdateOperation());
        return selections;
    }


    class FlightPriceUpdateOperation implements MenuOperation {

        @Override
        public void runOperation() throws IOException {
            printNoColor("The current price is " + flight.getSeatPrice());
            while (true) {
                String input = prompt("What is the new price? ");
                double newPrice;
                try {
                    newPrice = Double.parseDouble(input);
                } catch (NumberFormatException ex) {
                    print(input + " is not in a valid format.");
                    continue;
                }

                flightService.updateFlightField(flight, "seat_price", newPrice);
                printNoColor("Flight price updated.");
                printNewLine();
                break;
            }
        }

        @Override
        public boolean goBack() {
            return false;
        }
    }

    class FlightTimUpdateOperation implements MenuOperation {

        @Override
        public void runOperation() throws IOException {
            String city = flight.getRoute().getOrigin().getCity();
            printNoColor("The current depart time is " +
                    formatDateTimeForDb(getLocalTimeFromUtc(flight.getDepartureTime(), city)));
            while (true) {
                String input = prompt("What is the new departure date/time? ");
                LocalDateTime newDepartTime;
                try {
                    newDepartTime = dateFromString(input);
                } catch (DateTimeParseException ex) {
                    printNoColor(input + " must be in format " + DB_DATE_TIME_FORMAT);
                    printNewLine();
                    continue;
                }
                newDepartTime = getUtcTimeFromLocal(newDepartTime, city);
                flightService.updateFlightField(flight, "departure_time", formatDateTimeForDb(newDepartTime));
                printNoColor("Flight departure time updated.");
                printNewLine();
                break;
            }
        }

        @Override
        public boolean goBack() {
            return false;
        }
    }
}
