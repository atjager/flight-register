package com.aj.flightregister;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.aj.flightregister"})
public class FlightRegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlightRegisterApplication.class, args);
    }

}
