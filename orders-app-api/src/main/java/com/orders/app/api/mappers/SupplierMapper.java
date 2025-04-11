package com.orders.app.api.mappers;

import com.orders.app.api.dtos.SupplierDTO;
import com.orders.app.api.models.Supplier;

public class SupplierMapper {

    static SupplierDTO entityToDto(Supplier entity) {
        return new SupplierDTO(
                entity.getId(),
                entity.getName()
        );
    }

    static Supplier dtoToEntity(SupplierDTO dto) {
        return new Supplier(
                dto.name()
        );
    }
}
