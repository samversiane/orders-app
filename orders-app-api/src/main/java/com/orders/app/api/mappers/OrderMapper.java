package com.orders.app.api.mappers;

import com.orders.app.api.dtos.OrderDTO;
import com.orders.app.api.models.Order;

public class OrderMapper {

    public static OrderDTO entityToDto(Order entity) {
        return new OrderDTO(
                entity.getId(),
                CustomerMapper.entityToDto(entity.getCustomer()),
                SupplierMapper.entityToDto(entity.getSupplier()),
                entity.getItems().stream().map(OrderItemMapper::entityToDto).toList()
        );
    }

    static Order dtoToEntity(OrderDTO dto) {
        return new Order(
                CustomerMapper.dtoToEntity(dto.customerDTO()),
                SupplierMapper.dtoToEntity(dto.supplierDTO()),
                dto.items().stream().map(OrderItemMapper::dtoToEntity).toList()
        );
    }
}
