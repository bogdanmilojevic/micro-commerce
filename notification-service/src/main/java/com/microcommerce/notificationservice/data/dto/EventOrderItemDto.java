package com.microcommerce.notificationservice.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventOrderItemDto {
    private String skuCode;
    private String productName;
    private Integer orderedQuantity;
    private BigDecimal totalAmount;
}
