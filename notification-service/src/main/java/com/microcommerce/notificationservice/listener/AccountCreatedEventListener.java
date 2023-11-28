package com.microcommerce.notificationservice.listener;

import com.microcommerce.notificationservice.data.event.AccountCreatedEvent;
import com.microcommerce.notificationservice.service.EmailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AccountCreatedEventListener {

    private final EmailSenderService emailSenderService;

    @KafkaListener(topics = "${kafka.topics.account-topic}", groupId = "group-id-2", containerFactory = "accountCreatedListenerContainerFactory")
    public void listen(AccountCreatedEvent accountCreatedEvent) {

        System.out.println("New account created " + accountCreatedEvent.getEmail());

        var message = new SimpleMailMessage();
        message.setTo(accountCreatedEvent.getEmail());
        message.setSubject("Micro-Commerce Account Confirmation");
        message.setText("Hello " + accountCreatedEvent.getFirstName() + ",\n" +
                "Please confirm your account by following the link: " +
                "http://localhost:8086/api/auth/confirm?token=" + accountCreatedEvent.getActivationToken());
        emailSenderService.sendSimpleMailMessage(message);
    }
}
