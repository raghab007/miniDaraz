package com.Raghab.shopApp.service;
import com.Raghab.shopApp.entity.Product;
import com.Raghab.shopApp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public void addProduct(Product product){
        productRepository.save(product);
    }

    public boolean removeProduct(Long productId){
        boolean isThere = findById(productId)!=null;
       if (findById(productId)!=null) {
           productRepository.deleteById(productId);

       }
        return isThere;
    }

    public void updateById(Product product, Long productId){
        Product oldProduct = productRepository.findById(productId).orElse(null);
        if (oldProduct!=null){
            oldProduct.setDescription(product.getDescription()!=null?product.getDescription(): oldProduct.getDescription());
            oldProduct.setQuantity(product.getQuantity());
            productRepository.save(oldProduct);
        }

    }

    public Product findById(Long id){
      return productRepository.findById(id).orElse(null);
    }

    public List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    public List<Product> getAllProductsByName(String name){
      return  productRepository.findAllByName(name);
    }

    public boolean updateById(Long productId,Product product) {
        Product product1 = findById(productId);
        if (product1 != null) {
            product1.setDescription(product.getDescription() != null && !product.getDescription().isEmpty() ? product.getDescription() : product1.getDescription());
            product1.setQuantity(product.getQuantity());
            return true;
        }
        return false;
    }

    public List<Product> getAllProductsByBrand(String brandName) {
       return productRepository.findAllByBrand(brandName);
    }
}
