package com.microcommerce.userservice.util.validation;

import com.microcommerce.common.validation.ValidationResult;
import com.microcommerce.common.validation.ValidationRule;
import com.microcommerce.userservice.data.dto.RegistrationRequest;
import org.springframework.stereotype.Component;

@Component
public class PasswordValidationRule implements ValidationRule<RegistrationRequest> {

    /**
     * Password must:
     * <ul>
     *     <li>Be at least 8 characters long</li>
     *     <li>Contain at least one number</li>
     *     <li>Not contain whitespace characters</li>
     * </ul>
     */
    public static final String PASSWORD_REGEX = "^(?=.*?[A-Z]?)(?=.*?[a-z])(?=.*?[0-9]).{8,}$";

    @Override
    public ValidationResult validate(RegistrationRequest registrationRequest, ValidationResult validationResult) {
        if (!registrationRequest.getPassword().matches(PASSWORD_REGEX))
            validationResult.addErrorMessage("Password doesn't meet security requirements", "Password must be at least 8 characters long and contain one number. Whitespace characters aren't allowed.");

        return validationResult;
    }
}
