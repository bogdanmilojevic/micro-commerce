package com.microcommerce.productservice.data.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;
import java.util.Map;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product extends AuditMetadata {
    @Id
    private String id;

    @Indexed(unique = true)
    @Field(name = "sku_code")
    private String skuCode;

    @Field(name = "name")
    private String name;

    @Field(name="price", targetType = FieldType.DECIMAL128)
    private BigDecimal price;

    @Field(name = "description")
    private String description;

    @Field(name = "properties")
    private Map<String, String> properties;

//    private List<Category> categories;
    @Field(name = "stock")
    private Integer stock;

    @Field(name = "image_url")
    private String imageUrl;

//    private List<Review> reviews;

    @Field(name = "is_deleted")
    private boolean isDeleted;
}
