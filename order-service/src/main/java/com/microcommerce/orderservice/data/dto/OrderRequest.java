package com.microcommerce.orderservice.data.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.UUID;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderRequest {

    @NotNull
    @NotEmpty
    @Valid
    private List<OrderItemRequest> orderItems;

    @NotNull
    private UserDto user;
}
