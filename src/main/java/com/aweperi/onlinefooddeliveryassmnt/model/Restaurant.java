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
    @GeneratedValue
    private Long id;
    private String name;
    private String address;
}
