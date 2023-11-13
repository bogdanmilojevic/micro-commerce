package com.microcommerce.notificationservice.listener;

import com.microcommerce.notificationservice.data.event.OrderCreatedEvent;
import com.microcommerce.notificationservice.service.OrderCreatedEventHandlerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

// TODO: Send email to administrator when stock is low (below defined number of items available)
// TODO: Add webhook implementation

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderCreatedEventListener {

    private final OrderCreatedEventHandlerService orderCreatedEventHandler;

    @KafkaListener(topics = {"notification-topic"}, groupId = "group-id")
    public void listen(OrderCreatedEvent orderCreatedEvent) {
        log.info("Order created event received.");
        orderCreatedEventHandler.sendOrderConfirmedMail(orderCreatedEvent);
    }
}
