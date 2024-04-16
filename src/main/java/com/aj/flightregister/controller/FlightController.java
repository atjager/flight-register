package com.aj.flightregister.controller;

import com.aj.flightregister.model.Flight;
import com.aj.flightregister.model.FlightDTO;
import com.aj.flightregister.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/flights/")
public class FlightController {

    private final FlightService flightService;

    @PostMapping("")
    private Flight createFlight(@RequestBody FlightDTO flight) {
        return flightService.saveFlight(flight);
    }

    @GetMapping("")
    private List<Flight> getFlights() {
        return flightService.getFlights();
    }

    @GetMapping("origin/{origin}")
    private List<Flight> getFlightsByOrigin(@PathVariable String origin) {
        return flightService.getFlightsByOrigin(origin);
    }

    @GetMapping("departure-time/{time}")
    private List<Flight> getFlightsByDepartureTime(@PathVariable String time) throws ParseException {
        return flightService.getFlightByDepartureTime(time);
    }

    //    @GetMapping("destination/{destination}")
    //    private List<Flight> getFlightsByDestination(@PathVariable String destination) {
    //        return flightService.getFlightsByDestination(destination);
    //    }
}
