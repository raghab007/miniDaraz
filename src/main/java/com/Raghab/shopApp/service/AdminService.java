package com.Raghab.shopApp.service;
import com.Raghab.shopApp.entity.Product;
import com.Raghab.shopApp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired ProductRepository productRepository;
    public List<Product> getProducts(){
        return (ArrayList<Product>) productRepository.findAll();
    }

    public Product addProduct(Product product){
        return productRepository.save(product);
    }

    public boolean removeById(Long id){
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()){
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public Product getById(Long productId){
       return  productRepository.getById(productId);
    }

    public boolean updateById(Long productId,Product product) {
        Product product1 = getById(productId);
        if (product1 != null) {
            product1.setDescription(product.getDescription() != null && !product.getDescription().isEmpty() ? product.getDescription() : product1.getDescription());
            product1.setQuantity(product.getQuantity());
            return true;

        }
        return false;
    }


}
