package com.Raghab.shopApp.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_Order")
public class Orders {
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

}
