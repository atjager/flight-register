package com.aj.flightregister.service;

import com.aj.flightregister.exception.ItemAlreadyExistsException;
import com.aj.flightregister.model.Airport;
import com.aj.flightregister.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This service is responsible to handle the required database transactions on the Airport entity.
 */
@Service
@RequiredArgsConstructor
public class AirportService {

    private final AirportRepository airportRepository;

    /**
     * Writes a new Airport object to the table.
     * @param airport The Airport object to be saved.
     * @return The saved Airport object.
     * @throws ItemAlreadyExistsException
     */
    public Airport saveAirport(Airport airport) throws ItemAlreadyExistsException {
        if (airportRepository.findByName(airport.getName()).isEmpty()) {
            return airportRepository.save(airport);
        } else throw new ItemAlreadyExistsException("Conflict", airport.getName());
    }

    /**
     * Queries all the Airport objects from the table.
     * @return A list of Airport objects.
     */
    public List<Airport> getAirports() {
        return airportRepository.findAll();
    }

    /**
     *  Queries one Airport object by the name.
     * @param name The name of the required airport.
     * @return An Airport object.
     */
    public Airport getAirport(String name) {
        return airportRepository.findByName(name).get();
    }


    /**
     *  Deletes an Airport record by the name.
     * @param name The name of the Airport record to be deleted.
     */
    public void deleteAirport(String name) {
        airportRepository.delete(airportRepository.findByName(name).get());
    }

    /**
     * Updates the specified Airport record in the table.
     * @param newAirport The updated Airport object.
     * @return The saved Airport object.
     */
    public Airport updateAirport(Airport newAirport) {
        return airportRepository.findByName(newAirport.getName()).map(
                oldAirport -> {
                    Airport updated = oldAirport.updateWith(newAirport);
                    return airportRepository.save(updated);
                }).get();

    }
}
