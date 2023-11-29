package com.microcommerce.notificationservice.service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailSenderServiceImpl implements EmailSenderService {

    @Value("${spring.mail.verify.host}")
    private String host;

    @Value("${spring.mail.username}")
    private String sender;

    private final JavaMailSender emailSender;
    private final TemplateEngine templateEngine;

    @Async
    public void sendSimpleMailMessage(SimpleMailMessage message) {
        try {
            message.setFrom(sender);
            emailSender.send(message);
        } catch (Exception ex) {
            log.error("Exception occurred while sending an email: {}", ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    @Async
    public void sendHtmlMailMessage(MimeMessage message, Context context) {
        try {
            emailSender.send(message);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }
}
