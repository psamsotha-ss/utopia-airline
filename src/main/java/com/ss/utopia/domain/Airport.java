package com.ss.utopia.domain;

public class Airport {

    private String iataId;
    private String city;

    public Airport() {}

    public Airport(String city) {
        this.city = city;
    }

    public Airport(String iataId, String city) {
        this.iataId = iataId;
        this.city = city;
    }

    public String getIataId() {
        return iataId;
    }

    public void setIataId(String iataId) {
        this.iataId = iataId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airport airport = (Airport) o;
        if (iataId != null ? !iataId.equals(airport.iataId) : airport.iataId != null) return false;
        return city != null ? city.equals(airport.city) : airport.city == null;
    }

    @Override
    public int hashCode() {
        int result = iataId != null ? iataId.hashCode() : 0;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "iataId='" + iataId + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
