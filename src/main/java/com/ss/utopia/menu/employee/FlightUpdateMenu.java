package com.ss.utopia.menu.employee;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

import com.ss.utopia.console.Color;
import com.ss.utopia.db.PersistenceException;
import com.ss.utopia.domain.Flight;
import com.ss.utopia.menu.AbstractMenu;
import com.ss.utopia.menu.MenuSelection;
import com.ss.utopia.menu.admin.AbstractInputOperation;
import com.ss.utopia.menu.admin.AdminMenu;
import com.ss.utopia.repository.FlightRepository;
import com.ss.utopia.service.FlightService;

import static com.ss.utopia.util.Converters.DB_DATE_TIME_FORMAT;
import static com.ss.utopia.util.Converters.dateFromString;
import static com.ss.utopia.util.Converters.formatDateTimeForDb;
import static com.ss.utopia.util.StringUtils.newLine;

public class FlightUpdateMenu extends AbstractMenu {

    private final Flight flight;

    FlightUpdateMenu(Flight flight) {
        this.flight = flight;
    }

    @Override
    protected String getInitialPrompt() {
        return "What would you like to update on the flight:" + newLine()
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
        return "Returning to flight options.";
    }

    @Override
    public Map<Integer, MenuSelection> getMenuSelections() {
        Map<Integer, MenuSelection> selections = new HashMap<>();
        selections.put(1, new FlightTimeUpdateOperation());
        selections.put(2, new FlightPriceUpdateOperation());
        return selections;
    }

    private class FlightPriceUpdateOperation extends AbstractInputOperation {

        @Override
        public void runOperation() throws IOException {
            String priceInput = getInput("What is the new price: ", (input) -> {
                try {
                    Double.parseDouble(input);
                    return true;
                } catch (NumberFormatException ex) {
                    return false;
                }
            }, "Must be in 00.00 format.");
            double newPrice = Double.parseDouble(priceInput);
            try {
                FlightService service = new FlightService(new FlightRepository());
                service.updateFlightField(flight, "seat_price", newPrice);
                flight.setSeatPrice(BigDecimal.valueOf(newPrice));
                getConsole().print("Flight price updated successfully.");
            } catch (PersistenceException ex) {
                getConsole().print("Flight price could not be updated.");
            }
            getConsole().printNewLine();
        }

        @Override
        public boolean goBack() {
            return false;
        }
    }

    private class FlightTimeUpdateOperation extends AbstractInputOperation {

        @Override
        public void runOperation() throws IOException {
            String timeInput = getInput("What is the new departure time: ", (input) -> {
                try {
                    dateFromString(input);
                    return true;
                } catch (DateTimeParseException ex) {
                    return false;
                }
            }, "Must be in the format " + DB_DATE_TIME_FORMAT);
            LocalDateTime newDepartTime = dateFromString(timeInput);
            try {
                FlightService service = new FlightService(new FlightRepository());
                service.updateFlightField(flight, "departure_time", formatDateTimeForDb(newDepartTime));
                flight.setDepartureTime(newDepartTime);
                getConsole().print("Flight departure time update successfully.");
            } catch (PersistenceException ex) {
                getConsole().print("Flight departure time could not be updated.");
            }
            getConsole().printNewLine();
        }

        @Override
        public boolean goBack() {
            return false;
        }
    }
}
