package com.ss.utopia.service;

import java.util.List;

import com.ss.utopia.domain.Flight;
import com.ss.utopia.repository.FlightRepository;

public class FlightService {

    private FlightRepository repository;

    public FlightService(FlightRepository repository) {
        this.repository = repository;
    }

    public List<Flight> getFlights() {
        return repository.findAll();
    }
}
