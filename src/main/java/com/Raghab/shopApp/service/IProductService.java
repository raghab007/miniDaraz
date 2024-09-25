package com.Raghab.shopApp.service;

import com.Raghab.shopApp.entity.Product;

public interface IProductService {
    Product addProduct(Product product);

    void deleteByProductId(Long productId);
}
