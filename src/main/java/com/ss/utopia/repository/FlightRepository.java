package com.ss.utopia.repository;

import java.util.List;

import com.ss.utopia.domain.Flight;

public interface FlightRepository {
    List<Flight> findAll();
}
