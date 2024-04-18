package com.aj.flightregister.repository;

import com.aj.flightregister.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FlightRepository extends JpaRepository<Flight, UUID> {

    Optional<Flight> findById(UUID uuid);

    void deleteById(UUID uuid);

    List<Flight> getFlightByDepartureTime(LocalDateTime departureTime);

    List<Flight> findByOrigin_Name(String origin);

}
