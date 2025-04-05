package com.hareendev.orderservice.repo;

import com.hareendev.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {
    Order getOrderById(Integer orderId);
}
