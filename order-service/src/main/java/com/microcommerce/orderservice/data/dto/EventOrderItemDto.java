package com.microcommerce.orderservice.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventOrderItemDto {
    private String skuCode;
    private String productName;
    private Integer orderedQuantity;
    private BigDecimal totalAmount;
}
