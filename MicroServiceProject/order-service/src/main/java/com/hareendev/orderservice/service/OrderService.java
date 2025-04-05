package com.hareendev.orderservice.service;

import com.hareendev.inventoryservice.dto.InventoryDTO;
import com.hareendev.orderservice.common.ErrorOrderResponse;
import com.hareendev.orderservice.common.OrderResponse;
import com.hareendev.orderservice.common.SuccessOrderResponse;
import com.hareendev.orderservice.dto.OrderDTO;
import com.hareendev.orderservice.model.Order;
import com.hareendev.orderservice.repo.OrderRepo;
import com.hareendev.productservice.dto.ProductDTO;
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

    private final WebClient inventoryWebClient;
    private final WebClient productWebClient;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ModelMapper modelMapper;

    public OrderService(WebClient inventoryWebClient, WebClient productWebClient, OrderRepo orderRepo, ModelMapper modelMapper) {
        this.inventoryWebClient = inventoryWebClient;
        this.productWebClient = productWebClient;
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
            InventoryDTO inventoryResponse = inventoryWebClient.get()
                    // Inventory Get Items
                    .uri(uriBuilder -> uriBuilder.path("/getitem/{itemId}").build(itemId))
                    .retrieve()
                    .bodyToMono(InventoryDTO.class)
                    .block();

            if (inventoryResponse == null) {
                return new ErrorOrderResponse("Inventory service returned no data");
            }

            Integer productId = inventoryResponse.getProductId();
            ProductDTO productResponse = productWebClient.get()
                    // Inventory Get Items
                    .uri(uriBuilder -> uriBuilder.path("/product/{productId}").build(productId))
                    .retrieve()
                    .bodyToMono(ProductDTO.class)
                    .block();
            if (productResponse == null) {
                return new ErrorOrderResponse("Product service returned no data");
            }

            if(inventoryResponse.getQuantity()>0) {
                if(productResponse.getForSale()==1) {
                    orderRepo.save(modelMapper.map(orderDTO, Order.class));
                    return new SuccessOrderResponse(orderDTO);
                } else {
                    return new ErrorOrderResponse("Item is not for sale");
                }
            } else {
                return new ErrorOrderResponse("Item Not available in the Inventory, Please try later");
            }
        } catch (Exception e) {
            return new ErrorOrderResponse("Failed to check inventory: " + e.getMessage());
        }
    }


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
