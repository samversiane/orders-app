package com.orders.app.api.mappers;

import com.orders.app.api.dtos.ProductDTO;
import com.orders.app.api.models.Product;

public class ProductMapper {

    static ProductDTO entityToDto(Product entity) {
        return new ProductDTO(
                entity.getId(),
                entity.getName(),
                entity.getPrice()
        );
    }

    static Product dtoToEntity(ProductDTO dto) {
        return new Product(
                dto.name(),
                dto.price()
        );
    }
}
