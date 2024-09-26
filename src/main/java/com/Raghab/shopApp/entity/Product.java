package com.Raghab.shopApp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL
    )
    private List<Product_Cart> productCarts = new ArrayList<>();


    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    List<Product_Order> productOrders = new ArrayList<>();

}
