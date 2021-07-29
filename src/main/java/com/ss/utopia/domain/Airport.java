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
}
