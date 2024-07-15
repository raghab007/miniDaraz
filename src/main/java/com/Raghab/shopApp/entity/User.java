package com.Raghab.shopApp.entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "address")
    private String address;
    @Column(name = "email",length = 255,unique = true)
    private String email;
    @Column(unique = true,nullable = false)
    private String phoneNumber;
    @Column(updatable = false)
    private LocalDate dateOfBirth;
    String gender;
   private List<String> roles;
    @OneToOne(mappedBy = "user")
    private Cart cart;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Orders> orders;

}
