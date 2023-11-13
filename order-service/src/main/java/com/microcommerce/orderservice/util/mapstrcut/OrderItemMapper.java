package com.microcommerce.orderservice.util.mapstrcut;

import com.microcommerce.orderservice.data.dto.OrderItemRequest;
import com.microcommerce.orderservice.data.entity.OrderItem;
import com.microcommerce.orderservice.data.dto.EventOrderItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface OrderItemMapper {

    OrderItem toEntity(OrderItemRequest orderItemRequest);

    EventOrderItemDto toOrderItemEventDto(OrderItemRequest orderItemRequest);
}
