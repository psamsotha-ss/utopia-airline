package com.ss.utopia.domain;

public class BookingAgent {

    private Booking booking;
    private User agent;

    public BookingAgent() {}

    public BookingAgent(Booking booking, User agent) {
        this.booking = booking;
        this.agent = agent;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public User getAgent() {
        return agent;
    }

    public void setAgent(User agent) {
        this.agent = agent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingAgent that = (BookingAgent) o;
        if (booking != null ? !booking.equals(that.booking) : that.booking != null) return false;
        return agent != null ? agent.equals(that.agent) : that.agent == null;
    }

    @Override
    public int hashCode() {
        int result = booking != null ? booking.hashCode() : 0;
        result = 31 * result + (agent != null ? agent.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BookingAgent{" +
                "booking=" + booking +
                ", agent=" + agent +
                '}';
    }
}

