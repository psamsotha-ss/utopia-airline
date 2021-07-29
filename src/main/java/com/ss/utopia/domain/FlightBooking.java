package com.ss.utopia.domain;

public class FlightBooking {

    private Flight flight;
    private Booking booking;

    public FlightBooking() {}

    public FlightBooking(Flight flight, Booking booking) {
        this.flight = flight;
        this.booking = booking;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightBooking that = (FlightBooking) o;
        if (flight != null ? !flight.equals(that.flight) : that.flight != null) return false;
        return booking != null ? booking.equals(that.booking) : that.booking == null;
    }

    @Override
    public int hashCode() {
        int result = flight != null ? flight.hashCode() : 0;
        result = 31 * result + (booking != null ? booking.hashCode() : 0);
        return result;
    }
}
