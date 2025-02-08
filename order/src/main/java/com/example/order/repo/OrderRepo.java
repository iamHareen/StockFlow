package com.example.order.repo;
import com.example.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {
    Order getOrderById(int id);
//    @Query(value = "SELECT * FROM order WHERE id = ?1", nativeQuery = true)
//    Order getOrderById(Integer orderId);
}
