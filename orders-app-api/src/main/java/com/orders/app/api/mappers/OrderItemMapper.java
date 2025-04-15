package com.orders.app.api.mappers;

import com.orders.app.api.dtos.OrderItemDTO;
import com.orders.app.api.models.OrderItem;

public class OrderItemMapper {

    public static OrderItemDTO entityToDto(OrderItem entity) {
        return new OrderItemDTO(
                entity.getId(),
                ProductMapper.entityToDto(entity.getProduct()),
                entity.getQuantity()
        );
    }

    public static OrderItem dtoToEntity(OrderItemDTO dto) {
        return new OrderItem(
                ProductMapper.dtoToEntity(dto.product()),
                dto.quantity()
        );
    }
}
