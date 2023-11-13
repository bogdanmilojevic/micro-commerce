package com.microcommerce.userservice.cofniguration;

import com.microcommerce.common.validation.ConstraintValidationRule;
import com.microcommerce.userservice.data.dto.LoginRequest;
import com.microcommerce.userservice.data.dto.RegistrationRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConstraintValidationRuleConfiguration {

    @Bean
    public ConstraintValidationRule<RegistrationRequest> registrationRequestConstraintValidationRule() {
        return new ConstraintValidationRule<>();
    }

    @Bean
    public ConstraintValidationRule<LoginRequest> loginRequestConstraintValidationRule() {
        return new ConstraintValidationRule<>();
    }
}

