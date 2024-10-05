package com.Raghab.shopApp.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer cartId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private  User user;

    @OneToMany(mappedBy = "cart")
    private List<Product_Cart> productCarts  = new ArrayList<>();

}
