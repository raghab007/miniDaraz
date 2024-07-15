package com.Raghab.shopApp.controller;
import com.Raghab.shopApp.entity.User;
import com.Raghab.shopApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/public")
//@Profile("test")
//This annotation indicates that the bean is available only for the test profile
public class PublicController {

    UserService userService;

    @Autowired
    public PublicController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/create-user")
//    @Profile("/test")
    public ResponseEntity<?> createUser(@RequestBody User user){
        userService.createUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
