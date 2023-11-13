package com.microcommerce.orderservice.enricher;

public interface Enricher <T>{
    T enrich(String identifier);
}
