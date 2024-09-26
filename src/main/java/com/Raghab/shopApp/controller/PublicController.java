package com.Raghab.shopApp.controller;
import com.Raghab.shopApp.entity.Cart;
import com.Raghab.shopApp.entity.Product;
import com.Raghab.shopApp.entity.Product_Cart;
import com.Raghab.shopApp.entity.User;
import com.Raghab.shopApp.repository.CartRepository;
import com.Raghab.shopApp.repository.Product_CartRepository;
import com.Raghab.shopApp.service.ProductService;
import com.Raghab.shopApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@RestController
@RequestMapping("/public")
//@Profile("test")
//This annotation indicates that the bean is available only for the test profile
public class PublicController {

   private final UserService userService;

   private final ProductService productService;
   private final CartRepository cartRepository;
   @Autowired
   Product_CartRepository productCartRepository;

    @Autowired
    public PublicController(UserService userService,ProductService productService,CartRepository cartRepository) {
        this.userService = userService;
        this.productService = productService;
        this.cartRepository = cartRepository;

    }

    @PostMapping("/create-user")
    public void createUser(@RequestBody User user) {
      ////  User user2 = userService.findBYUserName(user.getUserName());
       // if (user2==null) {
            userService.createUser(user);
            Cart cart = new Cart();
            cart.setUser(user);
             cartRepository.save(cart);

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
    @GetMapping("/login")
    public String login(){
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findBYUserName(authentication.getName());
        return "login";

    }

    public void createCart() {
    }
}
