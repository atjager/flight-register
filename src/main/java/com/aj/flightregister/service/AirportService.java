package com.aj.flightregister.service;

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
        return airportRepository.findByName(name).get();
    }

    public Airport saveAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    public void deleteAirport(String id) {
        airportRepository.deleteById(id);
    }

    public Airport updateAirport(Airport newAirport) {
        return airportRepository.findByName(newAirport.getName()).map(
                oldAirport -> {
                    Airport updated = oldAirport.updateWith(newAirport);
                    return airportRepository.save(updated);
                }).get();

    }
}
