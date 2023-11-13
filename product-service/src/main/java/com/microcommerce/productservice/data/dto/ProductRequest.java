package com.microcommerce.productservice.data.dto;

import com.microcommerce.productservice.ProductRequestErrorMessages;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;
import java.util.Map;

@Data
@AllArgsConstructor
public class ProductRequest {

    @Length(
            min = 8, max = 12,
            message = ProductRequestErrorMessages.SKU_CODE_LENGTH
    )
    @NotBlank(message = ProductRequestErrorMessages.SKU_CODE_BLANK)
    private String skuCode;

    @Length(
            min = 3, max = 60,
            message = ProductRequestErrorMessages.NAME_LENGTH
    )
    @NotBlank(message = ProductRequestErrorMessages.NAME_BLANK)
    private String name;

    @DecimalMin(value = "1", message = ProductRequestErrorMessages.PRICE_MIN)
    @DecimalMax(value = "9999999", message = ProductRequestErrorMessages.PRICE_MAX)
    @NotNull(message = ProductRequestErrorMessages.PRICE_NULL)
    private BigDecimal price;

    @Size(max = 250, message = ProductRequestErrorMessages.DESCRIPTION_LENGTH)
    private String description;

    private Map<String, String> properties;

    @Min(value = 0, message = ProductRequestErrorMessages.QUANTITY_NEGATIVE)
    @NotNull(message = ProductRequestErrorMessages.QUANTITY_NULL)
    private Integer quantity;

    @URL(message = ProductRequestErrorMessages.IMAGE_URL_INVALID)
    private String imageUrl;
}
