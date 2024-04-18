package com.aj.flightregister.service;

import com.aj.flightregister.exception.ItemAlreadyExistsException;
import com.aj.flightregister.exception.ItemNotFoundException;
import com.aj.flightregister.exception.LockedException;
import com.aj.flightregister.model.Airport;
import com.aj.flightregister.repository.AirportRepository;
import com.aj.flightregister.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirportService {

    private final AirportRepository airportRepository;

    private final FlightRepository flightRepository;

    public List<Airport> getAirports() {
        return airportRepository.findAll();
    }

    public Airport getAirport(String name) throws ItemNotFoundException {
        return airportRepository
                .findByName(name)
                .orElseThrow(() -> new ItemNotFoundException("Not found", name));
    }

    public Airport saveAirport(Airport airport) throws ItemAlreadyExistsException {
        if (airportRepository.findByName(airport.getName()).isEmpty()) {
            return airportRepository.save(airport);
        } else throw new ItemAlreadyExistsException("Conflict", airport.getName());
    }

    public void deleteAirport(String id) throws ItemNotFoundException, LockedException {
        Airport airport = airportRepository.findByName(id).orElseThrow(
                () -> new ItemNotFoundException("Not found", id));
        if (!flightRepository.findByOrigin_NameOrDestination_Name(airport.getName(), airport.getName()).isEmpty()) {
            throw new LockedException("Foreign key", id);
        }
        airportRepository.delete(
                airportRepository
                        .findByName(id)
                        .orElseThrow(() -> new ItemNotFoundException("Not found", id)));
    }

    public Airport updateAirport(Airport newAirport) throws ItemNotFoundException {
        return airportRepository.findByName(newAirport.getName()).map(
                oldAirport -> {
                    Airport updated = oldAirport.updateWith(newAirport);
                    return airportRepository.save(updated);
                }).orElseThrow(() -> new ItemNotFoundException("Not found", newAirport.getName()));

    }
}
