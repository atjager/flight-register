package com.aj.flightregister.model.nondb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightDTO {

    private String origin, destination;

    private LocalDateTime departureTime;
}
