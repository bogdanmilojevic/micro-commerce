package com.microcommerce.notificationservice.data.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductLowInStockEvent {
    private String productName;
    private String skuCode;
    private Integer availableQuantity;
}
