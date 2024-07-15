package com.Raghab.shopApp.controller;

import  com.Raghab.shopApp.entity.User;
import com.Raghab.shopApp.entity.UserResponseDto;
import com.Raghab.shopApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PutMapping("/update-user")
    public ResponseEntity<?> updateByUserName(@RequestBody User user){
     boolean isUpdated =   userService.updateByUserName(user);
     if (isUpdated){
         return  new ResponseEntity<>(HttpStatus.OK);
     }

     return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

//    public UserResponseDto userResponseDto(User user){
//        return new UserResponseDto(user.getUserName());
//    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAll();
                //.stream().map(this::userResponseDto).collect(Collectors.toList());
    }

}
