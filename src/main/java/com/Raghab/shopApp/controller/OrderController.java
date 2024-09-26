package com.Raghab.shopApp.controller;

import com.Raghab.shopApp.entity.*;
import com.Raghab.shopApp.enums.OrderStatus;
import com.Raghab.shopApp.mappers.OrderMapper;
import com.Raghab.shopApp.repository.OrderRepository;
import com.Raghab.shopApp.repository.ProductOrderRepository;
import com.Raghab.shopApp.repository.Product_CartRepository;
import com.Raghab.shopApp.service.OrderService;
import com.Raghab.shopApp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {
  private final OrderRepository orderRepository;

    private final UserService userService;
    @Autowired
     private OrderService orderService;
    @Autowired
    ProductOrderRepository productOrderRepository;

    @Autowired
    Product_CartRepository productCartRepository;
    public OrderController(OrderRepository orderRepository, OrderMapper orderMapper, UserService userService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
    }

//    @PostMapping
//    public _Order createOrder(@Valid @RequestBody _Order order) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User user = userService.findBYUserName(authentication.getName());
//        order.setUser(user);
//        return orderRepository.save(order);
//    }

    public _Order createOrder(@PathVariable Integer productCartId){
     Product_Cart productCart =  productCartRepository.findById(productCartId).orElse(null);
     Authentication authentication  = SecurityContextHolder.getContext().getAuthentication();
        _Order order = new _Order();
        if (productCart!=null){
            Product product = productCart.getProduct();
            User user = userService.findBYUserName(authentication.getName());

            order.setUser(user);
            order.setStatus(String.valueOf(OrderStatus.PENDING));
            order.setOrderDate(new Date(2024));
            Product_Order productOrder = new Product_Order();
            productOrder.setProduct(product);
            productOrder.setOrder(order);
            productOrder.setQuantity(productCart.getQuantity());
            orderService.saveOrder(order);
            productCartRepository.delete(productCart);
            productOrderRepository.save(productOrder);

        }

     return order;
    }


    @GetMapping
    public List<_Order> getAll() {
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
