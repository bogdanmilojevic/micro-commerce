package com.microcommerce.orderservice.config;

import com.microcommerce.common.validation.SimpleValidator;
import com.microcommerce.orderservice.data.dto.OrderRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimpleValidatorConfiguration {

    @Bean
    public SimpleValidator<OrderRequest> simpleValidator() {
        return new SimpleValidator<>();
    }
}
