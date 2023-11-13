package com.microcommerce.common.validation;

import lombok.*;

import java.util.Properties;

@Getter
@Setter
@Builder
public class Message {
    private String code;
    private String message;
    private Properties properties;

    public Message(String code, String message) {
        this.code = code;
        this.message = message;
        this.properties = null;
    }

    public Message(String code, String message, Properties properties) {
        this.code = code;
        this.message = message;
        this.properties = properties;
    }

    public Message() {
    }
}
