package com.ss.utopia.domain;

public class BookingGuest {

    private Booking booking;
    private String contactEmail;
    private String contactPhone;

    public BookingGuest() {}

    public BookingGuest(Booking booking, String contactEmail, String contactPhone) {
        this.booking = booking;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingGuest that = (BookingGuest) o;
        return booking.equals(that.booking);
    }

    @Override
    public int hashCode() {
        return booking.hashCode();
    }

    @Override
    public String toString() {
        return "BookingGuest{" +
                "booking=" + booking +
                ", contactEmail='" + contactEmail + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                '}';
    }
}
