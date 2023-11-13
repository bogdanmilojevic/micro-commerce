package com.microcommerce.orderservice.controller;

import com.microcommerce.common.web.ApiResponse;
import com.microcommerce.orderservice.data.dto.OrderRequest;
import com.microcommerce.orderservice.data.dto.OrderResponse;
import com.microcommerce.orderservice.service.OrderService;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @RolesAllowed({"ADMIN,USER"})
    public ResponseEntity<ApiResponse<OrderResponse>> createOrder(@RequestBody OrderRequest orderRequest) {
        var response = orderService.createOrder(orderRequest);
        var httpStatus = response.isSuccess() ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(httpStatus).body(response);
    }

    @DeleteMapping("{order-number}")
    @RolesAllowed("{ADMIN}")
    public ResponseEntity<ApiResponse<Void>> deleteOrder(@PathVariable("order-number") String orderNumber) {
        return ResponseEntity.ok(orderService.deleteOrder(orderNumber));
    }
}
