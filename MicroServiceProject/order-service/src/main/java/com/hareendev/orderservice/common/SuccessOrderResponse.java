package com.hareendev.orderservice.common;

import com.hareendev.orderservice.dto.OrderDTO;
import lombok.Getter;

@Getter
public class SuccessOrderResponse implements OrderResponse {
    private final OrderDTO order;

    public SuccessOrderResponse(OrderDTO order) {
        this.order = order;
    }
}
