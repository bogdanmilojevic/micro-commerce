package com.microcommerce.orderservice.enricher;

import com.microcommerce.orderservice.data.dto.OrderRequest;

public class ProductEnricher implements Enricher<OrderRequest>{
    @Override
    public OrderRequest enrich(String identifier) {
        return null;
    }
}
