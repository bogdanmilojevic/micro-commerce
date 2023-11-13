package com.microcommerce.orderservice.util.mapstrcut;

import com.microcommerce.orderservice.data.dto.OrderRequest;
import com.microcommerce.orderservice.data.dto.OrderResponse;
import com.microcommerce.orderservice.data.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {OrderItemMapper.class}
)
public interface OrderMapper {

    /**
     * Maps an {@link Order} entity to an {@link OrderResponse} object.
     *
     * @param orderEntity The source {@link Order} entity.
     * @return The mapped {@link OrderResponse} object.
     */
    OrderResponse toResponse(Order orderEntity);

    /**
     * Maps an {@link OrderRequest} object to an {@link Order} entity.
     *
     * @param orderRequest The source {@link OrderRequest} object.
     * @return The mapped {@link Order} entity.
     */
    Order toEntity(OrderRequest orderRequest, String orderNumber);
}
