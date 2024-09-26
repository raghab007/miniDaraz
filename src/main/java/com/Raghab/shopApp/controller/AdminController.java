package com.Raghab.shopApp.controller;
import com.Raghab.shopApp.entity._Order;
import com.Raghab.shopApp.entity.Product;
import com.Raghab.shopApp.repository.OrderRepository;
import com.Raghab.shopApp.service.ProductService;
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
    ProductService productService;

    UserService userService;

    OrderRepository orderRepository;
   public AdminController(ProductService adminService, UserService userService, OrderRepository orderRepository){
       this.productService = adminService;
        this.userService = userService;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/product")
    public List<Product> getAllProduct() {
        return productService.getAllProducts();
    }
    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@Valid  @RequestBody Product product) {
        productService.addProduct(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<?> deleteById(@PathVariable Long productId) {
        if (productService.removeProduct(productId)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/product/")
    public ResponseEntity<?> getProductByID(@RequestParam Long productId) {
       Product product =  productService.findById(productId);
        if (product!=null){
            return new ResponseEntity<>(product,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/product/name/{productName}")
    public List<Product> getProductsByName(@PathVariable String productName){
        return productService. getAllProductsByName(productName);
    }

    @GetMapping("/product/brand/{brandName}")
    public List<Product> getProductsByBrand(@PathVariable String brandName){
        return productService. getAllProductsByBrand(brandName);
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
    public List<_Order> getAllOrders(){
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
