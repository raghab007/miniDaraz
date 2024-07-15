package com.Raghab.shopApp.controller;
import com.Raghab.shopApp.entity.Cart;
import com.Raghab.shopApp.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartRepository cartRepository;
    @PostMapping
    public Cart createCart(@RequestBody Cart cart){
       return cartRepository.save(cart);
    }
}
