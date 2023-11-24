package com.microcommerce.orderservice.data.event;

import com.microcommerce.orderservice.data.dto.EventOrderItemDto;
import com.microcommerce.orderservice.data.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderCreatedEvent {
    private String orderNumber;
    private List<EventOrderItemDto> orderItems;
    private UserDto user;
}
