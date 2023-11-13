package com.microcommerce.productservice.util.validation;

import com.microcommerce.common.data.ProductErrorMessages;
import com.microcommerce.common.validation.ValidationResult;
import com.microcommerce.common.validation.ValidationRule;
import com.microcommerce.productservice.data.dto.ProductRequest;
import com.microcommerce.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SkuCodeDuplicationValidationRule implements ValidationRule<ProductRequest> {

    private final ProductRepository productRepository;

    @Override
    public ValidationResult validate(ProductRequest productRequest, ValidationResult validationResult) {
        if (productRepository.existsBySkuCode(productRequest.getSkuCode())) {
            validationResult.addErrorMessage("skuCode", String.format(ProductErrorMessages.SKU_CODE_PRESENT, productRequest.getSkuCode()));
        }
        return validationResult;
    }
}
