package com.microcommerce.notificationservice.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

@Service
public interface EmailSenderService {
    void sendSimpleMailMessage(SimpleMailMessage message);
    void sendHtmlMailMessage(MimeMessage message, Context context);
}