package com.hareendev.orderservice.service;

import com.hareendev.inventoryservice.dto.InventoryDTO;
import com.hareendev.orderservice.common.ErrorOrderResponse;
import com.hareendev.orderservice.common.OrderResponse;
import com.hareendev.orderservice.common.SuccessOrderResponse;
import com.hareendev.orderservice.dto.OrderDTO;
import com.hareendev.orderservice.model.Order;
import com.hareendev.orderservice.repo.OrderRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@Transactional
public class OrderService {

    private final WebClient webClient;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ModelMapper modelMapper;

    public OrderService(WebClient.Builder webClientBuilder, OrderRepo orderRepo, ModelMapper modelMapper) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080/api/v1").build();
        this.orderRepo = orderRepo;
        this.modelMapper = modelMapper;
    }

    public List<OrderDTO> getAllOrders() {
        List<Order>orderList = orderRepo.findAll();
        return modelMapper.map(orderList, new TypeToken<List<OrderDTO>>(){}.getType());
    }

    public OrderResponse saveOrder(OrderDTO orderDTO) {
        Integer itemId = orderDTO.getItemId();
        try {
            InventoryDTO inventoryResponse = webClient.get()
                    // Inventory Get Items
                    .uri(uriBuilder -> uriBuilder.path("/getitem/{itemId}").build(itemId))
                    .retrieve()
                    .bodyToMono(InventoryDTO.class)
                    .block();

            if (inventoryResponse == null) {
                return new ErrorOrderResponse("Inventory service returned no data");
            }

//            System.out.println("*****************************\ninventoryResponse: "+inventoryResponse);
            if(inventoryResponse.getQuantity()>0) {
                orderRepo.save(modelMapper.map(orderDTO, Order.class));
                return new SuccessOrderResponse(orderDTO);
            } else {
                return new ErrorOrderResponse("Item Not available in the Inventory, Please try later");
            }
        } catch (Exception e) {
            return new ErrorOrderResponse("Failed to check inventory: " + e.getMessage());
        }
    }

//    public OrderDTO saveOrder(OrderDTO orderDTO) {
//        orderRepo.save(modelMapper.map(orderDTO, Order.class));
//        return orderDTO;
//    }

    public OrderDTO updateOrder(OrderDTO OrderDTO) {
        orderRepo.save(modelMapper.map(OrderDTO, Order.class));
        return OrderDTO;
    }

    public String deleteOrder(Integer orderId) {
        orderRepo.deleteById(orderId);
        return "Order deleted";
    }

    public OrderDTO getOrderById(Integer orderId) {
       Order order = orderRepo.getOrderById((orderId));
        return modelMapper.map(order, OrderDTO.class);
    }
}
