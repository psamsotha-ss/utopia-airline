package com.ss.utopia.domain;

public class Booking {

    private Integer id;
    private boolean isActive;
    private String confirmationCode;
    private FlightBooking flightBooking;

    public Booking() {}

    public Booking(boolean isActive, String confirmationCode) {
        this.isActive = isActive;
        this.confirmationCode = confirmationCode;
    }

    public Booking(Integer id, boolean isActive, String confirmationCode) {
        this.id = id;
        this.isActive = isActive;
        this.confirmationCode = confirmationCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }

    public FlightBooking getFlightBooking() {
        return flightBooking;
    }

    public void setFlightBooking(FlightBooking flightBooking) {
        this.flightBooking = flightBooking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return id.equals(booking.id);
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", isActive=" + isActive +
                ", confirmationCode='" + confirmationCode + '\'' +
                '}';
    }
}
