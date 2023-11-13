package com.microcommerce.productservice.repository;

import com.microcommerce.productservice.data.ProductProjection;
import com.microcommerce.productservice.data.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {
    boolean existsBySkuCode(String skuCode);
    Optional<Product> findBySkuCode(String skuCode);

    @Query(value="{ sku_code: { $in: ?0 } }", fields="{ 'skuCode' : 1, 'quantity' : 1}")
    List<ProductProjection> findQuantityBySkuCodes(List<String> skuCodes);

    @Query(value = "{sku_code: {$in: ?0}}")
    List<Product> findBySkuCodes(List<String> skuCodes);
}


