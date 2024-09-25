package com.Raghab.shopApp.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product_Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productCartId;

    @ManyToOne
    private  Product product;

    @ManyToOne
    private  Cart cart;
}
