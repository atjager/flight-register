package com.aj.flightregister.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.UUID;

@Data
@Table
@Entity
public class Flight extends BaseEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "origin", nullable = false)
    private Airport origin;

    @ManyToOne(fetch = FetchType.EAGER)
    @Column(name = "destination", nullable = false)
    private Airport destination;

    @Column(name = "departure_time", nullable = false)
    private Date departureTime;
}
