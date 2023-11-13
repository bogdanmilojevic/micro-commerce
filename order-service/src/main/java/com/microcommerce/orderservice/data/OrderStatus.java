package com.microcommerce.orderservice.data;

public enum OrderStatus {
    NEW("New"),
    PROCESSING("Processing"),
    COMPLETED("Completed"),
    REJECTED("Rejected");

    OrderStatus(String value) {}
}
