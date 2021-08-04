package com.ss.utopia.menu.traveler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ss.utopia.console.Color;
import com.ss.utopia.domain.Booking;
import com.ss.utopia.domain.Flight;
import com.ss.utopia.domain.User;
import com.ss.utopia.menu.AbstractMenu;
import com.ss.utopia.menu.MenuSelection;
import com.ss.utopia.service.BookingService;

import static com.ss.utopia.util.Formatters.formatDateTimeForDb;
import static com.ss.utopia.util.Formatters.formatFlight;
import static com.ss.utopia.util.StringUtils.newLine;

class BookingCancellationMenu extends AbstractMenu {
    private final List<Booking> bookings;

    BookingCancellationMenu(User traveler) {
        BookingService service = BookingService.newBasicInstance();
        bookings = service.getBookingsByUser(traveler);
    }

    @Override
    protected String getInitialPrompt() {
        StringBuilder sb = new StringBuilder();
        sb.append("Which flight would you like to cancel:").append(newLine())
                .append(newLine());
        for (int i = 0; i < bookings.size(); i++) {
            Booking booking = bookings.get(i);
            Flight flight = booking.getFlightBooking().getFlight();
            String line = "  " + (i+1) + ") " +  formatActive(booking.isActive()) + " - "
                    + formatDateTimeForDb(flight.getDepartureTime()) + " - "  + formatFlight(flight);
            sb.append(line).append(newLine());
        }
        return sb.toString();
    }

    private String formatActive(Boolean isActive) {
        return isActive ? "ACTIVE   " : "CANCELLED";
    }

    @Override
    protected Color getColor() {
        return TravelerMenu.DEFAULT_COLOR;
    }

    @Override
    protected String getExitingMessage() {
        return "Returning to Traveler menu.";
    }

    @Override
    public Map<Integer, MenuSelection> getMenuSelections() {
        Map<Integer, MenuSelection> selections = new HashMap<>();
        for (int i = 0; i < bookings.size(); i++) {
            selections.put(i + 1, new BookingCancellationOperation(bookings.get(i)));
        }
        return selections;
    }
}
