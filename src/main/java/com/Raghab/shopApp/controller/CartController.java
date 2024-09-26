package com.Raghab.shopApp.controller;
import com.Raghab.shopApp.entity.Cart;
import com.Raghab.shopApp.entity.Product;
import com.Raghab.shopApp.entity.Product_Cart;
import com.Raghab.shopApp.entity.User;
import com.Raghab.shopApp.repository.CartRepository;

import java.net.Authenticator;
import java.util.List;
import java.util.Objects;

import com.Raghab.shopApp.repository.Product_CartRepository;
import com.Raghab.shopApp.service.AdminService;
import com.Raghab.shopApp.service.ProductService;
import com.Raghab.shopApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

   // static  int cartID;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    @Autowired
    Product_CartRepository productCartRepository;

    @Autowired
    AdminService adminService;

    @PostMapping()
    public ResponseEntity<?> createCart(@RequestBody Cart cart) {
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findBYUserName(authentication.getName());
        cart.setUser(user);
        Product_Cart productCart = new Product_Cart();
        productCart.setCart(cart);
        Cart cart1 =  cartRepository.save(cart);
        productCartRepository.save(productCart);
        return new ResponseEntity<>(cart,HttpStatus.CREATED);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteCart(){
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
       String userName = authentication.getName();
       User user = userService.findBYUserName(userName);
       Cart cart = user.getCart();
       List<Product_Cart> productCarts= cart.getProductCarts();
       for (Product_Cart productCart:productCarts){
           System.out.println(productCart);
           Product product =productService.findById(productCart.getProduct().getId());
           System.out.println(product.getQuantity());
       }

//       for (Product_Cart productCart:productCarts){
//          Product product =  productCart.getProduct();
//          Product product1 = productService.findById(product.getId());
//          product1.setQuantity(product.getQuantity()+product1.getQuantity());
//          productCartRepository.delete(productCart);
//       }
       return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/product/{productId}/quantity/{quantity}")
    public ResponseEntity<?> addProductToCart(@PathVariable Long productId,@PathVariable Integer quantity){

        Product product  = productService.findById(productId);
        if (product.getQuantity()<quantity || quantity<=0 ){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findBYUserName(userName);
        Cart cart = user.getCart();
        Product_Cart productCart = new Product_Cart();
        productCart.setQuantity(quantity);
        productCart.setProduct(product);
        productCart.setCart(cart);


        List<Product_Cart> productCarts  = productCartRepository.findAll();
        for (Product_Cart productCart1:productCarts){
          Long id =   productCart1.getProduct().getId();
            System.out.println("ID"+id);
          if (id.equals(productId)){
              System.out.println("Hello world");
            Product product1 = productCart1.getProduct();
              System.out.println(STR."Quantity:\{product1.getQuantity()}");
              product1.setQuantity(product1.getQuantity()-quantity);
              productCart1.setQuantity(productCart1.getQuantity()+quantity);
//              product.setQuantity(product.getQuantity()-quantity);
              System.out.println(product);
              adminService.addProduct(product1);
              productCartRepository.save(productCart1);
              return  new ResponseEntity<>(HttpStatus.CREATED);
          }
        }
        if (product.getQuantity()>0){
            productCartRepository.save(productCart);
            return new ResponseEntity<>(HttpStatus.MULTI_STATUS);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
