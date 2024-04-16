package com.aj.flightregister.repository;

import com.aj.flightregister.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirportRepository extends JpaRepository<Airport, String> {

    Optional<Airport> findByName(String name);
}
