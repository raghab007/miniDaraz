package com.Raghab.shopApp.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Cart {
    @Id
    Integer cartId;
    @OneToOne
    @JoinColumn(name = "product_id",nullable = true)
    Product product;
    @OneToOne
    @JoinColumn(name = "user_id")
    User user;
}
