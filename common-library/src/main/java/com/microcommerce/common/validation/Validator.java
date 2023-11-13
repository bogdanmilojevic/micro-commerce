package com.microcommerce.common.validation;

import java.util.LinkedHashMap;
import java.util.Map;

public class Validator<T> {

    private final Map<ValidationRule<T>, Boolean> validationRules;

    private Validator(Map<ValidationRule<T>, Boolean> rules) {
        this.validationRules = rules;
    }

    public Validator() {
        validationRules = null;
    }

    public ValidationResult validateRules(T objectToValidate) {
        ValidationResult validationResult = new ValidationResult();

        for (Map.Entry<ValidationRule<T>, Boolean> rule : validationRules.entrySet()) {
            validationResult = rule.getKey().applyValidationRule(objectToValidate, validationResult, rule.getValue());
        }

        return validationResult;
    }

    public ValidationResult validateRule(T objectToValidate, ValidationRule<T> validationRule) {
        return validationRule.validate(objectToValidate, new ValidationResult());
    }

    public static <T> ValidatorBuilder<T> builder() {
        return new ValidatorBuilder<T>();
    }

    public static class ValidatorBuilder<T> {
        private final Map<ValidationRule<T>, Boolean> validationRules;

        ValidatorBuilder() {
            // ! LinkedHashMap keeps an order of insertion!!!
            validationRules = new LinkedHashMap<>();
        }

        public ValidatorBuilder<T> addRule(ValidationRule<T> rule, boolean skipOnExistingErrors) {
            validationRules.put(rule, skipOnExistingErrors);
            return this;
        }

        public ValidatorBuilder<T> addRule(ValidationRule<T> rule) {
            validationRules.put(rule, false);
            return this;
        }

        public Validator<T> build() {
            return new Validator<>(this.validationRules);
        }
    }
}
