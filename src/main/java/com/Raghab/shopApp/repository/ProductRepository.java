package com.Raghab.shopApp.repository;
import com.Raghab.shopApp.entity.Product;import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product,Long> {
    Product getById(Long productId);

}
