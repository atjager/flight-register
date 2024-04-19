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

/**
 * This service is responsible to handle the required database transactions on the Flight entity.
 */
@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Writes a new Flight object to the table.
     * @param flightDTO The Flight object to be saved with the Airport references.
     * @return The saved Flight object.
     * @throws ItemNotFoundException
     */
    public Flight saveFlight(FlightDTO flightDTO) throws ItemNotFoundException {
        Airport origin = entityManager.find(Airport.class, flightDTO.getOrigin());
        Airport destination = entityManager.find(Airport.class, flightDTO.getDestination());
        if (origin == null || destination == null) {
            throw new ItemNotFoundException("Not found", flightDTO.getOrigin() + " or " + flightDTO.getDestination());
        }
        return flightRepository.save(Flight.builder().origin(origin).destination(destination).departureTime(flightDTO.getDepartureTime()).build());
    }

    /**
     * Queries all the Flight objects from the table;
     * @return A list of Flight objects.
     */
    public List<Flight> getFlights() {
        return flightRepository.findAll();
    }

    /**
     * Queries the Flight records by the origin field.
     * @param origin Origin Airport's name.
     * @return A list of Flight objects that contain the specified origin field.
     * @throws ItemNotFoundException
     */
    public List<Flight> getFlightsByOrigin(String origin) throws ItemNotFoundException {
        List<Flight> flights = flightRepository.findByOrigin_Name(origin);
        if (flights.isEmpty()) throw new ItemNotFoundException("Not found", origin);
        return flights;
    }

    /**
     * Queries the Flight records by the departure time.
     * @param time The departure time in ISO format like: 2007-12-03T10:15:30.
     * The string must represent a valid date-time and is parsed using DateTimeFormatter.ISO_LOCAL_DATE_TIME.
     * @return A list of Flight records that contain the specified departure time.
     * @throws ItemNotFoundException
     */
    public List<Flight> getFlightsByDepartureTime(String time) throws ItemNotFoundException {
        List<Flight> flights = flightRepository.getFlightByDepartureTime(LocalDateTime.parse(time));
        if (flights.isEmpty()) throw new ItemNotFoundException("Not found", time);
        return flights;
    }

    /**
     * Deletes a flight record by the id.
     * @param uuid UUID of the Flight record to be deleted.
     */
    public void deleteFlight(String uuid) {
        flightRepository.delete(flightRepository.findById(UUID.fromString(uuid)).get());
    }
}
