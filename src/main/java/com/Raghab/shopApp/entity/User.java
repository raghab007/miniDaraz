package com.Raghab.shopApp.entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_")
@Builder
public class User {
    @Id
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "address")
    private String address;
    @Column(name = "email",length = 255)
    private String email;
    @Column(unique = true)
    private String phoneNumber;
    String gender;
//    @ElementCollection
//    @CollectionTable(name = "user_role",joinColumns = @JoinColumn(name = "user_id"))
//    @Column(name = "role")
   private String role;
    @OneToOne(mappedBy = "user")
    private Cart cart;
    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<_Order> orders;

}
