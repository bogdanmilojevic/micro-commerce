package com.microcommerce.common.web;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.microcommerce.common.validation.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@JsonPropertyOrder({"success", "httpStatus", "messages", "content", "pagination"})
public class ApiResponse<T> {
    private boolean success;
    private String httpStatus;
    private List<Message> messages;
    private T content;
    private Pagination pagination;

    public ApiResponse() {
        this.success = false;
        this.httpStatus = "";
        this.pagination = null;
        this.content = null;
        this.messages = new ArrayList<>();
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = String.valueOf(httpStatus.value()).concat(" ").concat(httpStatus.name());
    }

    public static class ApiResponseBuilder<T> {
        ApiResponseBuilder() {
            this.messages = new ArrayList<>();
        }

        public ApiResponseBuilder<T> httpStatus(HttpStatus httpStatus) {
            this.httpStatus = String.valueOf(httpStatus.value()).concat(" ").concat(httpStatus.name());
            return this;
        }

        public ApiResponseBuilder<T> addMessage(String code, String message) {
            this.messages.add(Message.builder().code(code).message(message).build());
            return this;
        }

        public ApiResponseBuilder<T> badRequest(List<Message> messages) {
            return this.success(false)
                    .messages(messages)
                    .content(null)
                    .httpStatus(HttpStatus.BAD_REQUEST);
        }

        public ApiResponseBuilder<T> badRequest(String code, String message) {
            return this.success(false)
                    .addMessage(code, message)
                    .content(null)
                    .httpStatus(HttpStatus.BAD_REQUEST);
        }

        public ApiResponseBuilder<T> successfulOperation() {
            return this.success(true)
                    .addMessage("Success", "Operation completed successfully");
        }

        public ApiResponseBuilder<T> successfulOperation(T content) {
            return this.success(true)
                    .addMessage("Success", "Operation completed successfully")
                    .content(content);
        }
    }
}
