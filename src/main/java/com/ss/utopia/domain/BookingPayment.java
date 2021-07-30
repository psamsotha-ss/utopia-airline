package com.ss.utopia.domain;

public class BookingPayment {

    private Booking booking;
    private String stripeId;
    private boolean refunded;

    public BookingPayment() {}

    public BookingPayment(String stripeId, boolean refunded) {
        this.stripeId = stripeId;
        this.refunded = refunded;
    }

    public BookingPayment(Booking booking, String stripeId, boolean refunded) {
        this.booking = booking;
        this.stripeId = stripeId;
        this.refunded = refunded;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public String getStripeId() {
        return stripeId;
    }

    public void setStripeId(String stripeId) {
        this.stripeId = stripeId;
    }

    public boolean isRefunded() {
        return refunded;
    }

    public void setRefunded(boolean refunded) {
        this.refunded = refunded;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingPayment that = (BookingPayment) o;
        return booking != null ? booking.equals(that.booking) : that.booking == null;
    }

    @Override
    public int hashCode() {
        return booking != null ? booking.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "BookingPayment{" +
                "booking=" + booking +
                ", stripeId='" + stripeId + '\'' +
                ", refunded=" + refunded +
                '}';
    }
}
