package com.Raghab.shopApp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_Order")
public class _Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long orderId;

   Date orderDate;
    @NotNull(message = "Status Cannot be null")
   String status;

    @ManyToOne
    @JoinColumn(name = "user_id")  // Ensure join column is not nullable
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    List<Product_Order> productOrders = new ArrayList<>();

}
