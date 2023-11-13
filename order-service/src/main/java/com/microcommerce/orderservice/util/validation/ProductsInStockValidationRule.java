package com.microcommerce.orderservice.util.validation;

import com.microcommerce.common.data.ProductErrorMessages;
import com.microcommerce.common.data.ProductResponse;
import com.microcommerce.common.validation.Message;
import com.microcommerce.common.validation.ValidationResult;
import com.microcommerce.common.validation.ValidationRule;
import com.microcommerce.orderservice.data.dto.OrderItemRequest;
import com.microcommerce.orderservice.data.dto.OrderRequest;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Properties;

@Component
@RequiredArgsConstructor
@Setter
public class ProductsInStockValidationRule implements ValidationRule<OrderRequest> {
    private List<ProductResponse> products;

    @Override
    public ValidationResult validate(OrderRequest orderRequest, ValidationResult validationResult) {
        if (products == null)
            throw new IllegalStateException("Field 'List<ProductResponse> products' is not initialized. Call setProducts(List<ProductResponse>) before evaluating validation rule.");

        List<ProductResponse> availableProductsQuantity = products;

        for (var orderItem : orderRequest.getOrderItems()) {
            var matchingProduct = findMatchingProduct(availableProductsQuantity, orderItem);

            if (matchingProduct.isEmpty()) {
                validationResult.addErrorMessage("Product not found", String.format(ProductErrorMessages.PRODUCT_WITH_SKU_NOT_FOUND, orderItem.getSkuCode()));
            } else {
                var orderedQuantity = orderItem.getOrderedQuantity();
                var stock = matchingProduct.get().getStock();

                if (orderedQuantity.compareTo(stock) > 0) {
                    var errorMessage = buildErrorMessage(orderItem, stock, orderedQuantity);
                    validationResult.addErrorMessage(errorMessage);
                }
            }
        }

        return validationResult;
    }

    private static Optional<ProductResponse> findMatchingProduct(List<ProductResponse> availableProductsQuantity, OrderItemRequest orderItem) {
        return availableProductsQuantity.stream()
                .filter(item -> item.getSkuCode().equals(orderItem.getSkuCode()))
                .findFirst();
    }

    private static Message buildErrorMessage(OrderItemRequest orderItem, Integer availableQuantity, Integer orderedQuantity) {
        Properties additionalInfo = new Properties() {{
            put("Ordered quantity", orderedQuantity.toString());
            put("Available quantity", availableQuantity.toString());
        }};

        return Message.builder()
                .code("Product not in stock.")
                .message(String.format(ProductErrorMessages.PRODUCT_NOT_IN_STOCK, orderItem.getSkuCode()))
                .properties(additionalInfo)
                .build();
    }
}
