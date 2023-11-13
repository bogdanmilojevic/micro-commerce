package com.microcommerce.orderservice.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.microcommerce.orderservice.data.OrderStatus;
import com.microcommerce.orderservice.data.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    @JsonProperty("order_id")
    private String id;
    private List<OrderItem> orderItems;
    private UUID userId;
    private BigDecimal totalAmount;
    private OrderStatus orderStatus;
}
