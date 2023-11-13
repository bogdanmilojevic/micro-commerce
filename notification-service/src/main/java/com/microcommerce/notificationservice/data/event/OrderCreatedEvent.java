package com.microcommerce.notificationservice.data.event;

import com.microcommerce.notificationservice.data.dto.EventOrderItemDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreatedEvent {
    private String orderNumber;
    private List<EventOrderItemDto> orderItems;
    private UUID userId;

    // TODO: Add field User(uuid, email, firstName, lastName)
}
