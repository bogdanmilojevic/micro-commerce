package com.microcommerce.productservice.repository;

import com.microcommerce.productservice.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    boolean existsBySkuCode(String skuCode);
    Optional<Product> findBySkuCode(String skuCode);
    List<Product> findProductsBySkuCodeIn(List<String> skuCodes);
}


