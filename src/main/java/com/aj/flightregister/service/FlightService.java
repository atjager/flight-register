package com.aj.flightregister.service;

import com.aj.flightregister.exception.ItemNotFoundException;
import com.aj.flightregister.model.Flight;
import com.aj.flightregister.model.FlightDTO;
import com.aj.flightregister.repository.AirportRepository;
import com.aj.flightregister.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;

    private final AirportRepository airportRepository;

    public Flight saveFlight(FlightDTO flightDTO) throws ItemNotFoundException {
        Flight flight = new Flight();
        flight.setOrigin(
                airportRepository.findByName(flightDTO.getOrigin())
                        .orElseThrow(() -> new ItemNotFoundException("Not found", flightDTO.getOrigin())));
        flight.setDestination(
                airportRepository.findByName(flightDTO.getDestination())
                        .orElseThrow(() -> new ItemNotFoundException("Not found", flightDTO.getDestination())));
        flight.setDepartureTime(flightDTO.getDepartureTime());
        return flightRepository.save(flight);
    }

    public List<Flight> getFlights() {
        return flightRepository.findAll();
    }

    public List<Flight> getFlightsByOrigin(String origin) throws ItemNotFoundException {
        List<Flight> flights = flightRepository.findByOrigin_Name(origin);
        if (flights.isEmpty()) throw new ItemNotFoundException("Not found", origin);
        return flights;
    }

    //    public List<Flight> getFlightsByDestination(String origin) {
    //        return flightRepository.findByDestination_Name(origin);
    //    }

    public List<Flight> getFlightByDepartureTime(String time) throws ItemNotFoundException {
        //SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        List<Flight> flights = flightRepository.getFlightByDepartureTime(LocalDateTime.parse(time));
        if (flights.isEmpty()) throw new ItemNotFoundException("Not found", time);
        return flights;
    }

    public void deleteFlight(String uuid) throws ItemNotFoundException {
        flightRepository.delete(
                flightRepository
                        .findById(UUID.fromString(uuid))
                        .orElseThrow(() -> new ItemNotFoundException("Not found", uuid)));
    }
}
