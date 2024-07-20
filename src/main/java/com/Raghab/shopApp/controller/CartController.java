package com.Raghab.shopApp.controller;
import com.Raghab.shopApp.entity.Cart;
import com.Raghab.shopApp.entity.User;
import com.Raghab.shopApp.repository.CartRepository;

import java.net.Authenticator;
import java.util.List;

import com.Raghab.shopApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserService userService;

    @PostMapping
    public Cart createCart(@RequestBody Cart cart) {
        return cartRepository.save(cart);
    }
    @DeleteMapping("delete/{cart}")
    public void deleteCart(){
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
       String userName = authentication.getName();
       User user = userService.findBYUserName(userName);
       Cart cart = user.getCart();



       
    }

}
