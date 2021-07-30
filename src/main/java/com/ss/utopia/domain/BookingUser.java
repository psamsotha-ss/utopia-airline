package com.ss.utopia.domain;

public class BookingUser {

    private Booking booking;
    private User user;

    public BookingUser() {}

    public BookingUser(Booking booking, User user) {
        this.booking = booking;
        this.user = user;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingUser that = (BookingUser) o;
        if (booking != null ? !booking.equals(that.booking) : that.booking != null) return false;
        return user != null ? user.equals(that.user) : that.user == null;
    }

    @Override
    public int hashCode() {
        int result = booking != null ? booking.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BookingUser{" +
                "booking=" + booking +
                ", user=" + user +
                '}';
    }
}
