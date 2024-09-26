package com.Raghab.shopApp.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data

public class Product_Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productCartId;

    @ManyToOne
    private  Product product;

    @ManyToOne
    private  Cart cart;

    private Integer quantity;

    @Override
    public String toString() {
        return STR."Product_Cart{productCartId=\{productCartId}, product=\{product}, cart=\{cart}, quantity=\{quantity}\{'}'}";
    }
}
