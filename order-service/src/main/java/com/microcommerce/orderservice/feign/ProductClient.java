package com.microcommerce.orderservice.feign;

import com.microcommerce.common.data.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient("product-service")
public interface ProductClient {

    @RequestMapping(method = RequestMethod.GET, value = "api/product/{sku-code}")
    ProductResponse getProduct(@PathVariable("sku-code") String skuCode);

    /**
     * Retrieves products with matching sku codes.
     * @param skuCodes list of sku codes to search by
     * @return list of products
     */
    @RequestMapping(method = RequestMethod.GET, value = "api/product")
    List<ProductResponse> getProducts(@RequestParam("skuCodes") List<String> skuCodes);
}
