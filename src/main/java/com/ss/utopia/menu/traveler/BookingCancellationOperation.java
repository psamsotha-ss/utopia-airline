package com.ss.utopia.menu.traveler;

import java.io.IOException;

import com.ss.utopia.db.PersistenceException;
import com.ss.utopia.domain.Booking;
import com.ss.utopia.menu.AbstractInputOperation;
import com.ss.utopia.service.BookingService;

class BookingCancellationOperation extends AbstractInputOperation {

    private final Booking booking;

    BookingCancellationOperation(Booking booking) {
        this.booking = booking;
    }

    @Override
    public void runOperation() throws IOException {
        if (!booking.isActive()) {
            getConsole().print("Flight is already cancelled.");
            getConsole().printNewLine();
            return;
        }
        try {
            BookingService service = BookingService.newInstance();
            service.setBookingStatus(booking, Boolean.FALSE);
            booking.setActive(Boolean.FALSE);
            getConsole().print("Booking cancelled successfully.");
        } catch (PersistenceException ex) {
            getConsole().print("Booking could not be cancelled.");
        }
        getConsole().printNewLine();
    }

    @Override
    public boolean goBack() {
        return false;
    }
}
