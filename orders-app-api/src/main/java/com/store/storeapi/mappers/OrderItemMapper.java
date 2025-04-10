package com.store.storeapi.mappers;

import com.store.storeapi.dtos.OrderItemDTO;
import com.store.storeapi.models.OrderItem;

public class OrderItemMapper {

    static OrderItemDTO entityToDto(OrderItem entity) {
        return new OrderItemDTO(
                entity.getId(),
                ProductMapper.entityToDto(entity.getProduct()),
                entity.getQuantity()
        );
    }

    static OrderItem dtoToEntity(OrderItemDTO dto) {
        return new OrderItem(
                dto.id(),
                ProductMapper.DtoToEntity(dto.productDTO()),
                dto.quantity()
        );
    }
}
