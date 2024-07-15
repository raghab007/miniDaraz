package com.Raghab.shopApp.repository;

import com.Raghab.shopApp.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository  extends JpaRepository<Cart,Integer> {
}
