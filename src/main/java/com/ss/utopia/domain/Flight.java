package com.ss.utopia.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Flight {

    private int id;
    private Route route;
    private Airplane airplane;
    private LocalDateTime departureTime;
    private int reservedSeats;
    private BigDecimal seatPrice;

    public Flight() {}

    public Flight(Route route, Airplane airplane, LocalDateTime departureTime) {
        this.route = route;
        this.airplane = airplane;
        this.departureTime = departureTime;
    }

    public Flight(int id, Route route, Airplane airplane, LocalDateTime departureTime) {
        this.id = id;
        this.route = route;
        this.airplane = airplane;
        this.departureTime = departureTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public int getReservedSeats() {
        return reservedSeats;
    }

    public void setReservedSeats(int reservedSeats) {
        this.reservedSeats = reservedSeats;
    }

    public BigDecimal getSeatPrice() {
        return seatPrice;
    }

    public void setSeatPrice(BigDecimal seatPrice) {
        this.seatPrice = seatPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return id == flight.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", route=" + route +
                ", airplane=" + airplane +
                ", departureTime=" + departureTime +
                ", reservedSeats=" + reservedSeats +
                ", seatPrice=" + seatPrice +
                '}';
    }
}
