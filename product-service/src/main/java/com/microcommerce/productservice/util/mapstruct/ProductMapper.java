package com.microcommerce.productservice.util.mapstruct;

import com.microcommerce.productservice.data.ProductProjection;
import com.microcommerce.productservice.data.dto.ProductRequest;
import com.microcommerce.productservice.data.dto.ProductResponse;
import com.microcommerce.productservice.data.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface ProductMapper {
    /**
     * Maps given ProductRequest instance to Product entity.
     * @param productRequest ProductRequest to map.
     * @return instance of Product entity.
     */
    Product toEntity(ProductRequest productRequest);

    ProductResponse toResponse(Product product);

    ProductResponse toResponse(ProductProjection productProjection);
}
