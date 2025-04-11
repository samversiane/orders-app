package com.orders.app.api.mappers;

import com.orders.app.api.dtos.CustomerDTO;
import com.orders.app.api.models.Customer;

public class CustomerMapper {

    static CustomerDTO entityToDto(Customer entity) {
        return new CustomerDTO(
                entity.getId(),
                entity.getName()
        );
    }

    static Customer dtoToEntity(CustomerDTO dto) {
        return new Customer(
                dto.name()
        );
    }
}
