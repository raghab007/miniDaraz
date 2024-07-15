package com.Raghab.shopApp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@Setter
@Entity
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull(message = "Quantity cannot be null")
    private int quantity;
    @NotNull(message = "Name cannot be null")
    private String name;
    @NotNull(message = "Description cannot be null")
    private String description;
    @NotNull(message = "Brand cannot be null")
    private String brand;
    @OneToOne(
            mappedBy = "product"
    )
    private Cart cart;

}
