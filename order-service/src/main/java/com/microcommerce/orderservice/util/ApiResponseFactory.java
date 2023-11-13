package com.microcommerce.orderservice.util;

import com.microcommerce.common.validation.Message;
import com.microcommerce.common.web.ApiResponse;
import com.microcommerce.orderservice.data.dto.OrderResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApiResponseFactory<T> {

    /**
     * Builds ApiResponse<T> instance with content = null
     * and message = "Success", "Operation completed successfully"
     * @return ApiResponse<T> instance
     */
    public ApiResponse<T> successfulOperation() {
        return ApiResponse.<T>builder().successfulOperation().build();
    }

    /**
     * Builds ApiResponse<T> instance with content = null and provided message list
     * @param messages
     * @return
     */
    public ApiResponse<T> badRequest(List<Message> messages) {
        return ApiResponse.<T>builder().badRequest(messages).build();
    }




}
