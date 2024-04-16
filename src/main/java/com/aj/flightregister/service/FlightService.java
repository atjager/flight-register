package com.aj.flightregister.service;

import com.aj.flightregister.model.Flight;
import com.aj.flightregister.model.FlightDTO;
import com.aj.flightregister.repository.AirportRepository;
import com.aj.flightregister.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;

    private final AirportRepository airportRepository;

    public Flight saveFlight(FlightDTO flightDTO) {
        Flight flight = new Flight();
        flight.setOrigin(airportRepository.findByName(flightDTO.getOrigin()).get());
        flight.setDestination(airportRepository.findByName(flightDTO.getDestination()).get());
        flight.setDepartureTime(flightDTO.getDepartureTime());
        return flightRepository.save(flight);
    }

    public List<Flight> getFlights() {
        return flightRepository.findAll();
    }

    public List<Flight> getFlightsByOrigin(String origin) {
        return flightRepository.findByOrigin_Name(origin);
    }

    //    public List<Flight> getFlightsByDestination(String origin) {
    //        return flightRepository.findByDestination_Name(origin);
    //    }

    public List<Flight> getFlightByDepartureTime(String time) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return flightRepository.getFlightByDepartureTime(LocalDateTime.parse(time));
    }

    public void deleteFlight(String uuid) {
        flightRepository.deleteById(UUID.fromString(uuid));
    }
}
