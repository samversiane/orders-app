package com.store.storeapi.mappers;

import com.store.storeapi.dtos.OrderDTO;
import com.store.storeapi.models.Order;

public class OrderMapper {

    static OrderDTO entityToDto(Order entity) {
        return new OrderDTO(
                entity.getId(),
                CustomerMapper.entityToDto(entity.getCustomer()),
                SupplierMapper.entityToDto(entity.getSupplier()),
                entity.getItems().stream().map(OrderItemMapper::entityToDto).toList()
        );
    }

    static Order DtoToEntity(OrderDTO dto) {
        return new Order(
                dto.id(),
                CustomerMapper.dtoToEntity(dto.customerDTO()),
                SupplierMapper.dtoToEntity(dto.supplierDTO()),
                dto.items().stream().map(OrderItemMapper::dtoToEntity).toList()
        );
    }
}
