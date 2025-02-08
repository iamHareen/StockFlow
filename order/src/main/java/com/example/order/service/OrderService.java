package com.example.order.service;

import com.example.order.dto.OrderDTO;
import com.example.order.model.Order;
import com.example.order.repo.OrderRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<OrderDTO> getAllOrders() {
        List<Order>orderList = orderRepo.findAll();
        return modelMapper.map(orderList, new TypeToken<List<OrderDTO>>(){}.getType());
    }

    public OrderDTO getOrderById(Integer orderId) {
        Order order = orderRepo.getOrderById((orderId));
        return modelMapper.map(order, OrderDTO.class);
    }

    public OrderDTO saveOrder(OrderDTO orderDTO) {
        orderRepo.save(modelMapper.map(orderDTO, Order.class));
        return orderDTO;
    }

    public OrderDTO updateOrder(OrderDTO OrderDTO) {
        orderRepo.save(modelMapper.map(OrderDTO, Order.class));
        return OrderDTO;
    }

    public String deleteOrder(Integer orderId) {
        orderRepo.deleteById(orderId);
        return "Order deleted";
    }

}
