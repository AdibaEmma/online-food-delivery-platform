package com.aweperi.onlinefooddeliveryassmnt.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "menu_items")
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "[menu_items_seq]")
    @SequenceGenerator(name = "[menu_items_seq]", sequenceName = "[menu_items_seq]", allocationSize = 1)
    @Column(name = "menu_item_id")
    private Long menuItemId;

    private String name;
    private BigDecimal price;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;
}
