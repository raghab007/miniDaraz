package com.Raghab.shopApp.repository;

import com.Raghab.shopApp.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders,Long> {
    //Orders  findByOrderId(String Id);
}
