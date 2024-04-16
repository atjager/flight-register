package com.aj.flightregister.controller;

import com.aj.flightregister.model.Airport;
import com.aj.flightregister.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/airports/")
public class AirportController {

    private final AirportService airportService;

    @GetMapping("")
    private List<Airport> getAirports() {
        return airportService.getAirports();
    }

    @GetMapping("{id}")
    private Airport getAirport(@PathVariable String id) {
        return airportService.getAirport(id);
    }

    @PostMapping("")
    private Airport createAirport(@RequestBody Airport airport) {
        return airportService.saveAirport(airport);
    }


    @PutMapping("")
    private Airport updateAirport(@RequestBody Airport airport) {
        return airportService.updateAirport(airport);
    }

    @DeleteMapping("{id}")
    private ResponseEntity<String> deleteAirport(@PathVariable String id) {
        airportService.deleteAirport(id);
        return ResponseEntity.noContent().build();
    }
}
