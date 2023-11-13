package com.microcommerce.productservice.controller;

import com.microcommerce.common.web.ApiResponse;
import com.microcommerce.productservice.data.dto.ProductRequest;
import com.microcommerce.productservice.data.dto.ProductResponse;
import com.microcommerce.productservice.service.ProductService;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // TODO: Add includeDeleted queryParameter
    @GetMapping
    @RolesAllowed("{ADMIN,USER}")
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getAllProducts(
            @RequestParam(required = false, defaultValue = "1") int pageNumber,
            @RequestParam(required = false, defaultValue = "10") int pageSize,
            @RequestParam(required = false, defaultValue = "price") String sortField,
            @RequestParam(required = false, defaultValue = "ASC") Sort.Direction sortDirection
    ) {
        var responseBody = productService.getAllProducts(pageNumber, pageSize, sortField, sortDirection);
        var httpStatus = responseBody.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(httpStatus).body(responseBody);
    }

//    @GetMapping("{id}")
//    public ResponseEntity<ApiResponse<List<ProductResponse>>> getProduct(@PathVariable String id) {
//        var responseBody = productService.getProduct(id);
//        var httpStatus = responseBody.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
//
//        return ResponseEntity.status(httpStatus).body(responseBody);
//    }

    @PostMapping
    @RolesAllowed("{ADMIN}")
    public ResponseEntity<ApiResponse<ProductResponse>> createProduct(@RequestBody ProductRequest productRequest) {
        var responseBody = productService.createProduct(productRequest);
        var httpStatus = responseBody.isSuccess() ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(httpStatus).body(responseBody);
    }

    @GetMapping("/{skuCode}")
    @RolesAllowed({"ADMIN,USER"})
    public ResponseEntity<ApiResponse<ProductResponse>> getProductStock(@PathVariable String skuCode) {
        var responseBody = productService.getProductStock(skuCode);
        var httpStatus = responseBody.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND;

        return ResponseEntity.status(httpStatus).body(responseBody);
    }

    // ! INTERNAL API, PROTECT WITH API KEY
    @GetMapping(params = {"skuCodes"})
    public ResponseEntity<List<ProductResponse>> getProductsBySkuCode(@RequestParam() List<String> skuCodes) {
        return ResponseEntity.ok(productService.getProductsBySkuCode(skuCodes));
    }
}
