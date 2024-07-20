package com.Raghab.shopApp.controller;
import com.Raghab.shopApp.entity.Product;
import com.Raghab.shopApp.entity.User;
import com.Raghab.shopApp.service.ProductService;
import com.Raghab.shopApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RestController
@RequestMapping("/public")
//@Profile("test")
//This annotation indicates that the bean is available only for the test profile
public class PublicController {

    UserService userService;

    ProductService productService;

    @Autowired
    public PublicController(UserService userService,ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping("/create-user")
    public String createUser(@RequestBody User user) {
        User user2 = userService.findBYUserName(user.getUserName());
        if (user2==null) {
            userService.createUser(user);
        }
        return "login";
    }

    @GetMapping("/products")
    public ModelAndView getallProducts(){
        List<Product> products =  this.productService.getAllProducts();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("product");
        modelAndView.setStatus(HttpStatus.OK);
        modelAndView.addObject("products",products);
        return  modelAndView;
    }
}
