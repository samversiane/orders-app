package com.store.storeapi.mappers;

import com.store.storeapi.dtos.SupplierDTO;
import com.store.storeapi.models.Supplier;

public class SupplierMapper {

    static SupplierDTO entityToDto(Supplier entity) {
        return new SupplierDTO(
                entity.getId(),
                entity.getName()
        );
    }

    static Supplier dtoToEntity(SupplierDTO dto) {
        return new Supplier(
                dto.id(),
                dto.name()
        );
    }
}
