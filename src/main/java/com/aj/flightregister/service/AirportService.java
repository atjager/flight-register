package com.aj.flightregister.service;

import com.aj.flightregister.exception.ItemAlreadyExistsException;
import com.aj.flightregister.model.Airport;
import com.aj.flightregister.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirportService {

    private final AirportRepository airportRepository;

    public List<Airport> getAirports() {
        return airportRepository.findAll();
    }

    public Airport getAirport(String name) {
        return airportRepository
                .findByName(name).get();
    }

    public Airport saveAirport(Airport airport) throws ItemAlreadyExistsException {
        if (airportRepository.findByName(airport.getName()).isEmpty()) {
            return airportRepository.save(airport);
        } else throw new ItemAlreadyExistsException("Conflict", airport.getName());
    }

    public void deleteAirport(String id) {
        airportRepository.delete(airportRepository.findByName(id).get());
    }

    public Airport updateAirport(Airport newAirport) {
        return airportRepository.findByName(newAirport.getName()).map(
                oldAirport -> {
                    Airport updated = oldAirport.updateWith(newAirport);
                    return airportRepository.save(updated);
                }).get();

    }
}
