package com.microcommerce.common.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ValidationResult {

    private boolean isValid;
    private List<Message> messages;

    public ValidationResult() {
        isValid = false;
        this.messages = new ArrayList<>();
    }

    public static ValidationResult valid() {
        return new ValidationResult(true, null);
    }

    public static ValidationResult invalid(List<Message> messages) {
        return new ValidationResult(false, messages);
    }

    public boolean isValid() {
        return messages.isEmpty();
    }

    public boolean isNotValid() {
        return !this.isValid();
    }

    public void addErrorMessage(String code, String message) {
        messages.add(Message.builder()
                .code(code)
                .message(message)
                .build()
        );
    }

    public void addErrorMessage(Message message) {
        messages.add(message);
    }
}
