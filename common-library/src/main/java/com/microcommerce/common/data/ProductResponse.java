package com.microcommerce.common.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    private String id;
    private String skuCode;
    private String name;
    private BigDecimal price;
//    private List<Review> reviews;
    private String description;
    private Map<String, String> properties;
    private Integer stock;
    private String imageUrl;
}
