package com.aj.flightregister.controller;

import com.aj.flightregister.exception.ItemNotFoundException;
import com.aj.flightregister.model.Flight;
import com.aj.flightregister.model.FlightDTO;
import com.aj.flightregister.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/flights/")
public class FlightController {

    private final FlightService flightService;

    @PostMapping("")
    private Flight createFlight(@RequestBody FlightDTO flight) throws ItemNotFoundException {
        return flightService.saveFlight(flight);
    }

    @GetMapping("")
    private List<Flight> getFlights() {
        return flightService.getFlights();
    }

    @GetMapping("origin/{origin}")
    private List<Flight> getFlightsByOrigin(@PathVariable String origin) throws ItemNotFoundException {
        return flightService.getFlightsByOrigin(origin);
    }

    @GetMapping("departure-time/{time}")
    private List<Flight> getFlightsByDepartureTime(@PathVariable String time) throws ItemNotFoundException {
        return flightService.getFlightsByDepartureTime(time);
    }

    @DeleteMapping("{id}")
    private ResponseEntity<String> deleteFlight(@PathVariable String id) {
        flightService.deleteFlight(id);
        return ResponseEntity.ok().build();
    }
}
