package com.microcommerce.userservice.util.validation;

import com.microcommerce.common.validation.ValidationResult;
import com.microcommerce.common.validation.ValidationRule;
import com.microcommerce.userservice.data.dto.RegistrationRequest;
import org.springframework.stereotype.Component;

@Component
public class FirstAndLastNameValidationRule implements ValidationRule<RegistrationRequest> {
    @Override
    public ValidationResult validate(RegistrationRequest registrationRequest, ValidationResult validationResult) {
        return validationResult;
    }
}
