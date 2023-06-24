package com.aweperi.onlinefooddeliveryassmnt.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "restaurants")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "[restaurants_seq]")
    @SequenceGenerator(name = "[restaurants_seq]", sequenceName = "[restaurants_seq]", allocationSize = 1)
    private Long id;
    private String name;
    private String address;
}
