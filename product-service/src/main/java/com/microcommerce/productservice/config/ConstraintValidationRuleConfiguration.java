package com.microcommerce.productservice.config;

import com.microcommerce.productservice.data.dto.ProductRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.microcommerce.common.validation.ConstraintValidationRule;

@Configuration
public class ConstraintValidationRuleConfiguration {

    @Bean
    public ConstraintValidationRule<ProductRequest> productRequestConstraintValidationRule() {
        return new ConstraintValidationRule<>();
    }
}
