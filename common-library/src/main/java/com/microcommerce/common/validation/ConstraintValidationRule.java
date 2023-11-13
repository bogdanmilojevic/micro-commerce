package com.microcommerce.common.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ConstraintValidationRule <T> implements ValidationRule<T> {

    @Override
    public ValidationResult validate(T toValidate, ValidationResult validationResult) {
        try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
            final Validator validator = validatorFactory.getValidator();
            final Set<ConstraintViolation<T>> constraintViolations = validator.validate(toValidate);

            if (!constraintViolations.isEmpty()) {
                constraintViolations.forEach(
                        violation -> validationResult.addErrorMessage(violation.getPropertyPath().toString(), violation.getMessage())
                );
            }
        }

        return validationResult;
    }
}
