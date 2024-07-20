package com.Raghab.shopApp.controller;
import com.Raghab.shopApp.entity.Orders;
import com.Raghab.shopApp.entity.Product;
import com.Raghab.shopApp.repository.OrderRepository;
import com.Raghab.shopApp.service.AdminService;
import com.Raghab.shopApp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
@RestController
@RequestMapping("/admin")
public class AdminController {


    AdminService productService;

    UserService userService;

    OrderRepository orderRepository;
    public AdminController(AdminService adminService, UserService userService, OrderRepository orderRepository){
        this.productService = adminService;
        this.userService = userService;
        this.orderRepository = orderRepository;
    }

    @GetMapping("product")
    public ResponseEntity<?> getAllProduct() {
        return new ResponseEntity<>(productService.getProducts(), HttpStatus.FOUND);
    }
    @PostMapping("product")
    public ResponseEntity<?> addProduct(@Valid  @RequestBody Product product) {
        productService.addProduct(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-product/{productId}")
    public ResponseEntity<?> deleteById(@PathVariable Long productId) {
        if (productService.removeById(productId)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/update-product/{productId}")
    public ResponseEntity<?> updateById(@PathVariable Long productID, @RequestBody Product product) {

        boolean saved = productService.updateById(productID, product);
        if (saved) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
    @GetMapping("/registered-users")
        public ResponseEntity<?> getAllRegisteredUsers(){
            return new ResponseEntity<>(userService.getAll(),HttpStatus.OK);
        }
    @DeleteMapping("/delete-user/{userName}")
    public boolean removeUser(@PathVariable String userName){
        return userService.deleteByUserName(userName);
    }

    @GetMapping
    public List<Orders> getAllOrders(){
        return  orderRepository.findAll();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException e){
        var errors = new HashMap<String,String>();
        e.getBindingResult().getAllErrors().forEach(error->{
            var fieldName =( (FieldError)error).getField();
            var errorMessage = error.getDefaultMessage();
            errors.put(fieldName,errorMessage);
        });

        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }


}
