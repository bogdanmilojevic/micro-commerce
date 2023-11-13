package com.microcommerce.notificationservice.listener;

import com.microcommerce.notificationservice.data.event.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductLowInStockEventListener {

    @KafkaListener(topics = {"notification-topic"}, groupId = "group-id")
    public void listen(OrderCreatedEvent orderCreatedEvent) {

//        var orderReceivedMailMessage = new SimpleMailMessage();
//        orderReceivedMailMessage.setTo("milojevicbogdan01@gmail.com");
//        orderReceivedMailMessage.setSubject("New order received!");
//        orderReceivedMailMessage.setText("New order received from user..." + Arrays.toString(products.toArray()));

//        emailSenderService.sendHtmlMailMessage(orderCreatedEvent.getOrderItems());
//        emailSenderService.sendSimpleMailMessage(orderReceivedMailMessage);
    }
}
