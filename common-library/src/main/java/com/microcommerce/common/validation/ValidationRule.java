package com.microcommerce.common.validation;

public interface ValidationRule<T> {
    ValidationResult validate(T toValidate, ValidationResult validationResult);

    default ValidationResult applyValidationRule(T toValidate, ValidationResult validationResult, boolean skipOnExistingErrors) {
        if (skipOnExistingErrors && validationResult.isNotValid()) {
            return validationResult;
        }

        return validate(toValidate, validationResult);
    }
}
