package com.store.storeapi.mappers;

import com.store.storeapi.dtos.CustomerDTO;
import com.store.storeapi.models.Customer;

public class CustomerMapper {

    static CustomerDTO entityToDto(Customer entity) {
        return new CustomerDTO(
                entity.getId(),
                entity.getName()
        );
    }

    static Customer dtoToEntity(CustomerDTO dto) {
        return new Customer(
                dto.id(),
                dto.name()
        );
    }
}
