package com.microcommerce.common.data;

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
            message = ProductErrorMessages.SKU_CODE_LENGTH
    )
    @NotBlank(message = ProductErrorMessages.SKU_CODE_BLANK)
    private String skuCode;

    @Length(
            min = 3, max = 60,
            message = ProductErrorMessages.NAME_LENGTH
    )
    @NotBlank(message = ProductErrorMessages.NAME_BLANK)
    private String name;

    @DecimalMin(value = "1", message = ProductErrorMessages.PRICE_MIN)
    @DecimalMax(value = "9999999", message = ProductErrorMessages.PRICE_MAX)
    @NotNull(message = ProductErrorMessages.PRICE_NULL)
    private BigDecimal price;

    @Size(max = 250, message = ProductErrorMessages.DESCRIPTION_LENGTH)
    private String description;

    private Map<String, String> properties;

    @Min(value = 0, message = ProductErrorMessages.QUANTITY_NEGATIVE)
    @NotNull(message = ProductErrorMessages.QUANTITY_NULL)
    private Integer quantity;

    @URL(message = ProductErrorMessages.IMAGE_URL_INVALID)
    private String imageUrl;
}
