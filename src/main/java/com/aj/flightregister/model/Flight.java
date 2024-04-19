package com.aj.flightregister.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Table(name = "flights")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Flight extends BaseEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "origin", nullable = false)
    private Airport origin;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "destination", nullable = false)
    private Airport destination;

    @Column(name = "departure_time", nullable = false)
    private LocalDateTime departureTime;
}
