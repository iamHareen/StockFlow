package com.hareendev.orderservice.repo;

import com.hareendev.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {
//    @Query(value = "SELECT * FROM order WHERE id = ?1", nativeQuery = true)
//    Orders getOrderById(Integer orderId);
}
