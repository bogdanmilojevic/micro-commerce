package com.microcommerce.orderservice.data.event;

import com.microcommerce.orderservice.data.dto.EventOrderItemDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderCreatedEvent {
    private String orderNumber;
    private List<EventOrderItemDto> orderItems;
    private UUID userId;
}
