package com.ss.utopia.menu.traveler;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.ss.utopia.db.PersistenceException;
import com.ss.utopia.domain.Booking;
import com.ss.utopia.domain.Flight;
import com.ss.utopia.domain.User;
import com.ss.utopia.menu.AbstractInputOperation;
import com.ss.utopia.service.BookingService;

public class FlightBookingOperation extends AbstractInputOperation {

    private final Flight flight;
    private final User traveler;

    FlightBookingOperation(Flight flight, User traveler) {
        this.flight = flight;
        this.traveler = traveler;
    }

    @Override
    public void runOperation() throws IOException {
        getConsole().print("Hello, " + traveler.getGivenName() + ". Please enter the following:");
        String dob = getInput("Date of birth (YYYY-MM-DD): ", (input) -> {
            try {
                LocalDate.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                return true;
            } catch (DateTimeParseException ex) {
                return false;
            }
        }, "Date format is incorrect.");
        String gender = getInput("Gender (m/f): ", (input) -> input.matches("[mMfF]"));
        String address = getInput("Address: ", (input) -> true);
        try {
            Booking booking = BookingService.newInstance().createBooking(traveler, flight, dob, gender, address);
            getConsole().printNewLine();
            getConsole().print("Booking is set.");
            getConsole().print("Confirmation code: " + booking.getConfirmationCode());
        } catch (PersistenceException ex) {
            getConsole().print("Booking couldn't be made.");
        }
        getConsole().printNewLine();
    }

    @Override
    public boolean goBack() {
        return true;
    }
}
