package com.ss.utopia.domain;

public class Route {

    private Integer id;
    private Airport origin;
    private Airport destination;

    public Route() {}

    public Route(Airport origin, Airport destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public Route(Integer id, Airport origin, Airport destination) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Airport getOrigin() {
        return origin;
    }

    public void setOrigin(Airport origin) {
        this.origin = origin;
    }

    public Airport getDestination() {
        return destination;
    }

    public void setDestination(Airport destination) {
        this.destination = destination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return id.equals(route.id);
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", origin=" + origin +
                ", destination=" + destination +
                '}';
    }
}
