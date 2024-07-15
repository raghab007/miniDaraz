package com.Raghab.shopApp.controller;
import com.Raghab.shopApp.entity.Product;
import com.Raghab.shopApp.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class AdminController {

    @Autowired
    AdminService productService;

    @GetMapping
    public ResponseEntity<?>  getAllProduct(){
        return new ResponseEntity<>(productService.getProducts(),HttpStatus.FOUND);
    }
    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody Product product){
        productService.addProduct(product);
        return  new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteById(@PathVariable() Long productId){
      if(productService.removeById(productId)){
          return new ResponseEntity<>(HttpStatus.OK);
      }
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/{productId}")
    public ResponseEntity<?> updateById(@PathVariable Long productID, @RequestBody Product product){

        boolean saved = productService.updateById(productID, product);
        if (saved){
            return new ResponseEntity<>(HttpStatus.OK);

        }

        return  new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

}
