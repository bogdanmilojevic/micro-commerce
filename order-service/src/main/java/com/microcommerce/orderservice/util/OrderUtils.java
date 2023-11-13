package com.microcommerce.orderservice.util;

import com.microcommerce.common.data.ProductResponse;
import com.microcommerce.orderservice.data.dto.EventOrderItemDto;
import com.microcommerce.orderservice.data.dto.OrderItemRequest;
import com.microcommerce.orderservice.data.dto.OrderRequest;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

public class OrderUtils {

    public static List<String> extractSkuCodes(OrderRequest orderRequest) {
        return orderRequest.getOrderItems().stream()
                .map(OrderItemRequest::getSkuCode)
                .collect(Collectors.toList());
    }

    public static String generateOrderNumber() {
        return UUID.randomUUID()
                .toString()
                .substring(0, 5).toUpperCase() +
                OffsetDateTime.now().getSecond() +
                "-" +
                OffsetDateTime.now().getNano();
    }

    public static List<EventOrderItemDto> getEventOrderItems(OrderRequest orderRequest, List<ProductResponse> enrichedProducts) {
        return enrichedProducts.stream()
                .map(enrichedProduct -> {
                    var matchingProduct = getMatchingProduct(orderRequest, enrichedProduct);
                    var totalAmount = enrichedProduct.getPrice().multiply(BigDecimal.valueOf(matchingProduct.getOrderedQuantity()));
                    return new EventOrderItemDto(matchingProduct.getSkuCode(), enrichedProduct.getName(), matchingProduct.getOrderedQuantity(), totalAmount);
                }).collect(Collectors.toList());
    }

    private static OrderItemRequest getMatchingProduct(OrderRequest orderRequest, ProductResponse enrichedProduct) {
        return orderRequest.getOrderItems()
                .stream()
                .filter(item -> item.getSkuCode().equals(enrichedProduct.getSkuCode()))
                .findFirst().orElseThrow(NoSuchElementException::new);
    }
}
