package com.Raghab.shopApp.controller;

import com.Raghab.shopApp.entity.Orders;
import com.Raghab.shopApp.entity.User;
import com.Raghab.shopApp.mappers.OrderMapper;
import com.Raghab.shopApp.repository.OrderRepository;
import com.Raghab.shopApp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    OrderRepository orderRepository;

    OrderMapper orderMapper;

    UserService userService;

    public OrderController(OrderRepository orderRepository, OrderMapper orderMapper, UserService userService) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.userService = userService;
    }

    @PostMapping
    public Orders createOrder(@Valid @RequestBody Orders order) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findBYUserName(authentication.getName());
        order.setUser(user);
        return orderRepository.save(order);
    }


    @GetMapping
    public List<Orders> getAll() {
        return orderRepository.findAll();
    }

//   @GetMapping("{/userName}")
//   public Orders getByUserName(@PathVariable("userName") String userName){
//       return orderRepository.findByOrderId(userName);
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException exp) {
        var errors = new HashMap<String, String>();
        exp.getBindingResult().getAllErrors().forEach(error -> {
            var fieldName = ((FieldError) error).getField();
            var errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

    }


}
