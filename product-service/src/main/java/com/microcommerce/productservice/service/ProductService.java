package com.microcommerce.productservice.service;

import com.microcommerce.common.validation.ConstraintValidationRule;
import com.microcommerce.common.validation.ValidationResult;
import com.microcommerce.common.validation.Validator;
import com.microcommerce.common.web.ApiResponse;
import com.microcommerce.common.web.Pagination;
import com.microcommerce.productservice.data.dto.ProductRequest;
import com.microcommerce.productservice.data.dto.ProductResponse;
import com.microcommerce.productservice.data.entity.Product;
import com.microcommerce.productservice.repository.ProductRepository;
import com.microcommerce.productservice.util.mapstruct.ProductMapper;
import com.microcommerce.productservice.util.validation.SkuCodeDuplicationValidationRule;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final SkuCodeDuplicationValidationRule skuCodeDuplicationValidationRule;
    private final ConstraintValidationRule<ProductRequest> productRequestConstraintValidationRule;

    /**
     * Retrieve a paginated list of products.
     *
     * @param pageNumber    The page number to retrieve (starts from 0).
     * @param pageSize      The number of products to display on each page.
     * @param sortField     The field to sort the products by.
     * @param sortDirection The direction in which to sort the products (ascending or descending).
     * @return An ApiResponse containing a paginated list of product responses.
     */
    public ApiResponse<List<ProductResponse>> getAllProducts(Integer pageNumber, Integer pageSize, String sortField, Sort.Direction sortDirection) {
        // TODO: Validate arguments

        var productResponsePage = productRepository.findAll(PageRequest.of(pageNumber, pageSize, sortDirection, sortField))
                .map(productMapper::toResponse);

        return ApiResponse.<List<ProductResponse>>builder()
                .pagination(new Pagination(productResponsePage))
                .content(productResponsePage.getContent())
                .successfulOperation()
                .build();
    }

    /**
     * Creates a new product based on the provided product request.
     *
     * @param productRequest The product request containing information for the new product.
     * @return An ApiResponse containing the result of the product creation.
     * - If validation fails, a bad request ApiResponse is returned with error messages.
     * - If validation succeeds, the new product is created, and a success ApiResponse is returned
     * with the saved product information.
     */
    @Transactional
    public ApiResponse<ProductResponse> createProduct(ProductRequest productRequest) {
        ValidationResult validationResult = Validator.<ProductRequest>builder()
                .addRule(productRequestConstraintValidationRule)
                .addRule(skuCodeDuplicationValidationRule, true)
                .build()
                .validateRules(productRequest);

        if (!validationResult.isValid()) {
            return ApiResponse.<ProductResponse>builder()
                    .badRequest(validationResult.getMessages())
                    .build();
        }

        Product productEntity = productMapper.toEntity(productRequest);
        Product savedProduct = productRepository.save(productEntity);

        return ApiResponse.<ProductResponse>builder().successfulOperation(productMapper.toResponse(savedProduct)).build();
    }

    public ApiResponse<ProductResponse> updateProduct(ProductRequest productRequest) {
        throw new NotImplementedException();
    }

    /**
     * Retrieves the available stock of a product in the inventory based on its SKU code.
     *
     * @param skuCode The SKU code of the product to query.
     * @return An ApiResponse containing the product quantity if the product is found,
     * or an error response if the product is not found.
     */
    public ApiResponse<ProductResponse> getProductStock(String skuCode) {
        Optional<Product> productOptional = productRepository.findBySkuCode(skuCode);

        if (productOptional.isEmpty()) {
            return ApiResponse.<ProductResponse>builder()
                    .badRequest("Record not found", String.format("Product with sku code '%s' doesn't exist", skuCode))
                    .build();
        }

        Product productEntity = productOptional.get();
        ProductResponse product = ProductResponse.builder()
                .name(productEntity.getName())
                .skuCode(productEntity.getSkuCode())
                .stock(productEntity.getStock())
                .build();

        return ApiResponse.<ProductResponse>builder()
                .successfulOperation(product)
                .build();
    }

    public List<ProductResponse> getProductsBySkuCode(List<String> skuCodes) {
        return productRepository.findBySkuCodes(skuCodes).stream()
                .map(productMapper::toResponse)
                .toList();
    }
}
