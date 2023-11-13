package com.microcommerce.orderservice.config;

import com.microcommerce.common.validation.ConstraintValidationRule;
import com.microcommerce.orderservice.data.dto.OrderRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConstraintValidationRuleConfiguration {

    @Bean
    public ConstraintValidationRule<OrderRequest> orderRequestConstraintValidationRule() {
        return new ConstraintValidationRule<>();
    }
}
