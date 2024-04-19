package com.aj.flightregister.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Table(name = "airports")
@Entity
@NoArgsConstructor
public class Airport extends BaseEntity {

    @Id
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "gps_coordinates", nullable = false)
    @Pattern(regexp = "^(-?\\d+(\\.\\d+)?),\\s*(-?\\d+(\\.\\d+)?)$")
    private String gpsCoordinates;

    public Airport(String name, String city, String gpsCoordinates, Date created) {
        this.name = name;
        this.city = city;
        this.gpsCoordinates = gpsCoordinates;
        this.created = created;
    }

    public Airport updateWith(Airport airport) {
        return new Airport(this.name, airport.city, airport.gpsCoordinates, created);
    }

}
