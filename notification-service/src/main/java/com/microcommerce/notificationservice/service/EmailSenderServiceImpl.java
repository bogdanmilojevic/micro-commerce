package com.microcommerce.notificationservice.service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailSenderServiceImpl implements EmailSenderService{

    public static final String ORDER_CONFIRMED_EMAIL_TEMPLATE = "order_confirmation_template";
    public static final String UTF_8_ENCODING = "UTF-8";

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

    // Todo: Refactor
    @Async
    public void sendHtmlMailMessage(MimeMessage message, Context context) {
        try {
            var helper = new MimeMessageHelper(message, true, UTF_8_ENCODING);
            helper.setTo("bogi.milojevic@gmail.com");
            emailSender.send(message);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }
}
