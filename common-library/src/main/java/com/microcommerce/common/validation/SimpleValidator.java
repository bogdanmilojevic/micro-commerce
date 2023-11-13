package com.microcommerce.common.validation;

public class SimpleValidator <T> {
    public ValidationResult validate(T objectToValidate, ValidationRule<T> validationRule) {
        return new Validator<T>().validateRule(objectToValidate, validationRule);
    }
}
