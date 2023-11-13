package com.microcommerce.userservice.util.validation;

import com.microcommerce.common.validation.ValidationResult;
import com.microcommerce.common.validation.ValidationRule;
import com.microcommerce.userservice.data.dto.RegistrationRequest;
import com.microcommerce.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailUniqueValidationRule implements ValidationRule<RegistrationRequest> {

    private final UserRepository userRepository;

    @Override
    public ValidationResult validate(RegistrationRequest registrationRequest, ValidationResult validationResult) {
        if (userRepository.findByEmail(registrationRequest.getEmail()).isPresent()) {
            validationResult.addErrorMessage("Account already registered", "Account with provided email is already registered.");
        }

        return validationResult;
    }
}
