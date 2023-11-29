package com.microcommerce.notificationservice.data.event;

import com.microcommerce.notificationservice.data.dto.EventOrderItemDto;
import com.microcommerce.notificationservice.data.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreatedEvent {
    private String orderNumber;
    private List<EventOrderItemDto> orderItems;
    private UserDto user;
}
