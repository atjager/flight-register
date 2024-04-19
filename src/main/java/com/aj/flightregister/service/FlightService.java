package com.aj.flightregister.service;

import com.aj.flightregister.exception.ItemNotFoundException;
import com.aj.flightregister.model.Airport;
import com.aj.flightregister.model.Flight;
import com.aj.flightregister.model.FlightDTO;
import com.aj.flightregister.repository.FlightRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public Flight saveFlight(FlightDTO flightDTO) throws ItemNotFoundException {
        Airport origin = entityManager.find(Airport.class, flightDTO.getOrigin());
        Airport destination = entityManager.find(Airport.class, flightDTO.getDestination());
        if (origin == null || destination == null) {
            throw new ItemNotFoundException("Not found", flightDTO.getOrigin() + " or " + flightDTO.getDestination());
        }
        return flightRepository.save(Flight.builder().origin(origin).destination(destination).departureTime(flightDTO.getDepartureTime()).build());
    }

    public List<Flight> getFlights() {
        return flightRepository.findAll();
    }

    public List<Flight> getFlightsByOrigin(String origin) throws ItemNotFoundException {
        List<Flight> flights = flightRepository.findByOrigin_Name(origin);
        if (flights.isEmpty()) throw new ItemNotFoundException("Not found", origin);
        return flights;
    }

    public List<Flight> getFlightByDepartureTime(String time) throws ItemNotFoundException {
        List<Flight> flights = flightRepository.getFlightByDepartureTime(LocalDateTime.parse(time));
        if (flights.isEmpty()) throw new ItemNotFoundException("Not found", time);
        return flights;
    }

    public void deleteFlight(String uuid) throws ItemNotFoundException {
        flightRepository.delete(flightRepository.findById(UUID.fromString(uuid)).get());
    }
}
